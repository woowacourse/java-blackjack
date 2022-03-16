package blackjack.domain.participant;

import static blackjack.domain.BettingResult.LOSE;
import static blackjack.domain.BettingResult.WIN;
import static blackjack.domain.BettingResult.WIN_BY_BLACKJACK;

import blackjack.domain.BettingResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final BettingMoney bettingMoney;

    public Player(String name, List<Card> cards, String money) {
        super(name, cards);
        this.bettingMoney = new BettingMoney(money);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() < BLACKJACK_SCORE;
    }

    public int calculateBettingMoney(Participant participant) {
        if (isBust()) {
            return LOSE.getResult(bettingMoney);
        }

        if (participant.isBust()) {
            return WIN.getResult(bettingMoney);
        }

        if (isBlackjack() && !participant.isBlackjack()) {
            return WIN_BY_BLACKJACK.getResult(bettingMoney);
        }

        return BettingResult.of(getTotalScore(), participant.getTotalScore()).getResult(bettingMoney);
    }
}
