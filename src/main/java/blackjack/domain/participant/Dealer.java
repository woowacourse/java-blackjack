package blackjack.domain.participant;

import blackjack.domain.Card;
import blackjack.domain.Deck;
import blackjack.domain.Hand;
import java.util.List;

public class Dealer {

    private static final int DEALER_CARD_PIVOT = 17;
    public static final String DEALER_NAME = "딜러";

    private final Name name = new Name(DEALER_NAME);
    private final Hand cardHand = new Hand();
    private final Deck deck;

    public Dealer() {
        this.deck = Deck.createFixedCards();
    }

    public Dealer(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void drawCardHandFirstTurn() {
        selfDraw();
        selfDraw();
    }

    public void selfDraw() {
        cardHand.add(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < DEALER_CARD_PIVOT;
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }
    
    public void drawCardToPlayers(List<Player> players) {
        for (Player player : players) {
            giveCard(player);
            giveCard(player);
        }
    }

    public void giveCard(Player player) {
        player.receiveCard(drawCard());
    }

    public String getName() {
        return name.getName();
    }

    public List<Card> getCards() {
        return cardHand.getCards();
    }

    public Hand getCardHand() {
        return cardHand;
    }
}
