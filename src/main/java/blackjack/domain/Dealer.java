package blackjack.domain;

import java.util.List;

public class Dealer implements Player {
    private static final int INITIAL_CARD_COUNT = 2;

    private final Name name = new Name("딜러");
    private Cards cards;

    public Dealer(List<Card> cards) {
        validateCardsSize(cards.size());
        this.cards = new Cards(cards);
    }

    private void validateCardsSize(final int size) {
        if (size != INITIAL_CARD_COUNT) {
            throw new IllegalArgumentException("유저는 카드 2장 이상을 갖고 있어야 합니다.");
        }
    }

    @Override
    public void draw(final Card card) {
        if (!canReceive()) {
            throw new IllegalStateException("딜러는 더이상 카드를 받을 수 없습니다.");
        }
        this.cards = cards.add(card);
    }

    public boolean needCard() {
        return canReceive();
    }

    @Override
    public boolean canReceive() {
        return cards.isLowerThan(16) && !cards.isBust();
    }

    @Override
    public GamePoint getGamePoint() {
        if (canReceive()) {
            throw new IllegalStateException("딜러는 17이상 혹은 버스트가 날 때 까지 카드를 줘야 결과를 알 수 있습니다.");
        }
        return cards.getPoint();
    }
}
