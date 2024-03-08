package model.player;

import java.util.List;
import java.util.Objects;
import model.card.Card;
import model.card.Cards;

public class Player {

    private static final String INVALID_NAME_LENGTH = "플레이어 이름은 빈 값일 수 없습니다.";
    private static final int ADD_CARD_CONDITION = 22;

    private final String name;
    private final Cards cards;

    public Player(String name) {
        this(name, new Cards(List.of()));
    }

    public Player(String name, Cards cards) {
        validateEmptyName(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateEmptyName(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(INVALID_NAME_LENGTH);
        }
    }

    public boolean isPossibleAddCard() {
        int totalNumbers = cards.calculateTotalNumbers();
        return totalNumbers < ADD_CARD_CONDITION;
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
        return Objects.equals(name, player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public int cardsSize() {
        return cards.size();
    }

    public String getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }
}
