package domain;

import java.util.List;
import java.util.Objects;

public abstract class Player {
    private static final int MIN_NAME_LENGTH = 2;
    private static final int MAX_NAME_LENGTH = 10;
    private static final int ADD_CARD_THRESHOLD = 16;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        validateName(name);
        this.name = name;
        cards = new Cards();
    }

    public void receiveInitialCards(Deck deck) {
        getCards().addAll(deck.drawCards(2));
    }

    public void addCard(Deck deck) {
        getCards().addAll(deck.drawCards(1));
    }

    public abstract List<Card> openInitialCards();

    private void validateName(String name) {
        if (name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException(name + ": 이름은 2자 이상 10자 이하여야 합니다.");
        }
    }

    public Cards getCards() {
        return cards;
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

    public boolean addCardIfLowScore(Deck deck) {
        if (cards.calculateOptimalSum() <= ADD_CARD_THRESHOLD) {
            addCard(deck);
            return true;
        }
        return false;
    }
}
