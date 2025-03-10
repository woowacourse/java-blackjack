package domain;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Game {
    private static final int MAX_PLAYER_COUNT = 5;

    private final Map<Player, GameResult> players = new LinkedHashMap<>();
    private final Dealer dealer;

    public Game(List<String> playerNames, Deck deck) {
        dealer = new Dealer(deck, deck.getInitialDeal());
        validatePlayerCount(playerNames);
        validateDuplicateName(playerNames);
        playerNames.forEach(this::registerPlayer);
    }

    public void playerHit(String playerName) {
        Card card = dealer.pickCard();
        findPlayerByName(playerName).hit(card);
    }

    public void dealerHit() {
        Card card = dealer.pickCard();
        dealer.hit(card);
    }

    public void calculateGameResult() {
        for (Player player : players.keySet()) {
            GameResult gameResult = GameResult.calculateDealerGameResult(dealer, player);
            dealer.recordGameResult(gameResult);
            players.put(player, GameResult.getOppositeResult(gameResult));
        }
    }

    public boolean doesDealerNeedCard() {
        return dealer.doesNeedCard();
    }

    public boolean canHit(String playerName) {
        return findPlayerByName(playerName).canHit();
    }

    public List<GameParticipant> getParticipants() {
        List<GameParticipant> participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(players.keySet());
        return participants;
    }

    public List<Player> getPlayers() {
        return List.copyOf(players.keySet());
    }

    public GameResult getPlayerGameResult(Player player) {
        return players.get(player);
    }

    public List<Card> getDealerCards() {
        return dealer.getCards();
    }

    public int getDealerGameResultCount(GameResult result) {
        return dealer.getGameResultCount(result);
    }

    public List<Card> getPlayerCards(String playerName) {
        return findPlayerByName(playerName).getCards();
    }

    public List<String> getPlayerNames() {
        return players.keySet().stream()
                .map(GameParticipant::getName)
                .toList();
    }

    private void registerPlayer(String playerName) {
        CardHand initialDeal = dealer.pickInitialDeal();
        Player player = new Player(playerName, initialDeal);
        players.put(player, GameResult.NONE);
    }

    private void validatePlayerCount(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException("[ERROR] 참여자는 최대 5명입니다.");
        }
    }

    private void validateDuplicateName(List<String> playerNames) {
        if (playerNames.size() != Set.copyOf(playerNames).size()) {
            throw new IllegalArgumentException("[ERROR] 이름은 중복될 수 없습니다.");
        }
    }

    private GameParticipant findPlayerByName(String playerName) {
        return players.keySet().stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("[ERROR] 일치하는 플레이어가 없습니다."));
    }
}
