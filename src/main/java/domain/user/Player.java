package domain.user;

import domain.Deck;
import domain.card.Card;
import java.util.List;
import java.util.Objects;

public class Player {
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

    public boolean isBlackJack() {
        return hand.isBlackjack();
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
