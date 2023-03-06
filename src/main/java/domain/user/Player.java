package domain.user;

import domain.card.Card;
import java.util.List;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;
    private boolean isWinner = false;

    public Player(Name name) {
        super();
        this.name = name;
    }

    @Override
    public void receiveCard(Card card) {
        super.receiveCard(card);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void receiveCards(List<Card> receivedCards) {
        super.receiveCards(receivedCards);
        status = score.calculatePlayerStatus();
    }

    @Override
    public void win(User dealer) {
        isWinner = true;
        dealer.lose();
    }

    @Override
    public void lose() {
        isWinner = false;
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name.getName();
    }

    public boolean isWinner() {
        return isWinner;
    }
}
