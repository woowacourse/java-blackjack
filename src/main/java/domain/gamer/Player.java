package domain.gamer;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.Hand;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Player {

    private final GamerName name;
    protected final Hand hand;

    public Player(String name, Hand hand) {
        this.name = new GamerName(name);
        this.hand = hand;
    }

    public boolean isBlackJack() {
        return hand.hasSize(2) && hand.hasScore(21);
    }

    public void receiveCards(CardPack cardPack, int count) {
        for (int i = 0; i < count; i++) {
            hit(cardPack.pickOneCard());
        }
    }

    public void hit(Card card) {
        hand.addCard(card);
    }

    public boolean isBust() {
        return !hand.cannotBust();
    }

    public boolean isNotBust() {
        return hand.cannotBust();
    }

    public int finalizeCardsScore() {
        return hand.calculateScore();
    }

    public String getPlayerName() {
        return name.getValue();
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getCards());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(hand, player.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, hand);
    }
}
