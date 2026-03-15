package blackjack.domain;

import blackjack.dto.GameResult;
import blackjack.dto.PlayerProfitResult;
import blackjack.dto.ProfitResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BlackjackGame {
    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private BlackjackGame(Dealer dealer, Players players, Deck deck) {
        validate(dealer, players, deck);
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame create(Players playersWithBetAmounts, ShuffleStrategy strategy) {
        Dealer dealer = Dealer.of();
        Deck deck = Deck.create(strategy);
        return new BlackjackGame(dealer, playersWithBetAmounts, deck);
    }

    private void validate(Dealer dealer, Players players, Deck deck) {
        Objects.requireNonNull(dealer, "dealer 는 null 이 올 수 없습니다.");
        Objects.requireNonNull(players, "players 는 null 이 올 수 없습니다.");
        Objects.requireNonNull(deck, "deck 은 null 이 올 수 없습니다.");
    }

    public void deal() {
        players.receiveCards(deck);
        dealer.receiveCards(deck.drawInitialCards());
    }

    public int playerCount() {
        return players.count();
    }

    public boolean canPlayerHit(int index) {
        return players.playerAt(index).canHit();
    }

    public String playerNameByIndex(int index) {
        return players.playerAt(index).name();
    }

    public Player playerDraw(int index) {
        TrumpCard drawn = deck.draw();
        players.playerAt(index).receiveCard(drawn);
        return players.playerAt(index);
    }

    public boolean canDealerHit() {
        return dealer.shouldHit();
    }

    public void dealerDraw() {
        TrumpCard drawn = deck.draw();
        dealer.receiveCard(drawn);
    }

    public GameResult generateGameResult() {
        return GameResult.from(players, dealer);
    }

    public Map<Player, MatchResult> getPlayerFinalResult() {
        Map<Player, MatchResult> playerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResult.put(player, MatchResult.of(player, dealer));
        }
        return playerResult;
    }

    public Map<String, Long> getDealerFinalResult(Map<Player, MatchResult> playerResult) {
        return Map.of(
                "승", countMatchResult(playerResult, MatchResult.LOSE),
                "패", countMatchResult(playerResult, MatchResult.WIN),
                "무", countMatchResult(playerResult, MatchResult.DRAW)
        );
    }

    public ProfitResult calculateProfits() {
        List<PlayerProfitResult> playerProfits = new ArrayList<>();

        for (Player player : players.getPlayers()) {
            playerProfits.add(calculatePlayerProfit(player));
        }

        double dealerProfit = playerProfits.stream()
                .mapToDouble(PlayerProfitResult::profit)
                .sum() * -1;

        return new ProfitResult((int) dealerProfit, playerProfits);
    }

    private PlayerProfitResult calculatePlayerProfit(Player player) {
        BettingResult outcome = BettingResult.of(MatchResult.of(player, dealer), player);
        double profit = outcome.calculateProfit(player.betAmount());
        return new PlayerProfitResult(player.name(), (int) profit);
    }

    private long countMatchResult(Map<Player, MatchResult> playerResults, MatchResult target) {
        return playerResults.values().stream()
                .filter(result -> result == target)
                .count();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}