package day4;

import tools.AOC;

import java.util.*;
import java.util.stream.Collectors;

public class Day4 extends AOC {


    List<String> cards;

    public Day4() {
        super("./files/" + Day4.class.getPackageName() + ".txt");

        cards = getStream().toList();
    }

    private int evaluateCard(String card){
        List<Integer> winning = Arrays.stream(card.split("[\\:\\|]")[1].trim().split("\s+")).map(Integer::valueOf).collect(Collectors.toList());
        List<Integer> hand = Arrays.stream(card.split("[\\:\\|]")[2].trim().split("\s+")).map(Integer::valueOf).collect(Collectors.toList());
        int points = 0;
        for (Integer number : hand) {
            if(winning.contains(number)){
                points++;
            }
        }
        return points;
    }

    @Override
    public void part1() {
        int worth = cards.stream().mapToInt(s -> {
            int points = evaluateCard(s);
            return (points>0) ? (int)Math.pow(2,points-1):0;
        }).sum();
        System.out.println(worth);
    }

    @Override
    public void part2() {
        int[] copies = new int[cards.size()];
        for (int i = 0; i < copies.length; i++) {
            copies[i]=1;
        }

        for (int i = 0; i < copies.length; i++) {
            int length = evaluateCard(cards.get(i));
            for (int j = i+1; j <= i+length; j++) {
                copies[j]+=copies[i];
            }
        }

        int product = Arrays.stream(copies).sum();
        System.out.println(product);
    }

    public static void main(String[] args) {
        Day4 day = new Day4();
        day.part1();
        day.part2();
    }
}