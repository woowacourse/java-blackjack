package domain.game;

import domain.deck.Card;
import domain.deck.Deck;
import domain.player.Amount;
import domain.player.Dealer;
import domain.player.Name;
import domain.player.Player;
import domain.player.Players;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final int DEFAULT_DEALER_PROFIT = 0;
    private static final double BLACKJACK_PROFIT_WEIGHT = 1.5;

    private final Deck deck;
    private final Dealer dealer;
    private final Players players;

    public BlackJackGame(final Deck deck, final List<Name> names, final List<Amount> amounts) {
        this.deck = deck;
        this.dealer = new Dealer();
        this.players = new Players(names, amounts);
        distributeTwoCards();
    }

    private static int calculateProfit(final Outcome outcome, final int amount) {
        if (outcome == Outcome.BLACKJACK) {
            return (int) (amount * BLACKJACK_PROFIT_WEIGHT);
        }
        if (outcome == Outcome.DRAW) {
            return 0;
        }
        if (outcome == Outcome.LOSE) {
            return -amount;
        }
        return amount;
    }

    private void distributeTwoCards() {
        dealer.drawCard(deck.popCard());
        dealer.drawCard(deck.popCard());

        for (final Player player : players.getPlayers()) {
            player.drawCard(deck.popCard());
            player.drawCard(deck.popCard());
        }
    }

    public void drawCardDealer() {
        dealer.drawCard(deck.popCard());
    }

    public void drawCardPlayer(final Name name) {
        players.getPlayer(name)
                .drawCard(deck.popCard());
    }

    public boolean isDealerDraw() {
        return dealer.isDealerDraw();
    }

    public List<Card> getPlayerCards(final Name name) {
        return players.getPlayer(name).cards();
    }

    public List<Card> getDealerCards() {
        return dealer.cards();
    }

    public int getPlayerScore(final Name name) {
        return players.getPlayer(name).score();
    }

    public int getDealerScore() {
        return dealer.score();
    }

    public Map<Name, Integer> calculateProfits() {
        final Map<Name, Outcome> outcomes = Judgement.judgeOutcome(dealer, players);
        final Map<Name, Integer> profits = new LinkedHashMap<>();
        final Name dealerName = dealer.getName();

        profits.put(dealerName, DEFAULT_DEALER_PROFIT);
        for (final Player player : players.getPlayers()) {
            final int profit = calculateProfit(outcomes.get(player.getName()), player.amount());
            profits.put(dealerName, profits.get(dealerName) - profit);
            profits.put(player.getName(), profit);
        }

        return profits;
    }
}
