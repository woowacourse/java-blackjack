package blackjack.domain.gamer;

import blackjack.domain.Answer;
import blackjack.domain.card.Card;
import blackjack.domain.result.BlackJackResult;

import java.util.List;
import java.util.function.UnaryOperator;

public class Player extends Gamer {

    public Player(String name, List<Card> cards) {
        super(name);
        for (Card card : cards) {
            addCard(card);
        }
    }

    public BlackJackResult match(Dealer dealer) {
        return BlackJackResult.of(this, dealer);
    }

    public boolean isSameName(String name) {
        return this.getName()
                .equals(name);
    }

    public boolean isDrawPossible(UnaryOperator<String> operator) {
        return canDraw() && Answer.from(operator.apply(getName())).isYes();
    }

    @Override
    public boolean canDraw() {
        return getCardsNumberSum() <= MAX_CARD_VALUE;
    }
}
