package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;

import java.util.List;

public class Player {
    private static final String NAME_EMPTY_ERROR = "공백이 아닌 플레이어를 입력해 주세요.";
    private static final int BLACKJACK_CARDS_COUNT = 2;

    protected final Hand hand;
    private final String name;

    public Player(String name, Hand hand) {
        validateNameIsBlank(name);
        this.name = name;
        this.hand = hand;
    }

    public void draw(List<Card> cards) {
        hand.add(cards);
    }

    public void draw(Card card) {
        hand.add(card);
    }

    public boolean isBlackjack() {
        return hand.getNumberOfCards() == BLACKJACK_CARDS_COUNT
                && hand.isLimitScore();
    }

    public boolean isBust() {
        return hand.isOverLimitScore();
    }

    public int getScore() {
        return hand.calculateScore();
    }

    public List<Card> getCards() {
        return hand.getMyCards();
    }

    public String getName() {
        return name;
    }

    private void validateNameIsBlank(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_EMPTY_ERROR);
        }
    }
}
