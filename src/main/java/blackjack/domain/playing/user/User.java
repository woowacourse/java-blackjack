package blackjack.domain.playing.user;

import blackjack.domain.playing.card.Card;
import blackjack.domain.playing.card.Score;
import blackjack.domain.playing.deck.Deck;
import blackjack.domain.playing.user.exception.UserException;

import java.util.List;

public class User {
    public static final int DRAWING_NUMBER_INITIALLY = 2;
    private static final int DRAWING_NUMBER_IN_TURN = 1;
    private static final int BLACKJACK_SCORE_NUMBER = 21;

    private final String name;
    private final Cards cards;

    protected User(String name, Cards cards) {
        this.name = name;
        this.cards = cards;
    }

    public void drawCardsAtFirst(Deck deck) {
        validateOwnedCardsEmpty();
        drawCards(deck, DRAWING_NUMBER_INITIALLY);
    }

    private void validateOwnedCardsEmpty() {
        if (cards.isNotEmpty()) {
            throw new UserException("가지고 있는 카드가 없을 때에만 초기 카드 2장을 받을 수 있습니다.");
        }
    }

    public void drawCardsInTurn(Deck deck) {
        drawCards(deck, DRAWING_NUMBER_IN_TURN);
    }

    private void drawCards(Deck deck, int drawingNumber) {
        for (int i = 0; i < drawingNumber; i++) {
            cards.add(deck.draw());
        }
    }

    public Score calculateScore() {
        return cards.calculateScore();
    }

    public boolean isOverScore(User other) {
        return calculateScore().isOver(other.calculateScore());
    }

    public boolean isUnderScore(User other) {
        return !isOverScore(other);
    }

    public boolean isSameScore(User other) {
        return calculateScore().equals(other.calculateScore());
    }

    public boolean isBlackjack() {
        return calculateScore().isSame(BLACKJACK_SCORE_NUMBER);
    }

    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    public boolean isBust() {
        return calculateScore().isOver(BLACKJACK_SCORE_NUMBER);
    }

    public boolean isNotBust() {
        return !isBust();
    }

    protected boolean hasName(String name) {
        return this.name.equals(name);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCardS();
    }
}
