package domain.deck;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import exception.BlackjackException;
import exception.ExceptionMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class StandardDeck implements Deck {

    private final List<Card> cards;
    private int index;

    public StandardDeck() {
        this.cards = new ArrayList<>();
        this.index = 0;
        for (CardSuit cardSuit : CardSuit.values()) {
            Arrays.stream(CardRank.values())
                    .forEach(c -> cards.add(new Card(c, cardSuit)));
        }
        Collections.shuffle(this.cards);
    }

    @Override
    public Card deal() {
        if (index >= cards.size()) {
            throw new BlackjackException(ExceptionMessage.EMPTY_CARD_DECK);
        }

        return cards.get(index++);
    }
}
