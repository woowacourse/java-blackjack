package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

import java.util.List;

public class Dealer implements Player {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final String DEALER_NAME = "딜러";
    private final Name name;
    private Cards cards;

    public Dealer(List<Card> cards) {
        validateCardsSize(cards.size());
        this.cards = new Cards(cards);
        this.name = new Name(DEALER_NAME);
    }

    private void validateCardsSize(final int size) {
        if (size != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("딜러 카드 두 장을 초기에 가지고 있어야 합니다.");
        }
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    @Override
    public void draw(final Card card) {
        if (!canReceive()) {
            throw new IllegalStateException("딜러는 더이상 카드를 받을 수 없습니다.");
        }
        this.cards = cards.add(card);
    }

    @Override
    public boolean canReceive() {
        return cards.getScore().getValue() <= 16 && !cards.isBust();
    }

    @Override
    public Score getGameScore() {
        return cards.getScore();
    }

    public Name getName() {
        return name;
    }

    public Cards getCards() {
        return cards;
    }

}
