package blackjack.domain.participant.state;

import blackjack.domain.carddeck.Card;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InitDraw {

    public static final int BLACKJACK = 21;

    public static State draw(final Card firstCard, final Card secondCard) {
        List<Card> cards = new ArrayList<>(Arrays.asList(firstCard, secondCard));
        if (isBlackjack(cards)) {
            return new Blackjack(cards);
        }
        return new Hit(cards);
    }

    private static boolean isBlackjack(List<Card> cards) {
        return cards.stream()
            .mapToInt(Card::getScore)
            .sum()
            == BLACKJACK;
    }
}
