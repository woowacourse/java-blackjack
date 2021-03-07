package blackjack.domain.participant;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    public static final int THRESHOLD = 21;
    private static final int DEALER_THRESHOLD = 16;
    private static final String DEALER_NAME = "딜러";

    private final Deck deck;

    public Dealer() {
        super(DEALER_NAME, Hand.createEmptyHand());
        this.deck = Deck.createShuffledDeck();
    }

    public void setBaseCard() {
        cardHand.add(draw());
        cardHand.add(draw());
    }

    public void deal(Player player) {
        player.receiveCard(draw());
    }

    public void setPlayersBaseCard(List<Player> players) {
        for (Player player : players) {
            deal(player);
            deal(player);
        }
    }

    public void pickAnotherCard() {
        cardHand.add(draw());
    }

    public Card getBaseCard() {
        return cardHand.getCards().get(0);
    }

    public boolean shouldReceive() {
        return cardHand.sumAceToEleven() <= DEALER_UNDER;
    }

    private Card draw() {
        return deck.drawCard();
    }

    @Override
    public boolean isBust() {
        return cardHand.sumAceToOne() > BUST_THRESHOLD;
    }

    @Override
    public int sumCard() {
        return cardHand.sumAceToEleven();
    }
}
