package day2;

import tools.AOC;

import java.util.List;

public class Day2 extends AOC {

    List<String> games = null;

    private int red, green, blue;

    public Day2() {
        super("./files/" + Day2.class.getPackageName() + ".txt");
        games = getStream().toList();
        red = 12; green = 13; blue = 14;
    }

    private boolean eval(String cube){
        int number = Integer.parseInt(cube.split(" ")[0]);
        String color = cube.split(" ")[1];
        switch (color){
            case "red":
                return red>=number;
            case "green":
                return green>=number;
            default:
                return blue>=number;
        }
    }

    @Override
    public void part1() {
        int product = games.stream().mapToInt(s -> {
            String[] tmp = s.split("[:,;]\\s");
            int game = Integer.parseInt(tmp[0].split(" ")[1]);
            for (int i = 1; i < tmp.length; i++) {
                if(!eval(tmp[i])){
                    return 0;
                }
            }
            return game;
        }).sum();

        System.out.println(product);
    }

    @Override
    public void part2() {
        int product = games.stream().mapToInt(s -> {
            String[] tmp = s.split("[:,;]\\s");
            int red = 0, green = 0, blue = 0;

            for (int i = 1; i < tmp.length; i++) {
                int number = Integer.parseInt(tmp[i].split(" ")[0]);
                String color = tmp[i].split(" ")[1];
                switch (color){
                    case "red":
                        if(red<number) red = number;
                        break;
                    case "green":
                        if (green<number) green=number;
                        break;
                    default:
                        if(blue<number) blue = number;
                        break;
                }
            }
            return red*green*blue;
        }).sum();

        System.out.println(product);

    }

    public static void main(String[] args) {
        Day2 day = new Day2();
        day.part1();
        day.part2();
    }
}