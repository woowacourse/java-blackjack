package blackjack.model.state;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public class Bust implements State {

    private final Cards cards;

    protected Bust(final Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] Bust상태 이기 때문에 카드를 추가 할 수 없습니다.");
    }

    @Override
    public boolean isReady() {
        return false;
    }

    @Override
    public boolean isHit() {
        return false;
    }

    @Override
    public List<String> getCards() {
        return cards.getCardNames();
    }

    @Override
    public int getScore() {
        return cards.sumScore();
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 현재 Bust이기 때문에 Stay 할 수 없습니다.");
    }
}
