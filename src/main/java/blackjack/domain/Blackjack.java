package blackjack.domain;

import blackjack.domain.user.Dealer;
import blackjack.domain.user.Participant;
import blackjack.domain.user.Player;
import java.util.HashMap;
import java.util.Map;

public class Blackjack {

    private final Deck deck;
    private final Map<Player, Receipt> bettingTable;
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

    public void distributeInitCards(Dealer dealer, Players players) {
        dealer.drawInitialCards(deck);
        players.drawInitialCards(deck);
    }

    public boolean isPossibleToGetCard(Participant player) {
        return player.isHit();
    }

    public void distributeAdditionalCard(Participant player) {
        player.drawAdditionalCard(deck);
    }

    public void betting(Player player, int money) {
        bettingTable.put(player, Receipt.generate(money));
    }

    public boolean gameOverByBlackjack(Dealer dealer) {
        return dealer.isBlackjack();
    }

    public Map<Participant, Receipt> calculateYield(Dealer dealer, Players players) {
        for (Player player : players) {
            yieldTable.put(player, BlackjackResult.of(dealer, player)
                    .settle(bettingTable.get(player)));
        }
        return yieldTable;
    }
}
