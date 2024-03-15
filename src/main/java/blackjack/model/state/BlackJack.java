package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import blackjack.vo.Money;
import java.util.List;

public class BlackJack implements State {
    private final Cards cards;

    public BlackJack(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        Cards newCards = new Cards(cards.getCards());
        newCards.add(card);
        if (newCards.isBust()) {
            return new Bust(newCards);
        }
        return new Hit(newCards);
    }

    @Override
    public State drawCards(List<Card> cardsToAdd) {
        throw new UnsupportedOperationException("게임 시작시에만 카드를 여러장 받을 수 있습니다.");
    }

    @Override
    public State stand() {
        return new BlackJack(cards);
    }

    @Override
    public Money calculateProfit(Money betMoney) {
        return new Money((int) ((betMoney.value()) * 1.5));
    }

    @Override
    public Cards cards() {
        return cards;
    }
}
