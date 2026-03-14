package blackjack.domain;

import blackjack.domain.vo.MatchResult;

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
            while (player.canHit()) {
                if (!decision.wantsHit(player.getName())) {
                    display.show(player.getName(), player.getCardNames());
                    break;
                }
                player.hit(deck.deal());
                display.show(player.getName(), player.getCardNames());
            }
        });
    }

    public void playDealerTurn() {
        while (dealer.canHit()) {
            dealer.hit(deck.deal());
        }
    }

    public Map<String, MatchResult> matchResults() {
        Map<String, MatchResult> results = new LinkedHashMap<>();
        players.forEach(player ->
                results.put(player.getName(), MatchResult.playerResult(player, dealer))
        );
        return results;
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
