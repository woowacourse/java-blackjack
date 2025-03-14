package blackjack.gamer;

import blackjack.bettingMachine.BettingMachine;
import blackjack.card.Card;
import java.util.List;

public class Player extends Gamer {

    private final String nickName;
    private final BettingMachine bettingMachine;

    public Player(final String nickName) {
        this.nickName = nickName;
        this.bettingMachine = new BettingMachine();
    }

    public void bet(final String bettingAmount) {
        bettingMachine.bet(bettingAmount);
    }

    public void win() {
        bettingMachine.earnDouble();
    }

    public void draw() {
        bettingMachine.earnSingle();
    }

    public void lose() {
    }

    public void blackjack() {
        bettingMachine.earnOneAndHalf();
    }

    public void push() {
        bettingMachine.earnSingle();
    }

    public long getProfit() {
        return bettingMachine.getProfit();
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
