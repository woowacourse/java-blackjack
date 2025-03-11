package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {

    private static final int PLAYER_DISTRIBUTE_CARD_THRESHOLD = 21;

    private final PlayerName name;
    private Payout payout;

    public Player(final PlayerName name) {
        this.name = name;
        this.payout = Payout.zero();
    }

    public void bet(final int amount) {
        this.payout = this.payout.bet(amount);
    }

    public Payout calculatePayout(final Dealer dealer) {
        this.payout = payout.calculatePayout(dealer, this);
        return this.payout;
    }

    @Override
    public List<Card> openInitialCards() {
        return super.handCards.getCards();
    }

    @Override
    public boolean isPossibleToAdd() {
        return super.calculateDenominations() < PLAYER_DISTRIBUTE_CARD_THRESHOLD;
    }

    public String getName() {
        return name.value();
    }
}
