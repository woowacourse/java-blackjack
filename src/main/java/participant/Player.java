package participant;

import card.Card;
import java.util.List;
import state.started.Started;

public class Player extends Participant {

    private final String nickname;

    public Player(final String nickname, final State start) {
        super(start);
        this.nickname = nickname;
    }

    public void prepareGame(final Card card1, final Card card2) {
        state = Started.start(card1, card2);
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
