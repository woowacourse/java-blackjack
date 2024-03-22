package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;

public class CardFactory {

    public List<Card> createBlackJackCard() {
        List<Card> cards = new ArrayList<>();

        for (CardSuit suit : CardSuit.values()) {
            for (CardNumber number : CardNumber.values()) {
                cards.add(new Card(suit, number));
            }
        }
        return cards;
    }
}
