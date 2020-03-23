package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Score;
import blackjack.domain.deck.Deck;
import blackjack.domain.result.Profit;
import blackjack.domain.result.Rule;
import blackjack.domain.user.exception.UserException;

import java.util.List;

public class AbstractUser implements User {
    public static final int DRAWING_NUMBER_INITIALLY = 2;
    private static final int DRAWING_NUMBER_IN_TURN = 1;
    public static final int BLACKJACK_SCORE_NUMBER = 21;
    private static final int BLACKJACK_CARDS_COUNT = 2;

    private final String name;
    private final Cards cards;

    protected AbstractUser(String name, Cards cards) {
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

    @Override
    public Score calculateScore() {
        return cards.calculateScore();
    }

    @Override
    public boolean isBlackjack() {
        return cards.count() == BLACKJACK_CARDS_COUNT && calculateScore().isSame(BLACKJACK_SCORE_NUMBER);
    }

    @Override
    public boolean isNotBlackjack() {
        return !isBlackjack();
    }

    @Override
    public boolean isBust() {
        return calculateScore().isOver(BLACKJACK_SCORE_NUMBER);
    }

    @Override
    public boolean isNotBust() {
        return !isBust();
    }

    @Override
    public boolean isOverScore(User other) {
        return calculateScore().isOver(other.calculateScore());
    }

    @Override
    public boolean isUnderScore(User other) {
        return !isOverScore(other);
    }

    @Override
    public boolean isSameScore(User other) {
        return calculateScore().equals(other.calculateScore());
    }

    @Override
    public Profit calculateProfit(User user) {
        BettingMoney bettingMoney = null;
        double profitRate = Rule.getProfitRate(this, user);
        return new Profit(bettingMoney.multiplyAndGetValue(profitRate));
    }

    public String getName() {
        return name;
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
