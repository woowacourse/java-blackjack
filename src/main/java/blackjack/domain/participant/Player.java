package blackjack.domain.participant;

import static blackjack.domain.BattingResult.LOSE;
import static blackjack.domain.BattingResult.WIN;
import static blackjack.domain.BattingResult.WIN_BY_BLACKJACK;

import blackjack.domain.BattingMoney;
import blackjack.domain.BattingResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final BattingMoney battingMoney;

    public Player(String name, List<Card> cards, String money) {
        super(name, cards);
        this.battingMoney = new BattingMoney(money);
    }

    @Override
    public boolean canHit() {
        return getTotalScore() < BLACKJACK_SCORE;
    }

    public int calculateBattingMoney(Participant participant) {
        if (isBust()) {
            return LOSE.getResult(battingMoney);
        }

        if (participant.isBust()) {
            return WIN.getResult(battingMoney);
        }

        if (isBlackjack() && !participant.isBlackjack()) {
            return WIN_BY_BLACKJACK.getResult(battingMoney);
        }

        return BattingResult.of(getTotalScore(), participant.getTotalScore()).getResult(battingMoney);
    }
}
