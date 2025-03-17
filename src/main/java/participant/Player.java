package participant;

import card.Card;
import card.CardHand;
import java.util.List;
import result.GameResult;
import result.Profit;
import state.running.PlayerHit;

public final class Player extends Participant {
    private final Name name;
    private final Bet bet;

    public Player(final Name name, final Bet bet, final CardHand initialHand) {
        super(PlayerHit.initialState(initialHand));
        this.name = name;
        this.bet = bet;
    }

    @Override
    public List<Card> openInitialCard() {
        CardHand cardHand = state.cardHand();
        return cardHand.getCards();
    }

    @Override
    public Name getName() {
        return name;
    }

    public Profit calculateProfit(final GameResult result) {
        return Profit.of(bet, result);
    }
}
