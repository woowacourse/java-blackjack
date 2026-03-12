package team.blackjack.domain;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import team.blackjack.domain.rule.DefaultBlackjackRule;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    public BlackjackGame(Dealer dealer, Players players) {
        this.dealer = dealer;
        this.players = players;
        this.deck = new Deck();
    }

    public void drawInitialCards() {
        this.players.initPlayerHands(deck);

        this.dealer.hit(dealer.draw(deck));
        this.dealer.hit(dealer.draw(deck));
    }

    public Map<String, Double> calculateAllPlayerRevenue() {
        final LinkedHashMap<String, Double> result = new LinkedHashMap<>();
        for (Player player : players.getPlayerList()) {
            result.put(player.getName(), calculateMoney(player));
        }

        return result;
    }

    public double calculateDealerRevenue() {
        return calculateAllPlayerRevenue().values().stream()
                .mapToDouble(Double::doubleValue)
                .sum() * -1;
    }

    private double calculateMoney(Player player) {
        final Result result = DefaultBlackjackRule.judge(player, dealer);
        return result.getOdds() * player.getBatMoney();
    }

    public void batMoney(String name, int money) {
        this.players.getPlayerByName(name).bat(money);
    }

    public boolean isPlayerBust(String name) {
        return this.players.getPlayerByName(name).isBust();
    }

    public void hitPlayer(String name) {
        this.players.getPlayerByName(name)
                .hit(deck.draw());
    }

    public boolean shouldDealerHit() {
        return DefaultBlackjackRule.shouldDealerHit(this.dealer.getScore());
    }

    public void hitDealer() {
        this.dealer.hit(deck.draw());
    }

    public int getDealerScore() {
        return this.dealer.getScore();
    }

    public Map<String, Integer> getAllPlayerScore() {
        return this.players.getPlayerScoresByPlayer();
    }

    public List<Card> getDealerCards() {
        return this.dealer.getCards();
    }

    public List<Card> getPlayerCardsByName(String name) {
        return this.players.getPlayerByName(name).getCards();
    }

    public Map<String, List<Card>> getAllPlayerCards() {
        return this.players.getCardsByPlayer();
    }

    public List<String> getAllPlayerNames() {
        return this.players.getPlayerNames();
    }
}
