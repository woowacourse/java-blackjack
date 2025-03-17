package blackjack.model.participant;

import static blackjack.model.constants.RuleConstants.DEALER_HIT_THRESHOLD;

import blackjack.model.betting.BetAmount;
import blackjack.model.betting.MatchResult;
import blackjack.model.betting.Profit;
import blackjack.model.card.Card;
import blackjack.model.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Dealer {

    private final Deck deck;
    private final Hand hand;
    private final Map<Player, BetAmount> bettings;

    public Dealer(Deck deck) {
        this.deck = deck;
        this.hand = new Hand(new ArrayList<>());
        this.bettings = new HashMap<>();
    }

    public void addBetting(Player player, int stake) {
        bettings.put(player, new BetAmount(stake));
    }

    public Map<Player, Profit> calculatePlayersProfit() {
        Map<Player, Profit> results = new LinkedHashMap<>();
        for (Entry<Player, BetAmount> bettingEntry : bettings.entrySet()) {
            Player player = bettingEntry.getKey();
            MatchResult matchResult = MatchResult.calculatePlayerResult(this, player);
            results.put(player, calculateProfit(bettingEntry.getValue(), matchResult));
        }
        return results;
    }

    public Profit calculateProfit(BetAmount betAmount, MatchResult matchResult) {
        return Profit.of(betAmount, matchResult);
    }


    public Profit calculateDealerProfit(Map<Player, Profit> playersProfit) {
        int sum = (-1) * playersProfit.values().stream()
                .map(Profit::getProfit)
                .reduce(0, Integer::sum);

        return new Profit(sum);
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

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand.getHand());
    }

    public Card getVisibleCard() {
        return hand.getFirstHand();
    }
}
