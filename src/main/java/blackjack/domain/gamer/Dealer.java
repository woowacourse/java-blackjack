package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.CardMachine;
import blackjack.domain.card.Cards;

import java.util.function.Consumer;

public class Dealer extends GameParticipant {

    private static final String NICKNAME = "딜러";
    private static final int HIT_THRESHOLD_POINT = 16;
    private static final int INITIAL_HIDE_CARD_COUNT = 1;

    private final CardMachine cardMachine;
    private final Runnable hitDecisionDisplay;

    private Dealer(Nickname nickname,
                   Cards hand,
                   CardMachine cardMachine,
                   Consumer<GameParticipant> handDisplay,
                   Runnable hitDecisionDisplay) {
        super(nickname, hand, handDisplay);
        this.cardMachine = cardMachine;
        this.hitDecisionDisplay = hitDecisionDisplay;
    }

    public static Dealer create(int playerCount,
                                Consumer<GameParticipant> handDisplay,
                                Runnable hitDecisionDisplay) {
        return new Dealer(
                Nickname.from(NICKNAME),
                Cards.empty(),
                CardMachine.initialize(playerCount),
                handDisplay,
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
