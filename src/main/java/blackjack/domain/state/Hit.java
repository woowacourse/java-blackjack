package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.game.BattingMoney;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stay;

public class Hit extends Created {

    Hit(final Cards cards, final BattingMoney battingMoney) {
        super(cards, battingMoney);
    }

    @Override
    public State draw(final Card card) {
        cards.add(card);
        if (cards.isBust()) {
            return new Bust(cards, battingMoney);
        }
        return this;
    }

    @Override
    public State stay() {
        return new Stay(cards, battingMoney);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public int getProfit(final State another) {
        throw new IllegalStateException("턴이 종료되지 않아, 수익 반환이 불가능합니다.");
    }
}
