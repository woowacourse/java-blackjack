package domain.gamer;

import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;
import java.util.List;

public abstract class Gamer {
    public static final int LIMIT = 21;

    private final CardGroup cardGroup;
    private final int battingAmount;

    protected Gamer(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
        this.battingAmount = 0;
    }

    protected Gamer(CardGroup cardGroup, int battingAmount) {
        this.cardGroup = cardGroup;
        this.battingAmount = battingAmount;
    }

    public void giveCard(final Card card) {
        cardGroup.addCard(card);
    }

    public boolean isBust() {
        return this.cardGroup.calculateScore(LIMIT) > LIMIT;
    }

    public GameResult calculateGameResult(final int compareScore) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.findByScores(cardGroup.calculateScore(LIMIT), compareScore);
    }

    public int calculateScore() {
        return cardGroup.calculateScore(LIMIT);
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public int getBattingAmount() {
        return battingAmount;
    }

    public int calculateBattingAmountOfReturn(final int amount) {
        return battingAmount + amount;
    }
}
