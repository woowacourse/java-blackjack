package domain.gamer;

import domain.Score;
import domain.card.Card;
import domain.card.Deck;
import domain.card.PlayingCards;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public abstract class Gamer {
    private final PlayingCards playingCards;
    private final String name;

    public Gamer(PlayingCards playingCards, String name) {
        validate(name);
        this.playingCards = playingCards;
        this.name = name;
    }

    private static void validate(String name) {
        validateNullAndEmpty(name);
        validateWhiteSpace(name);
    }

    private static void validateWhiteSpace(String name) {
        if (name.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("공백 문자가 입력되었습니다.");
        }
    }

    private static void validateNullAndEmpty(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("값을 올바르게 입력해주세요.");
        }
    }

    public void hit(Deck deck) {
        addCard(deck.dealCard());
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public Score calculateScore() {
        return playingCards.calculateScore();
    }

    public int countCards() {
        return playingCards.countCards();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public boolean isNotBust() {
        return !this.isBust();
    }

    public boolean isBlackJack() {
        return playingCards.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return !this.isBlackJack();
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(playingCards.getCards());
    }

    public abstract boolean isHittable();
}
