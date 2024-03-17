package blackjack.model.state;

import blackjack.model.cards.Card;
import blackjack.model.cards.Cards;
import java.util.List;

public abstract class Finished implements State {
    private final Cards cards;

    public Finished(Cards cards) {
        this.cards = cards;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException("게임이 종료된 카드는 카드를 발급받을 수 없습니다.");
    }

    @Override
    public State drawCards(List<Card> cardsToAdd) {
        throw new UnsupportedOperationException("게임 시작시에만 카드를 여러장 받을 수 있습니다.");
    }

    @Override
    public State stand() {
        throw new UnsupportedOperationException("게임이 종료된 카드는 stand할 수 없습니다.");
    }

    @Override
    public Cards cards() {
        return cards;
    }

    @Override
    public int getScore() {
        return cards.getScoreValue();
    }
}
