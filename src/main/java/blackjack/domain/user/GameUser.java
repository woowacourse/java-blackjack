package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.value.Nickname;
import java.util.List;

public class GameUser {

    private final Nickname nickname;
    private final Hand hand;

    public GameUser(Nickname nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public void addCardInHand(List<Card> cards) {
        hand.addCard(cards);
    }

    public void addCardInHand(Card card) {
        hand.addCard(card);
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public int getPoint() {
        return hand.calculateTotalPoint();
    }

    public Nickname getNickname() {
        return nickname;
    }
}
