import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        Card card = new Card();

//        List<String> cards = new ArrayList<>(List.of("A","1"));
//        List<String> cards = new ArrayList<>(List.of("A","1","2"));
//        List<String> cards = new ArrayList<>(List.of("A","1","9"));

//        List<String> cards = new ArrayList<>(List.of("1","9","10"));
//        List<String> cards = new ArrayList<>(List.of("A","A","9","10"));
        List<String> cards = new ArrayList<>(List.of("A","A","A"));

        int total = card.sumNumbers(cards);
        int finalTotal = card.filterAce(total, cards.contains("A"));

        System.out.println("Total number of cards: " + finalTotal);
    }



}
