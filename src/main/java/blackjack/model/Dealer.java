package blackjack.model;

import java.util.List;

public class Dealer {
    private static final int STANDARD = 16;

    private final Cards cards;

    public Dealer(Cards cards) {
        this.cards = cards;
    }

    public void addCard(CardGenerator cardGenerator) {
        if (isScoreLessThanStandard()) {
            cards.addCard(cardGenerator.drawCard());
        }
    }

    public boolean isScoreLessThanStandard() {
        return cards.calculateScore() <= STANDARD;
    }

    public void addCards(List<Card> cardsToAdd) {
        cards.addCard(cardsToAdd);
    }

    public ResultStatus determineWinner(Player player) {
        return player.compareScore(cards);
    }

    public Cards getCards() {
        return cards;
    }
}
