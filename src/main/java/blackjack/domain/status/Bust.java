package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Bust extends State {
    public Bust(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("버스트이므로 더이상 뽑을 수 없습니다.");
    }
}
