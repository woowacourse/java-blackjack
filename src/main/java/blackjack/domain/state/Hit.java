package blackjack.domain.state;

import blackjack.domain.Cards;

public class Hit extends Running {

    @Override
    public State draw(Cards cards) {
        if (cards.size() == Cards.BLACKJACK_CARD_SIZE && cards.getPoint() == Cards.HIGHEST_POINT) {
            return new Blackjack();
        } else if (cards.isBust()) {
            return new Bust();
        }
        return new Hit();
    }

    @Override
    public State stay() {
        return new Stay();
    }


}
