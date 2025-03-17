package blackjack.domain.player;

import blackjack.domain.card.Cards;

public class Hand {

    private static final int BLACKJACK_SCORE = 21;

    private final Cards cards;
    private final int betAmount;

    public Hand(final int betAmount) {
        this.cards = new Cards();
        this.betAmount = betAmount;
    }

    public void addCards(final Cards newCards) {
        this.cards.addCard(newCards);
    }

    public int calculateScore() {
        int totalScore = cards.sumCardScore();
        long aceCardCount = cards.countAceCard();

        while (aceCardCount > 0 && totalScore + 10 <= BLACKJACK_SCORE) {
            totalScore += 10;
            aceCardCount--;
        }
        return totalScore;
    }

    public int calculateWinningAmount(final double multiplier) {
        return (int) (betAmount * multiplier);
    }

    public boolean isBlackJack() {
        return calculateScore() == BLACKJACK_SCORE && cards.isBlackJackSize();
    }

    public Cards getCards() {
        return cards;
    }

    public int getBetAmount() {
        return betAmount;
    }
}
