package blackjack.model.dealer;

import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;
import blackjack.vo.Card;
import java.util.List;

public class Dealer {
    private static final int MAX_DRAWABLE_SCORE = 16;
    private static final int NON_DRAW_COUNT = 2;

    private final Cards cards;

    public Dealer() {
        this.cards = new Cards();
    }

    public void dealCards(final CardGenerator cardGenerator) {
        List<Card> receivedCards = List.of(cardGenerator.pick(), cardGenerator.pick());
        cards.addCards(receivedCards);
    }

    public void drawCards(final CardGenerator cardGenerator) {
        while (canDrawCard()) {
            cards.addCard(cardGenerator.pick());
        }
    }

    private boolean canDrawCard() {
        return cards.canAddCardWithinScoreLimit(MAX_DRAWABLE_SCORE);
    }

    public int calculateCardsScore() {
        return cards.calculateScore();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public int getDrawCount() {
        return cards.getSize() - NON_DRAW_COUNT;
    }

    public Card getFirstCard() {
        return cards.findByIndex(0);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
