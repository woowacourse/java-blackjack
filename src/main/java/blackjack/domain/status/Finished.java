package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Finished extends State{
    public Finished(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("결과가 정해졌기에 더 이상 카드를 뽑을 수 없습니다.");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
