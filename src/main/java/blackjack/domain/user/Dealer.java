package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.cardpack.CardPack;
import blackjack.domain.game.GameResult;

import java.util.List;

public class Dealer {

    private static final int MIN_DEALER_SCORE = 16;

    private final Participant participant;

    public Dealer(final String name) {
        this.participant = new Participant(name);
    }

    public GameResult declareGameResult(final int playerScore) {
        Score score = this.getScore();
        int dealerScore = score.getValue();

        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }

        if (playerScore == dealerScore) {
            return GameResult.DRAW;
        }

        return GameResult.LOSE;
    }

    public void drawCard(final CardPack cardPack) {
        participant.drawCard(cardPack);
    }

    private GameResult getDealerResult(final GameResult playerResult) {
        if (playerResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (playerResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public boolean isHitAble() {
        Score score = participant.getScore();
        return (score.getValue() <= MIN_DEALER_SCORE) && !isBust();
    }

    private boolean isBust() {
        return participant.isBust();
    }

    public Score getScore() {
        return participant.getScore();
    }

    public List<Card> showCards() {
        return List.copyOf(participant.showCards());
    }

    public String getName() {
        return participant.getName();
    }
}
