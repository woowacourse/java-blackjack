package blackjack.service;

import blackjack.domain.*;
import blackjack.dto.GameResult;

import java.util.*;

public class BlackjackGame {
    private static final int BLACKJACK_THRESHOLD = 21;

    private final Dealer dealer;
    private final Players players;
    private final Deck deck;

    private BlackjackGame(Dealer dealer, Players players, Deck deck) {
        this.dealer = dealer;
        this.players = players;
        this.deck = deck;
    }

    public static BlackjackGame create(List<String> names, ShuffleStrategy strategy) {
        Dealer dealer = Dealer.of();
        Players players = generatePlayers(names);
        Deck deck = Deck.create(strategy);
        return new BlackjackGame(dealer, players, deck);
    }

    private static Players generatePlayers(List<String> names) {
        List<Player> players = new ArrayList<>();
        for (String name : names) {
            Player player = Player.of(Name.of(name));
            players.add(player);
        }
        return Players.of(players);
    }

    public void deal(){
        players.receiveCards(deck);
        dealer.receiveCards(deck.drawSecondTimes());
    }

    public int playerCount(){
        return players.count();
    }

    public boolean canPlayerHit(int index){
        return players.playerAt(index).canHit();
    }

    public String playerNameByIndex(int index){
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

    public void dealerDraw(){
        TrumpCard drawn = deck.draw();
        dealer.receive(drawn);
    }

    public GameResult generateGameResult(){
        return GameResult.from(players, dealer);
    }

    public Map<Player, MatchResult> getPlayerFinalResult(){
        Map<Player, MatchResult> playerResult = new LinkedHashMap<>();
        for (Player player : players.getPlayers()) {
            playerResult.put(player, MatchResult.playerResult(player, dealer));
        }
        return playerResult;
    }

    public Map<String, Long> getDealerFinalResult(Map<Player, MatchResult> playerResult){
        return MatchResult.dealerResult(playerResult);
    }

    public Players getPlayers() {
        return players;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
