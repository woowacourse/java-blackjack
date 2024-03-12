package blackjack.domain.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck {
    private static final int PICK_CARD_INDEX = 0;

    private List<Card> cards;

    public Deck(List<Card> cards) {
        Collections.shuffle(cards);
        this.cards = cards;
    }

    public static Deck make() {
        List<Card> cards = Arrays.stream(CardScore.values())
                .flatMap(score -> Arrays.stream(CardSymbol.values())
                        .map(symbol -> new Card(score, symbol)))
                .toList();
        return new Deck(new ArrayList<>(cards));
    }

    public Card pickCard() {
        return cards.remove(PICK_CARD_INDEX);
    }

    public List<Card> pickCards(int count) {
        List<Card> pickCards = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            pickCards.add(cards.remove(PICK_CARD_INDEX));
        }
        return pickCards;
    }
}
