package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_OVER_SCORE = 17;

    private final Deck deck;

    public Dealer() {
        this(Deck.createShuffledCards());
    }

    public Dealer(Deck deck) {
        super(new Name(DEALER_NAME), new Hand());
        this.deck = deck;
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void drawBaseCardHand() {
        selfDraw();
        selfDraw();
    }

    public void selfDraw() {
        cardHand.add(drawCard());
    }

    public boolean shouldReceive() {
        return cardHand.getScore() < DEALER_OVER_SCORE;
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
}
