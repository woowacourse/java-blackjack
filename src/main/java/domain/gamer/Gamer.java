package domain.gamer;

import domain.AnswerCommand;
import domain.Betting;
import domain.GameResult;
import domain.card.Card;
import domain.card.CardGroup;
import java.util.List;

public abstract class Gamer {

    private final CardGroup cardGroup;
    private final Betting betting;

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

    public List<Card> getCards() {
        return cardGroup.getCards();
    }

    public CardGroup getCardGroup() {
        return cardGroup;
    }

    public boolean canReceiveCard(final AnswerCommand answerCommand) {
        if (!answerCommand.isYes()) {
            return false;
        }
        return !(isBust() && cardGroup.isBlackjack());
    }
}
