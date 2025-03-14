package blackjack.participant;

import blackjack.card.Card;
import blackjack.card.CardMachine;
import blackjack.card.Cards;

public class Dealer extends GameParticipant {

    private static final String NICKNAME = "딜러";
    private static final int HIT_THRESHOLD_POINT = 16;
    private static final int INITIAL_HIDE_CARD_COUNT = 1;

    private final CardMachine cardMachine;
    private final Runnable hitDecisionDisplay;

    private Dealer(Nickname nickname,
                   Cards hand,
                   CardMachine cardMachine,
                   Runnable hitDecisionDisplay) {
        super(nickname, hand);
        this.cardMachine = cardMachine;
        this.hitDecisionDisplay = hitDecisionDisplay;
    }

    public static Dealer create(int playerCount,
                                Runnable hitDecisionDisplay) {
        return new Dealer(
                Nickname.from(NICKNAME),
                Cards.empty(),
                CardMachine.initialize(playerCount),
                hitDecisionDisplay);
    }

    @Override
    public boolean shouldHit() {
        boolean shouldHit = calculateSumOfCards() <= HIT_THRESHOLD_POINT;
        if (shouldHit) {
            hitDecisionDisplay.run();
        }
        return shouldHit;
    }

    @Override
    public void showHand() {
        // 아무런 동작을 하지 않는 것이 올바른 동작이다.
    }

    public void dealCard(GameParticipant participant) {
        participant.drawCard(cardMachine.drawCard());
    }

    public void hideCard() {
        for (int cardIndex = 0; cardIndex < Math.min(INITIAL_HIDE_CARD_COUNT, hand.size()); cardIndex++) {
            hand.reverse(cardIndex);
        }
    }

    public void openHiddenCard() {
        hand.getCards().stream()
                .filter(Card::isHidden)
                .forEach(Card::reverse);
    }
}
