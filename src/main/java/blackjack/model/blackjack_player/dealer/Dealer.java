package blackjack.model.blackjack_player.dealer;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;

public final class Dealer {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int PLAYER_SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_DRAWABLE_POINT = 17;

    private final Hand hand;
    private final CardDeck cardDeck;

    public Dealer(final Hand hand, final CardDeck cardDeck) {
        this.hand = hand;
        this.cardDeck = cardDeck;
    }

    public Dealer(final CardDeckInitializer cardDeckInitializer) {
        this(Hand.empty(), CardDeck.initializeFrom(cardDeckInitializer));
    }

    public void dealStartingHand() {
        hand.addCards(drawCard(INITIAL_DRAW_AMOUNT));
    }

    public boolean dealSelf() {
        if (canDrawMoreCard()) {
            hand.addCards(drawCard(DEALER_SINGLE_DRAW_AMOUNT));
            return true;
        }
        return false;
    }

    public BlackJackCards drawPlayerStartingCards() {
        return drawCard(INITIAL_DRAW_AMOUNT);
    }

    public BlackJackCards drawPlayerCards() {
        return drawCard(PLAYER_SINGLE_DRAW_AMOUNT);
    }

    private BlackJackCards drawCard(final int amount) {
        return cardDeck.draw(amount);
    }

    public int getOptimalPoint() {
        return hand.calculateOptimalPoint();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public BlackJackCards openCards() {
        return new BlackJackCards(getAllCards().getFirst());
    }

    public BlackJackCards getAllCards() {
        return hand.getCards();
    }

    private boolean canDrawMoreCard() {
        return hand.getMinimumPoint() < DEALER_DRAWABLE_POINT;
    }
}
