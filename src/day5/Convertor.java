package day5;

import tools.Pair;

import java.util.ArrayList;
import java.util.List;

public class Convertor {
    private String type;
    private ArrayList<Long> sourceStart, destStart, range;

    public Convertor(String type) {
        this.type = type;
        this.sourceStart = new ArrayList<>();
        this.destStart = new ArrayList<>();
        this.range = new ArrayList<>();
    }

    public void addConversionPolicy(String line){
        if(!line.isBlank()){
            String[] numbers = line.split("\s+");
            sourceStart.add(Long.parseLong(numbers[1]));
            destStart.add(Long.parseLong(numbers[0]));
            range.add(Long.parseLong(numbers[2]));
        }
    }

    public long convert(long source){
        int i = 0;
        while(i < sourceStart.size() && !(sourceStart.get(i)<=source && (sourceStart.get(i)+range.get(i)-1)>=source)){
            i++;
        }
        if(i>=sourceStart.size()) return source;
        else{
            //System.out.println(source + ": " + sourceStart.get(i) + "(" + (sourceStart.get(i)+ range.get(i)) + ") ->" + destStart.get(i) );
            //System.out.println(type + ":\n" + source + "->" +(source + (destStart.get(i)-sourceStart.get(i))));
            return source + (destStart.get(i)-sourceStart.get(i));
        }
    }

    @Override
    public String toString() {
        return "Convertor{" +
                "type='" + type + '\'' +
                ", sourceStart=" + sourceStart +
                ", destStart=" + destStart +
                ", range=" + range +
                '}';
    }
}
