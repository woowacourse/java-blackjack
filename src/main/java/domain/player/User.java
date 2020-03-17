package domain.player;

import domain.card.Card;
import domain.card.CardCalculator;

import java.util.*;
import java.util.stream.Collectors;

public abstract class User {
    protected String name;
    protected final List<Card> cards;

    public User(Card... cards) {
        this.cards = new ArrayList<>();
        Collections.addAll(this.cards, cards);
        validateDuplicateCard();
    }

    public int sumCardNumber() {
        return CardCalculator.calculateDeterminedAce(this.cards);
    }

    public String cardToString() {
        List<String> cardString = cards.stream().map(Card::toString).collect(Collectors.toList());

        return String.join(",", cardString);
    }

    public List<Card> getCard() {
        return Collections.unmodifiableList(this.cards);
    }

    public String getName() {
        return this.name;
    }

    protected void validateDuplicateCard() {
        Set<Card> cards = new HashSet<>(this.cards);
        if (cards.size() != this.cards.size()) {
            throw new IllegalArgumentException("카드를 중복으로 받았습니다.");
        }
    }

    protected void validatePlayerName(String name) {
        if (name == null || name.isEmpty()) {
            throw new NullPointerException("플레이어 이름이 null 입니다.");
        }
    }

    public abstract void drawCard(Card cards);
}
