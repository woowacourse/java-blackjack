package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cards {
    public static final int GAME_OVER_SCORE = 21;
    private final List<Card> cards;

    public Cards(Card... cards) {
        this(Arrays.asList(cards));
    }

    public Cards(List<Card> cards) {
        this.cards = new ArrayList<>(cards);
    }

    private int scores() {
        return cards.stream()
                .mapToInt(Card::score)
                .sum();
    }

    public boolean isBlackjack() {
        return scores() == GAME_OVER_SCORE;
    }
}
