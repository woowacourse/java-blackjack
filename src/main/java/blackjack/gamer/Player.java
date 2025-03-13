package blackjack.gamer;

import blackjack.card.Card;
import java.util.List;

public class Player extends Gamer {

    private final String nickName;

    public Player(final String nickName) {
        this.nickName = nickName;
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

    @Override
    public List<Card> showInitialCards() {
        return hand.openAllCards();
    }

    @Override
    public String getNickName() {
        return nickName;
    }
}
