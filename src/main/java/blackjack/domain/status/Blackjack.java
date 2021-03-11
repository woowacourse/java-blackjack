package blackjack.domain.status;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Cards;

public class Blackjack extends State {
    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("블랙잭이므로 더이상 뽑을 수 없습니다.");
    }
}
