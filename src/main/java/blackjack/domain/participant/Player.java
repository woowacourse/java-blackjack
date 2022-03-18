package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.game.BettingMoney;
import blackjack.domain.participant.vo.ParticipantName;
import blackjack.domain.state.State;
import java.util.List;

public class Player extends Participant {

    private Player(final ParticipantName name, final State state) {
        super(name, state);
    }

    public Player(final String name, final String battingMoney, final List<Card> card) {
        super(new ParticipantName(name), new BettingMoney(battingMoney), card);
    }

    public Player stay() {
        state = state.stay();
        return new Player(name, state);
    }

    public Player draw(final Card card) {
        state = state.draw(card);
        return new Player(name, state);
    }

    @Override
    public boolean canDraw() {
        return !state.isFinished();
    }

    @Override
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public List<Card> getCards() {
        return state.cards().getValues();
    }
}
