package blackjack.gamer;

import blackjack.domain.card.CardMachine;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer extends GameParticipant {

    private static final int DEALER_HIT_THRESHOLD_POINT = 16;
    private static final int INITIAL_DEALING_CARD_COUNT = 2;

    private final CardMachine cardMachine;

    private Dealer(Cards cards, CardMachine cardMachine) {
        super(cards);
        this.cardMachine = cardMachine;
    }

    public static Dealer create(int playerCount) {
        return new Dealer(Cards.empty(), CardMachine.initialize(playerCount));
    }

    @Override
    public boolean shouldHit() {
        return calculateSumOfCards() <= DEALER_HIT_THRESHOLD_POINT;
    }
}
