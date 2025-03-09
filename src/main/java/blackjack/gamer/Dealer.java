package blackjack.gamer;

import blackjack.domain.card.CardMachine;
import blackjack.domain.card.Cards;

import java.util.List;

public class Dealer extends GameParticipant {

    private static final String NICKNAME = "딜러";
    private static final int DEALER_HIT_THRESHOLD_POINT = 16;

    private final CardMachine cardMachine;

    public Dealer(Nickname nickname, Cards hand, CardMachine cardMachine) {
        super(nickname, hand);
        this.cardMachine = cardMachine;
    }

    public static Dealer create(int playerCount) {
        return new Dealer(Nickname.from(NICKNAME), Cards.empty(), CardMachine.initialize(playerCount));
    }

    @Override
    public Cards showHand() {
        return Cards.from(
                List.of(hand.getCards().getFirst()));
    }

    @Override
    public boolean shouldHit() {
        return calculateSumOfCards() <= DEALER_HIT_THRESHOLD_POINT;
    }

    public void dealCard(GameParticipant participant) {
        participant.drawCard(cardMachine.drawCard());
    }
}
