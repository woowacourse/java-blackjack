package blackjack.model.state;

import static blackjack.model.ProfitResult.DRAW;
import static blackjack.model.ProfitResult.WIN;

import blackjack.model.ProfitResult;
import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import java.util.List;

public class Blackjack implements State {

    private final Cards cards;

    protected Blackjack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State add(Card card) {
        throw new IllegalArgumentException("[ERROR] 카드 숫자 합이 Blackjack이므로 카드를 추가 할 수 없습니다.");
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
    public State stay() {
        throw new IllegalArgumentException("[ERROR] 현재 Blackjack이기 때문에 Stay 할 수 없습니다.");
    }

    @Override
    public ProfitResult calculateProfit(State otherState) {
        if (otherState.isBlackjack()) {
            return DRAW;
        }
        return WIN;
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
        return true;
    }
}
