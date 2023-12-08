package day8;

import tools.AOC;

import java.util.*;
import java.util.stream.Collectors;

public class Day8 extends AOC {

    String sequence;

    HashMap<String, List<String>> tree;

    public Day8() {
        super("./files/" + Day8.class.getPackageName() + ".txt");

        tree = new HashMap<>();

        getStream().filter(s -> !s.isBlank()).forEach(s -> {
            if(s.matches("\\w{3} = \\(\\w{3}, \\w{3}\\)")){
                String tmp = s;
                String[] nodes = tmp.substring(0, tmp.length()-1).split("[^\\w]+");
                List<String> children = new ArrayList<>(2);

                children.add(nodes[1]);
                children.add(nodes[2]);
                tree.put(nodes[0], children);
            }else{
                sequence = s;
            }
        });
    }

    @Override
    public void part1() {

        String starting_node = "AAA";
        String end_node = "ZZZ";

        System.out.println(getSteps(starting_node, end_node));
    }

    private List<String> getPoints(String regex){
        return tree.keySet().stream().filter(s -> s.matches(regex)).collect(Collectors.toList());
    }

    private int getSteps(String starting, String ending){
        int step_count = 0;

        while(!starting.matches(ending)){
            switch (sequence.charAt(step_count%sequence.length())){
                case 'R':
                    starting = tree.get(starting).get(1);
                    break;
                case 'L':
                    starting = tree.get(starting).get(0);
                    break;
                default:
                    System.out.println("Something went wrong");
                    break;
            }
            step_count++;
        }

        return step_count;
    }

    private long lcm(long a, long b){
        int i = 1;
        long max = Math.max(a,b);
        long min = Math.min(a,b);
        while((max*i)%min!=0)i++;
        return (long)(max*i);
    }

    @Override
    public void part2() {
        List<String> starting_points = getPoints("\\w\\wA");
        ArrayList<Integer> steps = new ArrayList<>();
        for (String current_point : starting_points) {
            String point = current_point;

            steps.add(getSteps(current_point, "\\w\\wZ"));
        }
        long step_count = 1;
        for (int i = 0; i < steps.size(); i++) {
            //System.out.println(step_count);
            step_count = lcm(step_count, steps.get(i));
        }

        System.out.println(step_count);
    }

    public static void main(String[] args) {
        Day8 day = new Day8();
        day.part1();
        day.part2();
    }
}