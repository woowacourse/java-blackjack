package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.game.GameRule;
import blackjack.exception.ExceptionMessage;
import java.util.List;

public class Player {

    private final Nickname nickname;
    private final Hand hand;

    public Player(Nickname nickname) {
        this.nickname = nickname;
        this.hand = new Hand();
    }

    public void addInitialCards(List<Card> cards) {
        hand.addCard(cards);
    }

    public void hit(Card card) {
        validateHitPossibility();
        hand.addCard(card);
    }

    public boolean checkHitPossibility() {
        return GameRule.checkPossibilityOfHit(hand.calculateTotalPoint());
    }

    public List<Card> getHand() {
        return hand.getCards();
    }

    public int getPoint() {
        return hand.calculateTotalPoint();
    }

    public String getNickname() {
        return nickname.getValue();
    }

    private void validateHitPossibility() {
        if (!checkHitPossibility()) {
            throw new IllegalArgumentException(ExceptionMessage.CANNOT_HIT.getContent());
        }
    }
}
