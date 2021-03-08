package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    public static final String DEALER_NAME = "딜러";
    public static final int TWENTY_ONE = 21;
    private static final int DEALER_UNDER = 16;

    private final Deck deck;

    public Dealer() {
        super(DEALER_NAME, Hand.createEmptyHand());
        this.deck = Deck.createShuffledDeck();
    }

    public void drawBaseCard() {
        cardHand.add(draw());
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

    public void pickAnotherCard() {
        cardHand.add(draw());
    }

    private Card draw() {
        return deck.drawCard();
    }

    public Card getOpenCard() {
        return cardHand.getCards().get(0);
    }

    public boolean shouldReceive() {
        return cardHand.sumAceToEleven() <= DEALER_UNDER;
    }

    @Override
    public int getHandTotal() {
        return cardHand.sumAceToEleven();
    }
}
