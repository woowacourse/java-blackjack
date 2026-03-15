package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.vo.GameResult;
import blackjack.domain.vo.MatchResult;
import blackjack.domain.vo.Payoff;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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

    private void validate(Dealer dealer, Players players, Deck deck) {
        Objects.requireNonNull(dealer, "게임에 딜러가 존재해야 합니다.");
        Objects.requireNonNull(players, "게임에 플레이어가 존재해야 합니다.");
        Objects.requireNonNull(deck, "게임에 덱이 존재해야 합니다.");
    }

    public static BlackjackGame create(List<String> names, ShuffleStrategy strategy) {
        Dealer dealer = Dealer.of();
        Players players = generatePlayers(names);
        Deck deck = Deck.create(strategy);
        return new BlackjackGame(dealer, players, deck);
    }

    private static Players generatePlayers(List<String> names) {
        return names.stream()
                .map(name -> Player.of(Name.of(name)))
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::of));
    }

    public void betPlayers(BetDecision decision) {
        players.forEach(player -> {
            int amount = decision.decideBet(player.getName());
            player.placeBet(amount);
        });
    }

    public void deal() {
        players.forEach(
                player -> {
                    player.hit(deck.deal());
                    player.hit(deck.deal());
                });
        dealer.hit(deck.deal());
        dealer.hit(deck.deal());
    }

    public void playPlayerTurns(HitDecision decision, TurnDisplay display) {
        players.forEach(player -> {
            playerTurn(player, decision, display);
        });
    }

    private void playerTurn(Player player, HitDecision decision, TurnDisplay display) {
        boolean playing = true;
        while (player.canHit() && playing) {
            playing = processHit(player, decision, display);
        }
    }

    private boolean processHit(Player player, HitDecision decision, TurnDisplay display) {
        if (!decision.wantsHit(player.getName())) {
            display.show(player.getName(), player.getCardNames());
            return false;
        }
        player.hit(deck.deal());
        display.show(player.getName(), player.getCardNames());
        return true;
    }

    public void playDealerTurn() {
        while (dealer.canHit()) {
            dealer.hit(deck.deal());
        }
    }

    public List<GameResult> gameResults(){
        List<GameResult> results = new ArrayList<>();
        players.forEach(player -> {
            int profit = Payoff.playerResult(player, dealer)
                    .calculateProfit(player.getBet().getAmount());
            results.add(GameResult.from(player.getName(), profit));
                }
        );
        return results;
    }

    public int getDealerProfit(List<GameResult> gameResults) {
        return -gameResults.stream()
                .mapToInt(GameResult::profit)
                .sum();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
