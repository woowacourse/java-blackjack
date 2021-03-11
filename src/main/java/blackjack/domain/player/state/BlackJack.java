package blackjack.domain.player.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Score;
import java.util.List;

public class BlackJack implements State {

    private final Deck deck;

    protected BlackJack(Deck deck) {this.deck = deck;}

    @Override
    public boolean drawable() {
        return false;
    }

    @Override
    public State draw(Card card) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Score score() {
        return Score.ofBlackJack();
    }

    @Override
    public int winningMoney(int batMoney) {
        return (int) (batMoney * 1.5);
    }

    @Override
    public List<Card> cards() {
        return deck.cards();
    }


}
