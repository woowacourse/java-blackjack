package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Score;

import java.util.List;

public class User implements Player {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Name name;
    private Cards cards;

    public User(Name name, Cards cards) {
        validateCardsSize(cards.size());
        this.name = name;
        this.cards = cards;
    }

    private void validateCardsSize(final int size) {
        if (size != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("유저는 최소 2장의 카드 갖고 있어야 합니다.");
        }
    }

    @Override
    public void draw(final Card card) {
        if (!canReceive()) {
            throw new IllegalStateException("버스트 후에는 카드를 받을 수 없습니다.");
        }
        this.cards = cards.add(card);
    }

    @Override
    public boolean canReceive() {
        return !cards.isBust();
    }

    @Override
    public Score getGameScore() {
        return cards.getScore();
    }

    public List<Card> openCards() {
        return cards.getCards();
    }

    public boolean isNameOf(final Name userName) {
        return this.name.equals(userName);
    }

    public Name getName() {
        return name;
    }

    public boolean isBusted() {
        return cards.isBust();
    }

    public Cards getCards() {
        return cards;
    }
}
