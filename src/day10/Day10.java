package day10;

import tools.AOC;
import tools.Pair;

import java.util.*;
import java.util.stream.Collectors;

class Pipe{
    private char tile;
    private int x, y;
    private boolean visited;

    public Pipe(char tile, int x, int y) {
        this.tile = tile;
        this.x = x;
        this.y = y;
        this.visited = false;
    }

    public boolean isVisited(){
        return visited;
    }

    public void reset(){
        this.visited=false;
    }

    public void visit(){
        this.visited=true;
    }

    public boolean isStarting(){
        return tile=='S';
    }

    public Pair<Integer> getPos(){
        return new Pair<>(x,y);
    }

    public char getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return "Pipe{" +
                "tile=" + tile +
                ", x=" + x +
                ", y=" + y +
                ", visited=" + visited +
                '}';
    }
}

public class Day10 extends AOC {

    private List<Pipe> pipes;
    private List<char[]> map;
    private HashMap<Pair, Integer> steps;

    public Day10() {
        super("./files/" + Day10.class.getPackageName() + ".txt");

        map = getStream().map(String::toCharArray).collect(Collectors.toList());
        pipes = new ArrayList<>();

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                if(map.get(i)[j]!='.') pipes.add(new Pipe(map.get(i)[j],i,j));
            }
        }
    }

    public Pipe getStarting(){
        int i = 0;
        while(i<pipes.size() && !pipes.get(i).isStarting())i++;
        return pipes.get(i);
    }

    public int getPipeIndex(Pair<Integer> pos){
        int i = 0;
        while(i<pipes.size() && !pipes.get(i).getPos().equals(pos))i++;
        return (i<pipes.size()) ? i : -1;
    }

    public List<Pipe> getNeighbors(Pipe current){
        List<Pipe> neigbors = new ArrayList<>(2);

        Pair<Integer> currentPosition = current.getPos();
        int pos = getPipeIndex(new Pair<>(currentPosition.getOne()-1, currentPosition.getTwo()));
        //Look up
        if(pos!=-1 && "F7|".contains(pipes.get(pos).getTile()+"") && "JL|S".contains(current.getTile()+"")){
            neigbors.add(pipes.get(pos));
        }
        //Look down
        pos = getPipeIndex(new Pair<>(currentPosition.getOne()+1, currentPosition.getTwo()));
        if(pos!=-1 && "JL|".contains(pipes.get(pos).getTile()+"") && "F7|S".contains(current.getTile()+"")){
            neigbors.add(pipes.get(pos));
        }
        //Look left
        pos = getPipeIndex(new Pair<>(currentPosition.getOne(), currentPosition.getTwo()-1));
        if(pos!=-1 && "FL-".contains(pipes.get(pos).getTile()+"") && "J7-S".contains(current.getTile()+"")){
            neigbors.add(pipes.get(pos));
        }
        //Look down
        pos = getPipeIndex(new Pair<>(currentPosition.getOne(), currentPosition.getTwo()+1));
        if(pos!=-1 && "J7-".contains(pipes.get(pos).getTile()+"") && "FL-S".contains(current.getTile()+"")){
            neigbors.add(pipes.get(pos));
        }

        return neigbors;
     }

    @Override
    public void part1() {
        steps = new HashMap<>();

        Pipe starting = getStarting();

        Queue<Pipe> visit = new ArrayDeque<>();

        visit.add(starting);
        steps.put(starting.getPos(), 0);

        while(!visit.isEmpty()){
            Pipe current = visit.poll();

            current.visit();

            getNeighbors(current).stream().filter(pipe -> !pipe.isVisited()).forEach(pipe -> {
                visit.add(pipe);
                steps.put(pipe.getPos(), steps.get(current.getPos())+1);
            });
        }

        int max = 0;
        for (Map.Entry<Pair, Integer> entry : steps.entrySet()) {
            if(max< entry.getValue()) max = entry.getValue();;
        }

        System.out.println(max);

    }

    public char[][] enhancePixel(char pixel) {
        char[][] enhanced = new char[3][3];

        for (int i = 0; i < enhanced.length; i++) {
            for (int j = 0; j < enhanced.length; j++) {
                enhanced[i][j] = '.';
            }
        }
        enhanced[1][1] = pixel;


        if (pixel == 'S') {
            enhanced[0][1] = '|';
            enhanced[2][1] = '|';
            enhanced[1][0] = '-';
            enhanced[1][2] = '-';
        }

        if ("|LJ".contains(pixel + "")) {
            enhanced[0][1] = '|';
        }
        if ("|7F".contains(pixel + "")) {
            enhanced[2][1] = '|';
        }
        if ("-J7".contains(pixel + "")) {
            enhanced[1][0] = '-';
        }
        if ("-LF".contains(pixel + "")) {
            enhanced[1][2] = '-';
        }

        return enhanced;
    }

    @Override
    public void part2() {
        char[][] connected = new char[map.size()*3][map.get(0).length*3];
        boolean[][] visited = new boolean[map.size()*3][map.get(0).length*3];

        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).length; j++) {
                char[][] enhanced = enhancePixel(map.get(i)[j]);
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 3; l++) {
                        connected[i*3+k][j*3+l]= (steps.containsKey(new Pair<>(i,j)) && enhanced[k][l]!='.') ? '#' : enhanced[k][l];
                        visited[i*3+k][j*3+l]= (steps.containsKey(new Pair<>(i,j)) && enhanced[k][l]!='.');
                    }
                }
            }
        }

        Stack<Pair<Integer>> coordinates = new Stack<>();
        //Add 4 corners before floodfill
        coordinates.push(new Pair<>(0,0));
        coordinates.push(new Pair<>(0, connected[0].length-1));
        coordinates.push(new Pair<>(connected.length-1, 0));
        coordinates.push(new Pair<>(connected.length-1, connected[0].length-1));

        while(!coordinates.isEmpty()){
            Pair<Integer> pair = coordinates.pop();

            connected[pair.getOne()][pair.getTwo()]='O';
            visited[pair.getOne()][pair.getTwo()]= true;

            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    if((Math.abs(i)-Math.abs(j)!=0) &&
                        (pair.getOne()+i>=0 && pair.getOne()+i<connected.length)&&
                            (pair.getTwo()+j>=0 && pair.getTwo()+j<connected[0].length)){
                        if(connected[pair.getOne()+i][pair.getTwo()+j]!='#' && !visited[pair.getOne()+i][pair.getTwo()+j]){
                            coordinates.add(new Pair<>(pair.getOne()+i,pair.getTwo()+j));
                        }
                    }
                }
            }
        }
        visited=null;
        pipes=null;

        for (int i = 0; i < connected.length; i++) {
            for (int j = 0; j < connected[i].length; j++) {
                if(!"#O".contains(connected[i][j]+"")){
                    connected[i][j]='I';
                }
            }
        }
        int sum = 0;

        for (int i = 1; i < connected.length; i+=3) {
            for (int j = 1; j < connected[i].length; j+=3) {
                if(connected[i][j]=='I'){
                    sum++;
                }
            }
        }

        System.out.println(sum++);
    }

    public static void main(String[] args) {
        Day10 day = new Day10();
        day.part1();
        day.part2();
    }
}