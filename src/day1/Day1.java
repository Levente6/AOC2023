package day1;

import tools.AOC;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day1 extends AOC {

    List<String> calibration_value;

    public Day1() {
        super("./files/" + Day1.class.getPackageName() + ".txt");
        calibration_value = getStream().collect(Collectors.toList());
    }


    @Override
    public void part1() {
        int product = calibration_value.stream().map(s -> {
            char first_digit =' ', last_digit =' ';
            for (int i = 0; i < s.length(); i++) {
                if(Character.isDigit(s.charAt(i)) && first_digit==' '){
                    first_digit = s.charAt(i);
                }
                if(Character.isDigit(s.charAt(s.length()-i-1)) && last_digit==' '){
                    last_digit = s.charAt(s.length()-1-i);
                }
            }

            return Integer.parseInt(first_digit+""+last_digit);
        }).mapToInt(Integer::intValue).sum();
        System.out.println(product);
    }

    @Override
    public void part2() {
        int product = calibration_value.stream().map(s -> {
            String tmp = s;
            int first = 0, last = 0, holder = 0;

            while(!tmp.isBlank()){
                if(tmp.startsWith("one")){
                    holder = 1;
                } else if (tmp.startsWith("two")) {
                    holder = 2;
                } else if (tmp.startsWith("three")) {
                    holder = 3;
                }else if (tmp.startsWith("four")) {
                    holder = 4;
                }else if (tmp.startsWith("five")) {
                    holder = 5;
                }else if (tmp.startsWith("six")) {
                    holder = 6;
                }else if (tmp.startsWith("seven")) {
                    holder = 7;
                }else if (tmp.startsWith("eight")) {
                    holder = 8;
                }else if (tmp.startsWith("nine")) {
                    holder = 9;
                } else if(Character.isDigit(tmp.charAt(0))){
                    holder = Integer.parseInt(tmp.charAt(0)+"");
                }
                first = first==0 ? holder : first;
                last = holder;
                tmp = tmp.substring(1);
            }
            System.out.println(s + ":" +first + " " + last);

            return (first*10)+last;
        }).mapToInt(Integer::intValue).sum();

        System.out.println(product);

    }

    public static void main(String[] args) {
        Day1 day1 = new Day1();
        //day1.part1();
        day1.part2();
    }
}