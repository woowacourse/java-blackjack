package blackjack.domain;

import blackjack.dto.GameResultDto;

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
        players.receiveCards(deck);
        dealer.receiveCards(deck.drawSecondTimes());
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
        dealer.receive(drawn);
    }

    public GameResultDto generateGameResult() {
        return GameResultDto.from(players, dealer);
    }

    public Map<Player, MatchResult> getPlayerFinalResult() {
        Map<Player, MatchResult> playerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResult.put(player, MatchResult.playerResult(player, dealer));
        }
        return playerResult;
    }

    public Map<String, Long> getDealerFinalResult(Map<Player, MatchResult> playerResult) {
        return MatchResult.dealerResult(playerResult);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
