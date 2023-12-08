package day6;

import tools.AOC;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Day6 extends AOC {

    List<Integer> time, distance;

    public Day6() {
        super("./files/" + Day6.class.getPackageName() + ".txt");
        List<String> lines = getStream().toList();

        time = Arrays.stream(lines.get(0).replaceAll("Time", "").trim().split("[\\s\\:]+")).filter(s -> {return !s.isBlank();}).map(Integer::valueOf).collect(Collectors.toList());
        distance = Arrays.stream(lines.get(1).replaceAll("Distance", "").trim().split("[\\s\\:]+")).filter(s -> {return !s.isBlank();}).map(Integer::valueOf).collect(Collectors.toList());

    }

    @Override
    public void part1() {
        int product = 1;
        for (int i = 0; i < distance.size(); i++) {
            int tmp = 1, counter = 0;
            for (tmp = 0; tmp < time.get(i); tmp++) {
                if(tmp*(time.get(i)-tmp)>distance.get(i)){
                    counter++;
                }
            }
            product *= counter;
        }
        System.out.println(product);
    }

    @Override
    public void part2() {
        String tmp = "";
        for (int i : time) {
            tmp += i;
        }
        long timeNumber = Long.valueOf(tmp);
        tmp = "";
        for (int i : distance) {
            tmp += i;
        }
        long distanceNumber = Long.valueOf(tmp);

        int counter = 0;

        for (long i = 1; i < timeNumber; i++) {
            if(i * (timeNumber-i) > distanceNumber) counter++;
        }
        
        System.out.println(counter);

    }

    public static void main(String[] args) {
        Day6 day = new Day6();
        day.part1();
        day.part2();
    }
}