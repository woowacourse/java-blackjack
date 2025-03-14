package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class Dealer {

    private final Deck deck;
    private final Hand hand;

    public Dealer(Deck deck) {
        this.deck = deck;
        this.hand = new Hand(new ArrayList<>());
    }

    public Card drawCard() {
        return deck.draw();
    }

    public void receiveHand(Card card) {
        hand.receiveHand(card);
    }

    public boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public boolean isBust() {
        return hand.isBust();
    }

    public int calculateHandTotal() {
        return hand.calculateHandTotal();
    }

    public boolean hitDealer() {
        if (hand.calculateHandTotal() <= DEALER_HIT_THRESHOLD) {
            receiveHand(drawCard());
            return true;
        }
        return false;
    }

    public Profit calculateProfit(Map<Player, Profit> playersProfit) {
        int sum = (-1) * playersProfit.values().stream()
                .map(Profit::getProfit)
                .reduce(0, Integer::sum);

        return new Profit(sum);
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getHand());
    }

    public Card getVisibleCard() {
        return hand.getFirstHand();
    }
}
