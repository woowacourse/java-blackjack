package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import java.util.List;

public class Hit extends InProgress {

    public Hit(Cards cards) {
        super(cards);
    }

    @Override
    public State draw(Card card) {
        Cards newCards = new Cards(cards().getCards());
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
        return new Stand(cards());
    }
}
