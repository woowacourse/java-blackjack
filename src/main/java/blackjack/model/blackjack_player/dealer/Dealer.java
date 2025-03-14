package blackjack.model.blackjack_player.dealer;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.dealer.judgement.JudgementStrategy;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import java.util.List;

public final class Dealer {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int PLAYER_SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_DRAWABLE_POINT = 17;

    private final JudgementStrategy judgementStrategy;
    private final Hand hand;
    private final CardDeck cardDeck;

    public Dealer(final JudgementStrategy judgementStrategy, final Hand hand, final CardDeck cardDeck) {
        this.judgementStrategy = judgementStrategy;
        this.hand = hand;
        this.cardDeck = cardDeck;
    }

    public Dealer(final JudgementStrategy judgementStrategy, final CardDeckInitializer cardDeckInitializer) {
        this(judgementStrategy, Hand.empty(), CardDeck.initializeFrom(cardDeckInitializer));
    }

    public void dealInitialCards(final List<Player> players) {
        hand.addCards(drawCard(INITIAL_DRAW_AMOUNT));
        players.forEach(player -> player.receiveCards(drawCard(INITIAL_DRAW_AMOUNT)));
    }

    public boolean drawSelf() {
        if (canDrawMoreCard()) {
            hand.addCards(drawCard(DEALER_SINGLE_DRAW_AMOUNT));
            return true;
        }
        return false;
    }

    public BlackJackCards drawPlayerCards() {
        return drawCard(PLAYER_SINGLE_DRAW_AMOUNT);
    }

    public void fight(final Player player) {
        if (judgementStrategy.isDraw(this, player)) {
            return;
        }
        if (judgementStrategy.isDealerWin(this, player)) {
            player.loseMoney();
            return;
        }
        player.earnMoney(judgementStrategy.calculatePlayerReward(player));
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
