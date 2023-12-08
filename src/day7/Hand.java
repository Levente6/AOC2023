package day7;

import javax.security.auth.callback.Callback;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Hand {

    private String hand;
    private HashMap<Character, Integer> composition = null;
    public enum Type{five_of_a_kind, four_of_a_kind, full_house, three_of_a_kind, two_pair, one_pair, high_card}
    private Type type = null;
    private String order = "AKQT98765432J";

    public Hand(String hand) {
        this.hand = hand;
        this.composition = new HashMap<>();
        for (char c : hand.toCharArray()) {
            if(composition.containsKey(c)){
                int i = composition.remove(c);
                composition.put(c,i+1);
            }else{
                composition.put(c,1);
            }
        }
        this.type = getType();
    }

    public Type getType(){
            int max = 0;

        for (Map.Entry<Character, Integer> entry : composition.entrySet()) {
            if(entry.getValue()>max && entry.getKey()!='J') max = entry.getValue();
        }
        if(composition.containsKey('J') && composition.size()>=1){
            max+=composition.remove('J');
        }


            switch (max){
                case 5:
                    type = Type.five_of_a_kind;
                    break;
                case 4:
                    type = Type.four_of_a_kind;
                    break;
                case 3:
                    type = composition.size()==2 ? Type.full_house : Type.three_of_a_kind;
                    break;
                case 2:
                    type = composition.size()==3 ? Type.two_pair : Type.one_pair;
                    break;
                default:
                    type = Type.high_card;
            }
        return type;
    }

    public int compare(Hand other){
        //"AKQJT98765432";

        if(this.type!=other.type){
            return this.type.compareTo(other.type);
        }else{
            int i = 0;
            while(i < hand.length() && hand.charAt(i)==other.hand.charAt(i))i++;
            if(i>=hand.length()) return 0;
            return order.indexOf(hand.charAt(i))-order.indexOf(other.hand.charAt(i));
        }

    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand='" + hand + '\'' +
                ", composition=" + composition +
                ", type=" + type +
                '}';
    }
}
