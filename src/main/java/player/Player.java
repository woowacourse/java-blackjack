package player;

import card.Card;
import card.Cards;
import card.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Player {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private final Cards handCards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        handCards = new Cards();
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(name + ": 이름은 2자 이상 10자 이하여야 합니다.");
        }
    }

    public void receiveInitialCards(Deck deck) {
        handCards.addAll(deck.drawCards(2));
    }

    public void drawOneCard(Deck deck) {
        handCards.addAll(deck.drawCards(1));
    }

    public abstract List<Card> openInitialCards();

    public int computeOptimalSum() {
        return handCards.computeOptimalSum();
    }

    public boolean isBust() {
        return handCards.isBust();
    }

    public boolean isBlackjack() {
        return handCards.isBlackjack();
    }

    public Cards getHandCards() {
        return handCards;
    }

    public List<Card> getCards() {
        return handCards.getCards();
    }

    public List<Card> getCards(int count) {
        return handCards.getCards(count);
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
