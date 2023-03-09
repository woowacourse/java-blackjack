package blackjackgame.domain.user;

import blackjackgame.domain.card.Card;
import java.util.List;

public class Player extends User {
    private final Name name;
    private PlayerStatus status = PlayerStatus.NORMAL;

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

    public boolean isLessThanBustScore() {
        return score.isLessThanBustScore();
    }

    @Override
    public UserStatus getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return name.getName();
    }
}
