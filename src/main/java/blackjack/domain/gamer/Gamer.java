package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;

import java.util.List;

// TODO : 딜러가 플레이어를 컴포지션하도록 변경
public class Gamer {
    protected final CardHand cardHand;

    protected Gamer(CardHand cardHand) {
        this.cardHand = cardHand;
    }

    public void receiveInitCards(List<Card> cards) {
        cardHand.add(cards);
    }

    public void receiveCard(Card card) {
        cardHand.add(card);
    }

    public int getScore() {
        return cardHand.calculateScore();
    }

    public boolean isBust() {
        return cardHand.isBust();
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public boolean isBlackjack() {
        return cardHand.isBlackjack();
    }

    public boolean isStand() {
        return cardHand.isStand();
    }

    @Override
    public String toString() {
        return "Gamer{" +
                "cardHand=" + cardHand +
                '}';
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }
}
