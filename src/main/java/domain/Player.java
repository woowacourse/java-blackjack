package domain;

import java.util.List;

public class Player implements Participant {

    private final String name;
    private final CardDeck cardDeck;

    public Player(String name) {
        validateNotBlank(name);
        name = name.trim();
        validateLength(name);
        this.name = name;
        this.cardDeck = new CardDeck();
    }

    private static void validateLength(String name) {
        if (name.length() > 10) {
            throw new IllegalArgumentException("플레이어의 이름은 10자를 넘을 수 없습니다.");
        }
    }

    private static void validateNotBlank(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("플레이어의 이름은 비어있을 수 없습니다.");
        }
    }

    @Override
    public void setUpCardDeck(Card first, Card second) {
        cardDeck.setUpCards(first, second);
    }

    @Override
    public void takeMoreCard(Card card) {
        cardDeck.takeMore(card);
    }

    @Override
    public int calculateScore() {
        return cardDeck.calculateScore();
    }

    @Override
    public List<Card> getCards() {
        return cardDeck.getCards();
    }
}
