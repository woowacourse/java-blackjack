package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

import java.util.List;
import java.util.Objects;

public class Player {

    private static final int MAX_NAME_LENGTH = 6;

    private final String name;
    private boolean stay = false;
    private final Cards cards;

    public Player(String name, Cards cards) {
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
        if (cards.isBust() || cards.isBlackjack()) {
            stay = true;
        }
    }

    public String getName() {
        return name;
    }

    public boolean isAbleToHit() {
        return !stay;
    }

    public void stay() {
        stay = true;
    }

    public List<Card> getCards() {
        return cards.toList();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return name.equals(player.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
