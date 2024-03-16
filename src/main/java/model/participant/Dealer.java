package model.participant;

import java.util.List;
import model.card.Card;
import model.card.Cards;
import model.game.GameResult;
import model.game.action.JudgeAction;

public class Dealer extends Participant implements JudgeAction {

    private static final int HIT_CONDITION = 17;

    public Dealer() {
        this(List.of());
    }

    public Dealer(List<Card> cards) {
        super(Name.createDealerName(), new Cards(cards));
    }

    @Override
    public boolean isPossibleHit() {
        return score() < HIT_CONDITION;
    }

    @Override
    public GameResult judge(Player player) {
        int playerScore = player.score();
        int dealerScore = score();

        if (player.isBlackjack() && isNotBlackjack()) {
            return GameResult.BLACKJACK_WIN;
        }
        if (player.isBlackjack() && isBlackjack()) {
            return GameResult.PUSH;
        }
        if (player.isBurst()) {
            return GameResult.LOOSE;
        }
        if (isBurst()) {
            return GameResult.WIN;
        }
        if (player.isNotBurst() && playerScore > dealerScore) {
            return GameResult.WIN;
        }
        if (isNotBurst() && playerScore < dealerScore) {
            return GameResult.LOOSE;
        }
        return GameResult.PUSH;
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
