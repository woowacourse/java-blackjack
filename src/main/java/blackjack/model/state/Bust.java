package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.vo.Money;
import java.util.List;

public class Bust implements State {
    private final Cards cards;

    public Bust(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("Bust된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Override
    public State drawCards(List<Card> cardsToAdd) {
        throw new UnsupportedOperationException("게임 시작시에만 카드를 여러장 받을 수 있습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("Bust된 카드는 stand할 수 없습니다.");
    }

    @Override
    public Money calculateProfit(Money betMoney) {
        return new Money(-betMoney.value());
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
