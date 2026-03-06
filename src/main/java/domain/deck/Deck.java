package domain.deck;

import domain.card.Card;
import domain.card.CardRank;
import domain.card.CardSuit;
import expcetion.BlackjackException;
import expcetion.ExceptionMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Deck implements CardDeck {
    private final List<Card> cards;
    private int index;

    public Deck() {
        this.cards = createCards();
        this.index = 0;
    }

    private List<Card> createCards() {
        List<Card> cards = new ArrayList<>();

        for (CardSuit cardSuit : CardSuit.values()) {
            Arrays.stream(CardRank.values()).forEach(c -> cards.add(new Card(c, cardSuit)));
        }

        Collections.shuffle(cards);
        return cards;
    }

    public void shuffle() {
        Collections.shuffle(cards);
        index = 0;
    }

    @Override
    public Card deal() {
        if (index >= cards.size()) {
            throw new BlackjackException(ExceptionMessage.EMPTY_CARD_DECK);
        }

        return cards.get(index++);
    }
}
