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
        if (isBlackjackWin(player)) {
            return GameResult.BLACKJACK_WIN;
        }
        if (isWin(player)) {
            return GameResult.WIN;
        }
        if (isLoose(player)) {
            return GameResult.LOOSE;
        }
        return GameResult.PUSH;
    }

    private boolean isBlackjackWin(Player player) {
        return player.isBlackjack() && isNotBlackjack();
    }

    private boolean isWin(Player player) {
        return player.isNotBurst() && (isBurst() || player.score() > score());
    }

    private boolean isLoose(Player player) {
        if (isBlackjack() && player.isNotBlackjack() || player.isBurst()) {
            return true;
        }
        return isNotBurst() && player.score() < score();
    }

    public Card getFirstCard() {
        return getCards().get(0);
    }
}
