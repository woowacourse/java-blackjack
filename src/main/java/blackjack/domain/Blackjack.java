package blackjack.domain;

import blackjack.domain.user.Participant;
import java.util.HashMap;
import java.util.Map;

public class Blackjack {

    private final Deck deck;
    private final Map<Participant, Receipt> bettingTable;
    private final Map<Participant, Receipt> yieldTable;

    private Blackjack() {
        deck = Deck.makeRandomShuffledDeck();
        bettingTable = new HashMap<>();
        yieldTable = new HashMap<>();
    }

    private Blackjack(Deck deck) {
        this.deck = deck;
        bettingTable = new HashMap<>();
        yieldTable = new HashMap<>();
    }

    public static Blackjack generate() {
        return new Blackjack();
    }

    public static Blackjack generateWithDeck(Deck deck) {
        return new Blackjack(deck);
    }

    public void distributeInitCards(Participant dealer, Players players) {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    public boolean isPossibleToGetCard(Participant player) {
        return player.isStateSame(State.HIT);
    }

    public void distributeAdditionalCard(Participant player) {
        player.drawAdditionalCard(deck);
    }

    public void betting(Participant player, int money) {
        bettingTable.put(player, Receipt.generate(money));
    }

    public boolean gameOverByBlackjack(Participant dealer) {
        return dealer.isStateSame(State.BLACKJACK);
    }

    public Map<Participant, Receipt> calculateYield(Participant dealer, Players players) {
        for (Participant player : players) {
            yieldTable.put(player, BlackjackResult.of(dealer, player)
                    .settle(bettingTable.get(player)));
        }
        return yieldTable;
    }
}
