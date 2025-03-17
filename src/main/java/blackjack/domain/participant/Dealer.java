package blackjack.domain.participant;

import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.CardHand;
import blackjack.state.State;
import java.util.List;
import java.util.Map;

public class Dealer extends Participant {
    private final static String DEALER_NAME = "딜러";
    private final static int DEALER_HIT_THRESHOLD = 16;

    public Dealer(State state) {
        super(state);
    }

    public void playTurn(CardDeck deck) {
        while (!state.isFinished()) {
            state = state.draw(deck.drawCard());
        }
        finishTurn();
    }

    @Override
    public boolean canHit() {
        int score = getScore().intValue();
        return score <= DEALER_HIT_THRESHOLD;
    }

    @Override
    public List<Card> showStartCards() {
        Card firstCard = state.getCards().getFirst();
        return List.of(firstCard);
    }

    public int calculateProfit(Map<Player, Integer> playersProfit) {
        int totalPlayersProfit = playersProfit.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
        return -totalPlayersProfit;
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
