package blackjack.domain;

public class Player {

    private static final int BLACKJACK_NUMBER = 21;

    private final Cards cards = new Cards();
    private final Name name;

    public Player(String name) {
        this(new Name(name));
    }

    public Player(Name name) {
        this.name = name;
    }

    public void drawCard(Card card) {
        validateBust();
        cards.add(card);
    }

    private void validateBust() {
        if (getTotalNumber() > BLACKJACK_NUMBER) {
            throw new IllegalStateException("버스트여서 더 이상 드로우할 수 없습니다.");
        }
    }

    public int getTotalNumber() {
        return cards.getTotalNumber();
    }
}
