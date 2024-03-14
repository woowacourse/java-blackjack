package model.player;

import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;

public class Player {

    private static final int ADD_CARD_CONDITION = 22;
    private static final int BLACKJACK_NUMBER = 21;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        this(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public boolean isNotBust() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < ADD_CARD_CONDITION;
    }

    public boolean isBust() {
        return !isNotBust();
    }

    public boolean isBlackjack() {
        return cards.calculateTotalNumbers() == BLACKJACK_NUMBER;
    }

    public Player addCard(Card card) {
        Cards addedCards = cards.add(card);
        return new Player(name, addedCards);
    }

    public Player addCards(List<Card> cardsElement) {
        Cards addedCards = cards.addAll(cardsElement);
        return new Player(name, addedCards);
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
        return Objects.equals(name, player.name) && Objects.equals(cards, player.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, cards);
    }

    public int cardsSize() {
        return cards.size();
    }

    public int totalNumber() {
        return cards.calculateTotalNumbers();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
