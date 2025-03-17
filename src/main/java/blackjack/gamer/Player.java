package blackjack.gamer;

import blackjack.bettingMachine.BettingMachine;
import blackjack.card.Card;
import blackjack.state.State;
import blackjack.state.UndefinedState;
import java.util.List;

public class Player extends Gamer {

    private final String nickName;
    private final BettingMachine bettingMachine;
    private State state;

    public Player(final String nickName) {
        this.nickName = nickName;
        this.state = new UndefinedState();
        this.bettingMachine = new BettingMachine();
    }

    public void betMoney(final String bettingAmount) {
        bettingMachine.bet(bettingAmount);
    }

    public void winGame() {
        bettingMachine.earnDouble();
    }

    public void drawGame() {
        bettingMachine.earnSingle();
    }

    public void loseGame() {
    }

    public void blackjackGame() {
        bettingMachine.earnOneAndHalf();
    }

    public void pushGame() {
        bettingMachine.earnSingle();
    }

    public long getProfit() {
        return bettingMachine.getProfit();
    }

    public void changeState(final State state) {
        this.state = state;
    }

    public void calculateState(final Dealer dealer) {
        state.checkState(this, dealer);
    }

    @Override
    public List<Card> showInitialCards() {
        return hand.openAllCards();
    }

    @Override
    public String getNickName() {
        return nickName;
    }
}
