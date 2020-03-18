package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer implements Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;

    private Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public int addedCardCount() {
        return cards.addedCardCount();
    }

    @Override
    public String getName() {
        return "딜러";
    }

    @Override
    public void draw(Deck deck) {
        cards.add(deck.pop());
    }

    // 테스트용
    public void draw(Card card) {
        cards.add(card);
    }

    @Override
    public int score() {
        return cards.calculate();
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck);
        }
    }

    private boolean needsMoreCard() {
        return cards.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }

    @Override
    public String cards() {
        return cards.toString();
    }
}
