package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    public static final Name DEALER_NAME = new Name("딜러");
    public static final int BLACKJACK_VALUE = 21;
    private static final int DEALER_UNDER = 16;

    private final Deck deck;

    public Dealer(Hand hand) {
        this(hand, Deck.createShuffledDeck());
    }

    public Dealer(Hand hand, Deck deck) {
        this(DEALER_NAME, hand, deck);
    }

    private Dealer(Name name, Hand hand, Deck deck) {
        super(name, hand);
        this.deck = deck;
    }

    public static Dealer getInstance() {
        return new Dealer(Hand.createEmptyHand());
    }

    public void drawBaseCard() {
        selfDraw();
        selfDraw();
    }

    public void selfDraw() {
        cardHand.add(draw());
    }

    public void drawBaseCardToPlayers(List<Player> players) {
        for (Player player : players) {
            deal(player);
            deal(player);
        }
    }

    public void deal(Player player) {
        player.receiveCard(draw());
    }

    private Card draw() {
        return deck.drawCard();
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public boolean shouldReceive() {
        return cardHand.getScore() <= DEALER_UNDER;
    }
}
