package blackjack.model.blackjack_player.dealer;

import blackjack.model.blackjack_player.Hand;
import blackjack.model.blackjack_player.dealer.judgement.Judgement;
import blackjack.model.blackjack_player.player.Player;
import blackjack.model.card.BlackJackCards;
import blackjack.model.card.CardDeck;
import blackjack.model.card.initializer.CardDeckInitializer;
import java.util.List;

public class Dealer {

    private static final int INITIAL_DRAW_AMOUNT = 2;
    private static final int PLAYER_SINGLE_DRAW_AMOUNT = 1;
    private static final int DEALER_SINGLE_DRAW_AMOUNT = 1;
    private static final int PLAYER_DRAWABLE_POINT = 21;
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

    public void drawPlayerCards(final Player player) {
        if (canPlayerDrawMoreCard(player)) {
            player.receiveCards(drawCard(PLAYER_SINGLE_DRAW_AMOUNT));
            return;
        }
        throw new IllegalStateException("카드를 더 뽑을 수 없습니다.");
    }

    public void fight(final Judgement judgement, final Player player) {
        if (judgement.isDraw(this, player)) {
            return;
        }
        if (judgement.isDealerWin(this, player)) {
            player.loseMoney();
            return;
        }
        player.earnMoney(judgement.calculatePlayerReward(player));
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

    public boolean canPlayerDrawMoreCard(final Player player) {
        return player.getOptimalPoint() < PLAYER_DRAWABLE_POINT;
    }

    private boolean canDrawMoreCard() {
        return hand.getMinimumPoint() < DEALER_DRAWABLE_POINT;
    }
}
