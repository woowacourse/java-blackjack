package blackjack.domain.participant;

import blackjack.domain.BattingMoney;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final BattingMoney bettingMoney;

    public Player(String name, List<Card> cards, String money) {
        super(name, cards);
        this.bettingMoney = new BattingMoney(money);
    }

    @Override
    public boolean isDrawable() {
        return getTotalScore() <= BLACKJACK_SCORE;
    }

    @Override
    public GameResult decideResult(Participant participant) {
        if (isBust()) {
            return GameResult.LOSE;
        }

        if (participant.isBust()) {
            return GameResult.WIN;
        }

        return GameResult.of(getTotalScore(), participant.getTotalScore());
    }
}
