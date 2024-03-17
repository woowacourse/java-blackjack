package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import java.util.List;

public class InitialState extends InProgress {
    public InitialState() {
        super(new Cards());
    }

    @Override
    public State drawCards(List<Card> cardToAdd) {
        Cards newCards = new Cards(cardToAdd);
        if (newCards.isBlackJack()) {
            return new BlackJack(newCards);
        }
        return new Hit(newCards);
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("게임 시작 전에는 카드를 뽑을 수 없습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("게임 시작 전에는 stand할 수 없습니다.");
    }
}
