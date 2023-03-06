package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// TODO 중복 로직 메서드 수정, 클래스 책임 검토
public class Participants {

    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final Dealer dealer;
    // TODO players 클래스 분리해서 player 반환받아 쓰기?
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.dealer = new Dealer();
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

    public void handOut(Deck deck) {
        handOutToParticipant(dealer, deck.draw(INITIAL_HAND_OUT_COUNT));
        for (Player player : players) {
            handOutToParticipant(player, deck.draw(INITIAL_HAND_OUT_COUNT));
        }
    }

    private void handOutToParticipant(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.take(card);
        }
    }

    // TODO stream 사용해서 map 만들기
    public Map<String, List<Card>> openPlayerCards() {
        Map<String, List<Card>> cardsByParticipants = new LinkedHashMap<>();
        players.forEach(player -> cardsByParticipants.put(player.getName(), player.getCards()));
        return cardsByParticipants;
    }

    public FinalCards openDealerFinalCards() {
        return FinalCards.from(dealer);
    }

    public Map<String, FinalCards> openPlayersFinalCards() {
        Map<String, FinalCards> finalCardsByPlayerName = new LinkedHashMap<>();
        players.forEach(player -> finalCardsByPlayerName.put(player.getName(), FinalCards.from(player)));
        return finalCardsByPlayerName;
    }

    public Card openDealerFirstCard() {
        return dealer.getCards().get(0);
    }

    public PlayerJudgeResults computeJudgeResultsByPlayer() {
        PlayerJudgeResults playerJudgeResults = new PlayerJudgeResults();
        for (Player player : players) {
            playerJudgeResults.addResultByPlayerName(player.getName(), dealer.judge(player));
        }
        return playerJudgeResults;
    }

    public List<String> findAvailablePlayerNames() {
        List<String> availablePlayerNames = new ArrayList<>();
        for (Player player : players) {
            addAvailablePlayer(player, availablePlayerNames);
        }
        return availablePlayerNames;
    }

    private void addAvailablePlayer(Player player, List<String> availablePlayerNames) {
        if (player.isAvailable()) {
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
}
