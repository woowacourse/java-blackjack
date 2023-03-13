package domain.blackjack.gamestate;

import domain.blackjack.BlackjackScore;
import domain.card.Card;
import domain.card.Cards;

public class Playing extends GameState {

    private Playing(Cards cards) {
        super(cards);
    }

    public static GameState from(Cards cards) {
        validateSize(cards);
        if (BlackjackScore.from(cards).isEqualTo(BlackjackScore.getMaxScore())) {
            return new Blackjack(cards);
        }

        return new Playing(cards);
    }

    private static void validateSize(Cards cards) {
        if (cards.getCards().size() != 2) {
            throw new IllegalStateException();
        }
    }

    @Override
    public GameState receive(Card card) {
        cards.add(card);

        if (BlackjackScore.from(cards).isGreaterThan(BlackjackScore.getMaxScore())) {
            return new Bust(cards);
        }

        return new Playing(cards);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return true;
    }

    @Override
    public double getEarningRate() {
        return 0;
    }
}
