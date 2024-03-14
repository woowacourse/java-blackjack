package domain.user;

import domain.Deck;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player {
    public static final int RECEIVABLE_THRESHOLD = 21;
    private final Name name;
    private final Hand hand;

    public Player(Name name) {
        this.name = name;
        this.hand = new Hand();
    }

    public void addStartCards(Deck deck) {
        hand.addStartCards(deck.getNewCard(), deck.getNewCard());
    }

    public void addCard(Card card) {
        hand.addCard(card);
    }

    public int sumHand() {
        return hand.sumCard();
    }

    public boolean busted() {
        return hand.busted();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isReceivable() {
        return hand.sumCard() <= RECEIVABLE_THRESHOLD;
    }

    public List<Card> getAllCards() {
        return hand.getCards();
    }

    public String getNameValue() {
        return name.value();
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
