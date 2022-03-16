package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.BattingMoney;
import blackjack.domain.state.finished.BlackJack;

public interface State {

    State draw(final Card card);

    State stay();

    boolean isFinished();

    Cards cards();

    int getProfit(final State another);

    boolean isSameStateWith(final Class<? extends State> state);

    static State create(final Cards cards, final BattingMoney battingMoney) {
        if (cards.isBlackJack()) {
            return new BlackJack(cards, battingMoney);
        }
        return new Hit(cards, battingMoney);
    }
}
