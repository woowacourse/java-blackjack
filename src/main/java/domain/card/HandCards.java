package domain.card;

import domain.CardPossessor;
import domain.CardProvider;

import java.util.ArrayList;
import java.util.List;

public class HandCards implements CardPossessor {
    private final List<Card> cards;

    public HandCards() {
        this.cards = new ArrayList<>();
    }

    public HandCards(List<Card> cards) {
        this.cards = cards;
    }

    @Override
    public void drawCard(CardProvider cardProvider) {
        cards.add(cardProvider.giveCard());
    }

    @Override
    public int calculateScore() {
        return 0;
    }

    @Override
    public int getCardAmount() {
        return cards.size();
    }

    public List<Card> getCards() {
        return cards;
    }
}
