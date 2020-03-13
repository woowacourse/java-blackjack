package domain.gamer;

import domain.card.Card;
import domain.card.PlayingCards;

import java.util.Objects;

public abstract class Gamer {
    final PlayingCards playingCards;
    final String name;

    public Gamer(PlayingCards playingCards, String name) {
        validate(name);
        this.playingCards = playingCards;
        this.name = name;
    }

    private static void validate(String name) {
        if (Objects.isNull(name) || name.isEmpty()) {
            throw new IllegalArgumentException("값을 올바르게 입력해주세요.");
        }
        if (name.chars().anyMatch(Character::isWhitespace)) {
            throw new IllegalArgumentException("공백 문자가 입력되었습니다.");
        }
    }

    public void addCard(Card card) {
        playingCards.add(card);
    }

    public int calculateScore() {
        return playingCards.calculateScore();
    }

    public int countCards() {
        return playingCards.countCards();
    }

    public boolean isBust() {
        return playingCards.isBust();
    }

    public boolean isNotBust() {
        return playingCards.isNotBust();
    }

    public boolean isBlackJack() {
        return playingCards.isBlackJack();
    }

    public boolean isNotBlackJack() {
        return playingCards.isNotBlackJack();
    }

    public String getName() {
        return name;
    }

    public PlayingCards getPlayingCards() {
        return playingCards;
    }
}
