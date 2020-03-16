package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import blackjack.domain.user.exception.AbstractUserException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class User {
    private static final int MAX_SCORE_NUMBER_NOT_BUST = 21;
    private static final int MAX_SCORE_NUMBER_TO_MAXIMIZE = 12;
    private static final Score ADDING_SCORE_TO_MAXIMIZE = new Score(10);

    private final String name;
    private final List<Card> cards;

    protected User(String name) {
        this.name = name;
        this.cards = new ArrayList<>();
    }

    public boolean isBust() {
        return calculateScore().isOver(MAX_SCORE_NUMBER_NOT_BUST);
    }

    public boolean isNotBust() {
        return !isBust();
    }

    public void drawCard(Deck deck) {
        cards.add(deck.draw());
    }

    public void receiveInitialCards(Deck deck) {
        validateOwnedCardsEmpty();
        receiveTwoCards(deck);
    }

    private void validateOwnedCardsEmpty() {
        if (!cards.isEmpty()) {
            throw new AbstractUserException("가지고 있는 카드가 없을 때에만 초기 카드 2장을 받을 수 있습니다.");
        }
    }

    private void receiveTwoCards(Deck deck) {
        cards.add(deck.draw());
        cards.add(deck.draw());
    }

    public boolean hasName(String name) {
        return this.name.equals(name);
    }

    public Score calculateScore() {
        Score score = sumScore();
        return maximize(score);
    }

    private Score sumScore() {
        Score score = Score.ZERO_SCORE;

        for (Card card : cards) {
            score = score.add(card.getScore());
        }
        return score;
    }

    private Score maximize(Score score) {
        if (score.isUnder(MAX_SCORE_NUMBER_TO_MAXIMIZE) && hasAce()) {
            return score.add(ADDING_SCORE_TO_MAXIMIZE);
        }
        return score;
    }

    private boolean hasAce() {
        return cards.stream().anyMatch(Card::isAce);
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return Collections.unmodifiableList(cards);
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

    public int countCards() {
        return cards.size();
    }
}
