package blackjack.domain.participant;

import java.util.List;
import blackjack.domain.card.Card;

public class Dealer extends Participant {

    private static final int STAND_BOUND = 17;
    private static final int HIDDEN_CARD_SIZE = 1;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public JudgeResult judgePlayer(Player player) {
        int playerScore = player.calculateScore();
        int dealerScore = calculateScore();

        if (player.isBust()) {
            return JudgeResult.LOSE;
        }

        if (isBust() || playerScore > dealerScore) {
            return JudgeResult.WIN;
        }

        if (player.isBlackJack() && isBlackJack()) {
            return JudgeResult.TIE;
        }

        if (player.isBlackJack()) {
            return JudgeResult.BLACKJACK_WIN;
        }

        if (isBlackJack() || playerScore < dealerScore) {
            return JudgeResult.LOSE;
        }

        return JudgeResult.TIE;
    }

    public List<Card> getOpenCards() {
        List<Card> allCards = getCards();

        return allCards.subList(HIDDEN_CARD_SIZE, allCards.size());
    }

    @Override
    public boolean isPlayable() {
        int score = calculateScore();

        return score < STAND_BOUND;
    }
}
