package participant;

import card.Card;
import java.util.List;
import state.finished.Blackjack;
import state.finished.Bust;
import state.started.Started;

public class Player extends Participant {

    private final Nickname nickname;
    private final BettingMoney bettingMoney;

    public Player(Nickname nickname, BettingMoney bettingMoney) {
        this.nickname = nickname;
        this.bettingMoney = bettingMoney;
    }

    public void prepareGame(final Card card1, final Card card2) {
        state = Started.start(card1, card2);
    }

    @Override
    public boolean canReceiveCard() {
        return !state.isFinished();
    }

    public String getNickname() {
        return this.nickname.getNickname();
    }

    public List<Card> cards() {
        return this.state.cards().getCards();
    }
}
