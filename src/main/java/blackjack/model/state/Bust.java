package blackjack.model.state;

import blackjack.model.Money;
import blackjack.model.deck.Card;
import blackjack.model.participant.Hand;
import java.util.List;

public class Bust implements State {
    private final Hand hand;

    public Bust(final Hand hand) {
        this.hand = hand;
    }

    @Override
    public State draw(final Card card) {
        throw new UnsupportedOperationException("버스트 상태에서는 카드를 더 받을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("이미 버스트이므로 카드를 받지 않을지 여부를 선택할 수 없습니다.");
    }

    @Override
    public List<Card> getCards() {
        return hand.getCards();
    }

    @Override
    public double calculateProfit(final Money money) {
        return 0;
    }
}
