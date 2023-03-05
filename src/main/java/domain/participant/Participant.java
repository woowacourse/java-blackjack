package domain.participant;

import domain.Deck;
import domain.GamePoint;
import domain.card.Card;
import domain.card.Cards;

import java.util.Collections;
import java.util.List;

public abstract class Participant {

    private static final int INITIAL_CARD_COUNT = 2;
    protected static final String DEALER_NAME = "딜러";

    private final Name name;
    protected Cards cards;

    protected Participant(Name name) {
        this.name = name;
        this.cards = Cards.create(Collections.emptyList());
    }

    protected Participant(Name name, Cards cards) {
//        validateCardsSize(cards.size());
        this.name = name;
        this.cards = cards;
    }

    private void validateCardsSize(final int size) {
        if (size != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("유저는 카드 2장 이상을 갖고 있어야 합니다.");
        }
    }
    public void takeCard(final Card card) {
        this.cards = cards.add(card);
    }

    public void takeCard(final Deck deck, final int count) {
        for (int i = 0; i < count; i++) {
            this.cards = cards.add(deck.drawCard());
        }
    }

    public List<Card> getCard() {
        return List.copyOf(cards.getCards());
    }

    public GamePoint calculatePoint() {
        return cards.getGamePoint();
    }

    public boolean isBust() {
        return cards.isBusted();
    }

    public Name getName() {
        return name;
    }
}
