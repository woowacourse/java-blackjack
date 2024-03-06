package blackjack.domain;

import java.util.List;

class Player {

    private final Name name;
    private final Hand hand;

    public Player(String name) {
        this.name = new Name(name);
        this.hand = new Hand();
    }

    public void draw(Card card) {
        validateIsPlayable();

        hand.add(card);
    }

    private void validateIsPlayable() {
        if (!isPlayable()) {
            throw new IllegalStateException("카드를 더이상 받을 수 없습니다.");
        }
    }

    public List<Card> getCards() {
        return hand.getCards();
    }

    public boolean isPlayable() {
        return !(hand.isBust() || hand.isBlackJack());
    }
}
