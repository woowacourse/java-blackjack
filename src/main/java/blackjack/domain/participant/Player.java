package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.Money;
import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private final Money money;

    public Player(String name, List<Card> cards, String money) {
        super(name, cards);
        this.money = new Money(money);
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
