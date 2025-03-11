package domain.player;

import domain.card.Card;
import domain.card.Cards;
import domain.card.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Player {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        this.cards = new Cards();
    }

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(name + ": 이름은 2자 이상 10자 이하여야 합니다.");
        }
    }

    public abstract void openInitialCards();


    public void receiveInitialCards(Deck deck) {
        cards.addAll(deck.drawCards(2));
    }

    public void drawOneCard(Deck deck) {
        cards.addAll(deck.drawCards(1));
    }

    public void openCards(int count) {
        cards.openCards(count);
    }

    public int computeOptimalSum() {
        return cards.computeOptimalSum();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public List<Card> getOpenedCards() {
        return cards.getOpenedCards();
    }

    public List<Card> getCards() {
        return cards.getCards();
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
