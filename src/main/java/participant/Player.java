package participant;

import betting.BettingMoney;
import card.Card;
import java.util.List;
import state.State;
import state.started.Started;

public class Player {

    private final Nickname nickname;

    private State state;
    private BettingMoney bettingMoney;

    public Player(Nickname nickname, BettingMoney bettingMoney) {
        this.bettingMoney = bettingMoney;
        this.nickname = nickname;
    }

    public void bet(int bettingMoney) {
        this.bettingMoney = new BettingMoney(bettingMoney);
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

    public void stand() {
        this.state = state.stand();
    }

    public boolean isBlackjack() {
        return state.isBlackjack();
    }

    public boolean isBust() {
        return state.isBlackjack();
    }

    public boolean isFinished() {
        return state.isFinished();
    }

    public void hit(Card card) {
        this.state.draw(card);
    }

    public int score() {
        return this.state.cards().calculateScore();
    }

    public String getNickname() {
        return this.nickname.getNickname();
    }

    public List<Card> cards() {
        return this.state.cards().getCards();
    }
}
