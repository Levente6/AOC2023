package day5;

import tools.AOC;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day5 extends AOC {

    List<Long> seeds = null;
    List<Convertor> convertors = null;

    public Day5() {
        super("./files/" + Day5.class.getPackageName() + ".txt");

        convertors = new ArrayList<>();

        List<String> lines = getStream().filter(s -> !s.isBlank()).toList();
        seeds = Arrays.stream(lines.get(0).replaceAll("seeds:","").trim().split(" ")).map(Long::valueOf).collect(Collectors.toList());
        Convertor tmp = null;
        for (String s : lines) {
            if(s.matches("\\w+\\-to-\\w+\\smap:")){
                if(tmp!=null){
                    convertors.add(tmp);
                }
                tmp = new Convertor(s.replaceAll(":",""));
            }else if(s.matches("\\d+\\s\\d+\\s\\d+")){
                tmp.addConversionPolicy(s);
            }
        }
        convertors.add(tmp);
    }

    @Override
    public void part1() {
        long lowest = Long.MAX_VALUE;

        for (Long seed : seeds) {
            long tmp = seed;
            for (Convertor convertor: convertors) {
                tmp = convertor.convert(tmp);
            }
            if(tmp<lowest) lowest = tmp;
        }

        System.out.println(lowest);
    }

    @Override
    public void part2() {
        //This is shameless bruteforcing
        /*long lowest = Long.MAX_VALUE;

        for (int i = 0; i < seeds.size(); i+=2) {
            for (long j = 0; j <= seeds.get(i+1); j++) {
                long tmp = seeds.get(i)+j;
                for (Convertor convertor: convertors) {
                    tmp = convertor.convert(tmp);
                }
                if(tmp<lowest) lowest = tmp;
            }
        }

        System.out.println(lowest);*/



    }

    public static void main(String[] args) {
        Day5 day = new Day5();
        day.part1();
        day.part2();
    }
}