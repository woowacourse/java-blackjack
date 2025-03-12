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

    @Override
    public List<Card> showInitialCards() {
        return hand.openAllCards();
    }

    @Override
    public String getNickName() {
        return nickName;
    }
}
