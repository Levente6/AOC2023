package day9;

import tools.AOC;

import java.util.*;
import java.util.stream.Collectors;

public class Day9 extends AOC {

    List<List<Integer>> history;

    public Day9() {
        super("./files/" + Day9.class.getPackageName() + ".txt");

        history = getStream().map(s -> {
            return Arrays.stream(s.split(" ")).map(Integer::valueOf).toList();
        }).collect(Collectors.toList());
    }

    private int getNextElement(List<Integer> sequence){
        Set<Integer> differences = new HashSet<>(sequence);
        List<Integer> tmp = new ArrayList<>();

        for (int i = 1; i < sequence.size(); i++) {
            tmp.add(sequence.get(i)-sequence.get(i-1));
        }
        System.out.println(sequence + ": " + differences);
        if(differences.size()>1){
            return sequence.get(sequence.size()-1)+getPreviousElement(tmp);
        }else{
            return differences.stream().findFirst().get();
        }
    }


    @Override
    public void part1() {
        int sum = 0;

        for (List<Integer> record : history) {
            sum += getNextElement(record);
        }

        System.out.println(sum);
    }


    private int getPreviousElement(List<Integer> sequence){
        Set<Integer> differences = new HashSet<>(sequence);
        List<Integer> tmp = new ArrayList<>();

        for (int i = 1; i < sequence.size(); i++) {
            tmp.add(sequence.get(i)-sequence.get(i-1));
        }
        if(differences.size()>1){
            return sequence.get(0)-getPreviousElement(tmp);
        }else{
            return differences.stream().findFirst().get();
        }
    }

    @Override
    public void part2() {
        int sum = 0;

        for (List<Integer> record : history) {
            sum+=getPreviousElement(record);
        }

        System.out.println(sum);
    }

    public static void main(String[] args) {
        Day9 day = new Day9();
        day.part1();
        day.part2();
    }
}