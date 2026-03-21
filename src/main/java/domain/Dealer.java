package domain;

import java.util.List;

public class Dealer extends Participant {
    private static final int MINIMUM_TOTAL_SCORE = 16;
    private static final Name DEALER_NAME = new Name("딜러");

    public Dealer() {
        super(DEALER_NAME);
    }

    @Override
    public List<Card> getInitialVisibleCards() {
        List<Card> currentCards = super.getCards();
        return List.of(currentCards.getFirst());
    }

    @Override
    public boolean isDrawable() {
        return super.getCardsSum() <= MINIMUM_TOTAL_SCORE;
    }

    @Override
    public void addCard(Card card) {
        validateAddable();
        super.addCard(card);
    }

    private void validateAddable() {
        if (super.getCardsSum() > MINIMUM_TOTAL_SCORE) {
            throw new IllegalStateException("딜러는 카드 합계가 16을 초과할 시, 카드를 더 받을 수 없습니다.");
        }
    }
}
