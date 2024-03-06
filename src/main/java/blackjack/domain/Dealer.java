package blackjack.domain;

import java.util.List;

class Dealer {

    private static final int STAND_BOUND = 17;

    private final Name name;
    private final Hand hand;

    public Dealer() {
        this.name = new Name("딜러");
        this.hand = new Hand();
    }

    public void draw(Card card) {
        validateIsPlayable();

        hand.add(card);
    }

    private void validateIsPlayable() {
        if (!isPlayable()) {
            throw new IllegalStateException("카드를 더이상 받을 없습니다.");
        }
    }

    public boolean isPlayable() {
        int score = hand.calculateScore();

        return score < STAND_BOUND;
    }

    public String getName() {
        return name.getValue();
    }

    public List<Card> getCards() {
        return hand.getCards();
    }
}
