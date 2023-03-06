package domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    private static final List<Card> cards = new ArrayList<>();

    static {
        Arrays.stream(Rank.values())
                .flatMap(rank -> Arrays.stream(Suit.values())
                        .map(suit -> new Card(rank, suit)))
                .forEach(cards::add);
    }

    private Cards() {
    }

    public static List<Card> getInitCards() {
        return new ArrayList<>(cards);
    }
}
