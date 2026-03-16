package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.vo.GameResult;
import blackjack.domain.vo.MatchResult;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;
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
        players.betPlayers(decision);
    }

    public void deal() {
        players.deal(deck::deal);
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

    public List<GameResult> calculatePlayerProfits(){
        List<GameResult> results = new ArrayList<>();
        players.forEach(player -> {
            BigDecimal profit = MatchResult.judge(player, dealer)
                    .calculateProfit(player.getBet().getAmount());
            results.add(GameResult.from(player.getName(), profit));
                }
        );
        return results;
    }

    public BigDecimal calculateDealerProfit(List<GameResult> gameResults) {
        return gameResults.stream()
                .map(GameResult::profit)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .negate();
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
