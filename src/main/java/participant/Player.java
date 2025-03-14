package participant;

import card.Card;
import card.Cards;
import java.util.List;
import state.State;

public class Player extends Participant {

    private final String nickname;

    public Player(final String nickname, final State start) {
        super(start);
        this.nickname = nickname;
    }

    @Override
    public boolean canReceiveCard() {
        return !state.isFinished();
    }

    public String getNickname() {
        return this.nickname;
    }

    public List<Card> cards() {
        return this.state.cards().getCards();
    }
}
