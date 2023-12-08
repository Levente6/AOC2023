package day7;

import tools.AOC;

import java.util.Map;
import java.util.TreeMap;

public class Day7 extends AOC {

    private TreeMap<Hand, Integer> lines;

    public Day7() {
        super("./files/" + Day7.class.getPackageName() + ".txt");

        lines = new TreeMap<>(Hand::compare);

        getStream().forEach(s -> {
            String[] tmp = s.split(" ");
            lines.put(new Hand(tmp[0]), Integer.parseInt(tmp[1]));
        });
    }

    @Override
    public void part1() {
        int rank = lines.size();
        long total = 0;
        for (Map.Entry<Hand, Integer> entry : lines.entrySet()) {
            total += entry.getValue()*rank;
            rank--;
        }
        System.out.println(total);
    }

    @Override
    public void part2() {
        for (Map.Entry<Hand, Integer> entry : lines.entrySet()) {
            System.out.println(entry);
        }
    }

    public static void main(String[] args) {
        Day7 day = new Day7();
        day.part1();
        day.part2();
    }
}