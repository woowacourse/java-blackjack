package domain.gamer;

import domain.Betting;
import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;
import java.util.List;

public abstract class Gamer {

    private final CardGroup cardGroup;
    private Betting betting;

    protected Gamer(CardGroup cardGroup) {
        this.cardGroup = cardGroup;
        this.betting = new Betting(0);
    }

    protected Gamer(CardGroup cardGroup, final Betting betting) {
        this.cardGroup = cardGroup;
        this.betting = betting;
    }

    public void giveCard(final Card card) {
        cardGroup.addCard(card);
    }

    public boolean isBust() {
        return cardGroup.isBust();
    }

    public GameResult calculateGameResult(final int compareScore) {
        if (isBust()) {
            return GameResult.LOSE;
        }
        return GameResult.findByScores(cardGroup.calculateScore(), compareScore);
    }

    public int calculateScore() {
        return cardGroup.calculateScore();
    }

    public boolean isBlackjack() {
        return cardGroup.isBlackjack();
    }

    public List<Card> getCards() {
        return cardGroup.getCards();
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public double calculateBettingScore(final GameResult gameResult, final boolean isBlackjackOnlyPlayer) {
        return betting.calculateBettingOfReturn(gameResult, isBlackjackOnlyPlayer);
    }

    public abstract boolean canReceiveCard();

    public void betAmount(final Betting betting) {
        this.betting = betting;
    }
}
