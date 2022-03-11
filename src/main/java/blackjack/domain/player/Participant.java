package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public abstract class Participant {

    private static final int MAX_NAME_LENGTH = 6;

    private final String name;
    private final Cards cards;

    public Participant(String name, Cards cards) {
        validateNameFormat(name);
        this.name = name;
        this.cards = cards;
    }

    private void validateNameFormat(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("[ERROR] 이름을 입력해주세요");
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("[ERROR] 이름의 길이는 최대 6글자입니다");
        }
    }


    public void addCard(Card card) {
        cards.add(card);
    }


    public String getName() {
        return name;
    }

    public List<Card> getCardsToList() {
        return cards.toList();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackjack() {
        return cards.isBlackjack();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Participant{" +
                "name='" + name + '\'' +
                ", cards=" + cards +
                '}';
    }
}
