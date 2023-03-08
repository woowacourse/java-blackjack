package domain.player;

import domain.card.Cards;
import domain.card.Card;

import java.util.List;

public abstract class Player {

    private static final int MAX_NAME_LENGTH = 10;
    private static final int NUM_CARDS_FOR_BLACKJACK = 2;

    private final String name;
    private final Cards cards;

    Player(final String name, final Cards cards) {
        validateBlank(name);
        validateLength(name);
        this.name = name;
        this.cards = cards;
    }

    public abstract boolean isInPlaying();

    private void validateBlank(final String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException("참가자의 이름을 입력해 주세요");
        }
    }

    private void validateLength(final String name) {
        if (name.length() > MAX_NAME_LENGTH) {
            throw new IllegalArgumentException("10자 이하의 이름만 입력해 주세요");
        }
    }

    public boolean isBust() {
        return HandsState.from(cards.calculateScore()) == HandsState.BUST;
    }

    public boolean isBlackJack() {
        return HandsState.from(cards.calculateScore()) == HandsState.MAX_SCORE
                && cards.getCardsSize() == NUM_CARDS_FOR_BLACKJACK;
    }

    public String getName() {
        return name;
    }

    public List<String> getCardNames() {
        return cards.getCardNames();
    }

    public int getScore() {
        return cards.calculateScore();
    }

    public void takeCard(final Card card) {
        cards.takeCard(card);
    }
}
