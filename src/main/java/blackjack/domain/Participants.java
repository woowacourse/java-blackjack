package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    private static final int NUMBER_OF_DEALER = 1;
    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final Dealer dealer = new Dealer();
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.players = players;
    }

    public static Participants of(List<String> playerNames) {
        validatePlayerNames(playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(players);
    }

    private static void validatePlayerNames(List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
    }

    public int getNeededNumberOfCards() {
        return INITIAL_HAND_OUT_COUNT * getNumberOfParticipants();
    }

    private int getNumberOfParticipants() {
        return NUMBER_OF_DEALER + players.size();
    }

    public void handInitialCards(Deck deck) {
        handInitialCards(dealer, deck);

        for (Player player : players) {
            handInitialCards(player, deck);
        }
    }

    private void handInitialCards(Participant participant, Deck deck) {
        for (int i = 0; i < INITIAL_HAND_OUT_COUNT; i++) {
            participant.take(deck.draw());
        }
    }

    public Map<String, List<Card>> openPlayerCards() {
        Map<String, List<Card>> cardsByParticipants = new LinkedHashMap<>();
        players.forEach(player -> cardsByParticipants.put(player.getName(), player.getCards()));
        return cardsByParticipants;
    }

    public GameResult openDealerGameResult() {
        return GameResult.from(dealer);
    }

    public Map<String, GameResult> openPlayerGameResults() {
        Map<String, GameResult> gameResultsByPlayerName = new LinkedHashMap<>();
        players.forEach(player -> gameResultsByPlayerName.put(player.getName(), GameResult.from(player)));
        return gameResultsByPlayerName;
    }

    public Card openDealerFirstCard() {
        return dealer.getCards().get(0);
    }

    public List<Card> openDealerCards() {
        return dealer.getCards();
    }

    public PlayerWinResults computePlayerWinResults() {
        PlayerWinResults playerWinResults = new PlayerWinResults();
        for (Player player : players) {
            playerWinResults.addResultByPlayerName(player.getName(), dealer.judge(player));
        }
        return playerWinResults;
    }

    public List<String> findNotBustPlayerNames() {
        List<String> notBustPlayerNames = new ArrayList<>();
        for (Player player : players) {
            addAvailablePlayer(player, notBustPlayerNames);
        }
        return notBustPlayerNames;
    }

    private void addAvailablePlayer(Player player, List<String> availablePlayerNames) {
        if (player.canDraw()) {
            availablePlayerNames.add(player.getName());
        }
    }

    public List<Card> getDealerCards() {
        return new ArrayList<>(dealer.getCards());
    }

    public List<List<Card>> getPlayersCards() {
        return players.stream()
                .map(Player::getCards)
                .collect(Collectors.toList());
    }

    public Player findPlayerBy(String playerName) {
        return players.stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당 이름을 가진 플레이어를 찾을 수 없습니다."));
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<String> getPlayerNames() {
        return players.stream()
                .map(Player::getName)
                .collect(Collectors.toList());
    }
}
