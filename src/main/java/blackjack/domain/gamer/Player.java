package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import blackjack.domain.state.HandState;

import java.util.List;

public class Player {
    private final Name name;
    private final CardHand cardHand;

    private Player(String name, CardHand cardHand) {
        this.name = new Name(name);
        this.cardHand = cardHand;
    }

    public static Player newInstance(String name) {
        return new Player(name, CardHand.createEmpty());
    }

    public static Player of(String name, List<Card> cards) {
        return new Player(name, CardHand.from(cards));
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

    public boolean isState(HandState handState) {
        return cardHand.isState(handState);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name=" + name +
                ", cardHand=" + cardHand +
                '}';
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCardHand() {
        return cardHand.getCards();
    }
}
