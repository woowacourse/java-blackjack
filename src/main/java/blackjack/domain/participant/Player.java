package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.BattingMoney;
import java.util.List;

public class Player extends Participant {

    public Player(final String name, final String battingMoney, final List<Card> card) {
        super(name, new BattingMoney(battingMoney), card);
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    @Override
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public List<Card> getCards() {
        return state.cards().values();
    }
}
