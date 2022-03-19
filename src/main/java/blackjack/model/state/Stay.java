package blackjack.model.state;

import static blackjack.model.Profits.LOSE;
import static blackjack.model.Profits.WIN;

import blackjack.model.Profits;
import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public class Stay implements State {

    private final Cards cards;

    protected Stay(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] Stay상태 이기 때문에 카드를 추가 할 수 없습니다.");
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
    public boolean isBust() {
        return false;
    }

    @Override
    public boolean isBlackjack() {
        return false;
    }

    @Override
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 이미 Stay 상태 입니다.");
    }

    @Override
    public Profits calculateProfit(State otherState) {
        if (otherState.isBlackjack()) {
            return LOSE;
        }
        if (otherState.isBust()) {
            return WIN;
        }
        return Profits.compareTo(this, otherState);
    }
}
