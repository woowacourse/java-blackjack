package blackjack.domain.player;

import blackjack.domain.card.Card;

public class Dealer extends Player {
    private static final int HIT_CONDITION = 16;

    public Dealer() {
        super("딜러");
    }

    public boolean isMoreCardNeeded() {
        return this.getScore() <= HIT_CONDITION;
    }

    public Card getFirstCard() {
        try {
            return hand.getAllCards().get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new RuntimeException("[ERROR] 딜러가 카드를 갖고 있지 않습니다.");
        }
    }
}
