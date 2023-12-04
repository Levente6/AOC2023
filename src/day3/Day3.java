package day3;

import tools.AOC;
import tools.Pair;

import java.util.*;

public class Day3 extends AOC {

    HashMap<Pair<Integer>, Integer> part_numbers;
    List<char[]> schematic = null;

    public Day3() {
        super("./files/" + Day3.class.getPackageName() + ".txt");
        part_numbers = new HashMap<>();

        schematic = getStream().map(String::toCharArray).toList();

        for (int i = 0; i < schematic.size(); i++) {
            String number = "";
            for (int j = 0; j < schematic.get(i).length; j++) {
                if(Character.isDigit(schematic.get(i)[j])){
                    number += schematic.get(i)[j];
                }else if(!Character.isDigit(schematic.get(i)[j]) && !number.isBlank()){
                    if(isAdjacent(new Pair<>(i, j-number.length()), Integer.parseInt(number))){
                        part_numbers.put(new Pair<>(i, j-number.length()), Integer.parseInt(number));
                    }
                    number="";
                }
            }
            if(!number.isBlank()){
                if (isAdjacent(new Pair<>(i, schematic.get(i).length-number.length()), Integer.parseInt(number))){
                    part_numbers.put(new Pair<>(i, schematic.get(i).length-number.length()), Integer.parseInt(number));
                }
            }
        }
    }

    private boolean isAdjacent(Pair<Integer> pair, int number){
        int x = pair.getOne();
        int y = pair.getTwo();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j < (number+"").length()+1; j++) {
                if((x+i>=0 && x+i<schematic.size()) && (y+j>=0 && y+j<schematic.get(0).length)){
                    if(!Character.isDigit(schematic.get(x+i)[y+j]) && schematic.get(x+i)[y+j]!='.'){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public void part1() {
        int product = 0;
        for (Map.Entry<Pair<Integer>, Integer> entry : part_numbers.entrySet()){
                product += entry.getValue();
        }
        System.out.println(product);
    }



    @Override
    public void part2() {
        HashMap<Pair<Integer>, ArrayList<Integer>> valid_gears = new HashMap<>();
        //System.out.println(part_numbers);
        for (Map.Entry<Pair<Integer>, Integer> entry : part_numbers.entrySet()){
            int x = entry.getKey().getOne();
            int y = entry.getKey().getTwo();
            for (int i = -1; i <= 1; i++){
                for (int j = -1; j <= (entry.getValue()+"").length(); j++) {
                    if((x+i>=0 && x+i<schematic.size()) && (y+j>=0 && y+j<schematic.get(0).length)){
                        if(schematic.get(x+i)[y+j]=='*'){
                            Pair p = new Pair<>((x+i),(y+j));
                            ArrayList<Integer> tmp = new ArrayList<>();
                            if(!valid_gears.containsKey(p)){
                                tmp.add(entry.getValue());
                                valid_gears.put(p, tmp);
                            }else{
                                valid_gears.get(p).add(entry.getValue());
                            }
                        }
                    }
                }
            }
        }
        
        int product = 0;
        for (Map.Entry<Pair<Integer>, ArrayList<Integer>> entry : valid_gears.entrySet()){
            if(entry.getValue().size()==2){
                product += (entry.getValue().get(0)*entry.getValue().get(1));
            }
        }
        System.out.println(product);

    }

    public static void main(String[] args) {
        Day3 day = new Day3();
        day.part1();
        day.part2();
    }
}