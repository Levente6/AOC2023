package day11;

import tools.AOC;
import tools.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 extends AOC {
    private List<char[]> image;

    public Day11() {
        super("./files/" + Day11.class.getPackageName() + ".txt");

        image = getStream().map(String::toCharArray).collect(Collectors.toList());

    }

    public boolean isRowEmpty(int row){
        int i = 0;
        while(i < image.size() && image.get(i)[row]=='.')i++;
        return i>=image.size();
    }


    public boolean isColEmpty(int col){
        int i = 0;
        while(i < image.get(0).length && image.get(col)[i]=='.')i++;
        return i>=image.size();
    }

    public int getManhattan(Pair<Integer> p1, Pair<Integer> p2){
        return Math.abs(p1.getOne()- p2.getOne())+Math.abs(p1.getTwo()- p2.getTwo());
    }

    public List<Pair> getGalaxies(int offset){
        List<Pair> galaxies = new ArrayList<>();

        int x=0;
        for (int i = 0; i < image.size(); i++) {
            if(isColEmpty(i)){
                x += offset;
            }else {
                int y = 0;
                for (int j = 0; j < image.get(i).length; j++) {
                    if(isRowEmpty(j)) y+=offset;
                    else{
                        if(image.get(i)[j]=='#'){
                            galaxies.add(new Pair<>(x,y));
                        }
                        y++;
                    }
                }
                x++;
            }
        }

        return galaxies;
    }

    @Override
    public void part1() {
        List<Pair> galaxies = getGalaxies(2);

        int sum = 0;

        for (int i = 0; i < galaxies.size()-1; i++) {
            int minManhattan = Integer.MAX_VALUE;
            for (int j = i+1; j < galaxies.size(); j++) {
                int manhattan = getManhattan(galaxies.get(i),galaxies.get(j));
                //System.out.println(galaxies.get(i) + "->" + galaxies.get(j) + ": " + manhattan);
                sum+=manhattan;
            }
        }
        System.out.println(sum);

    }

    @Override
    public void part2() {
        List<Pair> galaxies = getGalaxies(1000000);

        long sum = 0;

        for (int i = 0; i < galaxies.size()-1; i++) {
            int minManhattan = Integer.MAX_VALUE;
            for (int j = i+1; j < galaxies.size(); j++) {
                int manhattan = getManhattan(galaxies.get(i),galaxies.get(j));
                //System.out.println(galaxies.get(i) + "->" + galaxies.get(j) + ": " + manhattan);
                sum+=manhattan;
            }
        }
        System.out.println(sum);

    }

    public static void main(String[] args) {
        Day11 day = new Day11();
        day.part1();
        day.part2();
    }
}