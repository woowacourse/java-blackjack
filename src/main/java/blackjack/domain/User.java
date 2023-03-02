package blackjack.domain;

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
            throw new IllegalArgumentException("유저는 카드 2장 이상을 갖고 있어야 합니다.");
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
    public GamePoint getGamePoint() {
        return cards.getPoint();
    }

}
