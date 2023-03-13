package domain.blackjack.gamestate;

import domain.blackjack.BlackjackScore;
import domain.card.Card;
import domain.card.Cards;

public class Start extends GameState {

    private Start(Cards cards) {
        super(cards);
    }

    public static GameState from(Cards cards) {
        validateSize(cards);
        if (BlackjackScore.from(cards).isEqualTo(BlackjackScore.getMaxScore())) {
            return new Blackjack(cards);
        }

        return new Start(cards);
    }

    private static void validateSize(Cards cards) {
        if (cards.getCards().size() != 2) {
            throw new IllegalStateException();
        }
    }

    @Override
    public GameState receive(Card card) {
        cards.add(card);
        return new Start(cards);
    }

    @Override
    public boolean isAbleToReceiveCard() {
        return false;
    }

    @Override
    public double getEarningRate() {
        return 0;
    }
}
