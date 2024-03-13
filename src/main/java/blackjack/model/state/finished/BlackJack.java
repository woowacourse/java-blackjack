package blackjack.model.state.finished;

import blackjack.model.Money;
import blackjack.model.deck.Card;
import blackjack.model.participant.Hand;
import blackjack.model.state.State;

public class BlackJack implements State {
    private final Hand hand;

    public BlackJack(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("추가로 카드를 받을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("이미 블랙잭이므로 카드를 받지 않을지 여부를 선택할 수 없습니다.");
    }

    @Override
    public Hand hand() {
        return hand;
    }

    @Override
    public int calculateScore() {
        return hand.calculateScore();
    }

    @Override
    public double calculateProfit(final Money money) {
        return 0;
    }
}
