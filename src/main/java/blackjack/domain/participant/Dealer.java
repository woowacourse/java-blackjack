package blackjack.domain.participant;

import static blackjack.domain.game.Rule.THRESHOLD;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import java.util.List;

public class Dealer extends Participant {

    private static final int DEALER_THRESHOLD = 16;

    private final Deck deck;

    public Dealer() {
        super("딜러", Hand.createEmptyHand());
        this.deck = Deck.createShuffledDeck();
    }

    public void setBaseCard() {
        cardHand.add(drawCard());
        cardHand.add(drawCard());
    }

    public void deal(Player player) {
        player.receiveCard(drawCard());
    }

    public void setPlayersBaseCard(List<Player> players) {
        for (Player player : players) {
            deal(player);
            deal(player);
        }
    }

    public void pickAnotherCard() {
        cardHand.add(drawCard());
    }

    private Card drawCard() {
        return deck.drawCard();
    }

    public boolean shouldReceive() {
        return cardHand.sumAceToEleven() <= DEALER_THRESHOLD;
    }

    @Override
    public boolean isBust() {
        return cardHand.sumAceToOne() > THRESHOLD;
    }

    @Override
    public int sumCard() {
        return cardHand.sumAceToEleven();
    }
}
