package blackjack.domain;

import java.util.List;

public class Dealer {

    private static final String NAME = "딜러";

    private static final int DEALER_LIMIT_SCORE = 17;

    private final Cards cards;

    public Dealer(final Cards cards) {
        this.cards = cards;
    }

    public Dealer(final List<Card> cards) {
        this(new Cards(cards));
    }

    public void draw(final Card card) {
        validateLimitScore();
        cards.addCard(card);
    }

    private void validateLimitScore() {
        if (score() >= DEALER_LIMIT_SCORE) {
            throw new IllegalStateException("[Error] 딜러 카드가 이미 17이상입니다.");
        }
    }

    private int score() {
        return cards.calculateScore();
    }

    public boolean isEnd() {
        return score() >= DEALER_LIMIT_SCORE;
    }

    public GameOutcome fightResult(final Player player) {
        return cards.fightResult(new Cards(player.cards()));
    }

    public List<Card> initCards() {
        return cards.firstCard();
    }

    public List<Card> getCards() {
        if (!isEnd()) {
            throw new IllegalStateException("딜러는 턴이 종료되지 않을 때 모든 카드를 반환할 수 없습니다.");
        }
        return cards.cards();
    }

    public String getName() {
        return NAME;
    }
}
