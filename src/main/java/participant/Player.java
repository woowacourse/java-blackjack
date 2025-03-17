package participant;

import card.Card;
import java.util.List;
import state.finished.Blackjack;
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

    public int calculateProfit(GameResult result) {
        if (result == GameResult.WIN) {
            return -(int) state.profit(bettingMoney.getMoney());
        }

        if (result == GameResult.LOSE) {
            return (int) state.profit(bettingMoney.getMoney());
        }

        return 0;
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

    public void stand() {
        this.state = state.stand();
    }

    public boolean isBlackjack() {
        return state instanceof Blackjack;
    }

    public boolean isFinished() {
        return state.isFinished();
    }
}
