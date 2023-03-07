package blackjack.domain;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private static final int INITIAL_HAND_OUT_COUNT = 2;

    private final Dealer dealer;
    private final Players players;

    private Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = new Players(players);
    }

    public static Participants of(String dealerName, List<String> playerNames) {
        validatePlayerNames(dealerName, playerNames);
        List<Player> players = playerNames.stream()
                .map(Player::new)
                .collect(Collectors.toList());
        return new Participants(new Dealer(dealerName), players);
    }

    private static void validatePlayerNames(String dealerName, List<String> playerNames) {
        if (playerNames.size() != new HashSet<>(playerNames).size()) {
            throw new IllegalArgumentException("플레이어 이름은 중복될 수 없습니다.");
        }
        if (playerNames.contains(dealerName)) {
            throw new IllegalArgumentException("플레이어 이름은 딜러 이름(" + dealerName + ")과 중복될 수 없습니다.");
        }
    }

    public void handOut(Deck deck) {
        handCardsTo(dealer, deck.draw(INITIAL_HAND_OUT_COUNT));
        for (Player player : players.players()) {
            handCardsTo(player, deck.draw(INITIAL_HAND_OUT_COUNT));
        }
    }

    public void handCardsByPlayerName(String playerName, List<Card> cards) {
        Player player = players.findPlayerBy(playerName);
        handCardsTo(player, cards);
    }

    public boolean isAvailablePlayer(String name) {
        Player player = players.findPlayerBy(name);
        return player.isAvailable();
    }

    public int repeatHandToDealerUntilAvailable(Deck deck) {
        int hitCount = 0;
        while (dealer.isAvailable()) {
            handCardsTo(dealer, deck.draw(1));
            hitCount++;
        }
        return hitCount;
    }

    private void handCardsTo(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.take(card);
        }
    }

    public Map<String, List<Card>> openHandOutCardsByName() {
        Map<String, List<Card>> cardsByParticipants = new LinkedHashMap<>();
        cardsByParticipants.put(dealer.getName(), List.of(dealer.openFirstCard()));
        players.players()
                .forEach(player -> cardsByParticipants.put(player.getName(), player.getCards()));
        return cardsByParticipants;
    }

    public List<String> findAvailablePlayerNames() {
        return players.findAvailablePlayerNames();
    }

    public Map<String, FinalCards> openFinalCardsByName() {
        Map<String, FinalCards> finalCardsByPlayerName = new LinkedHashMap<>();
        finalCardsByPlayerName.put(dealer.getName(), createFinalCards(dealer));
        players.players()
                .forEach(player -> finalCardsByPlayerName.put(player.getName(), createFinalCards(player)));
        return finalCardsByPlayerName;
    }

    private FinalCards createFinalCards(Participant participant) {
        return new FinalCards(participant.getCards(), participant.computeCardsScore());
    }

    public PlayerJudgeResults computeJudgeResultsByPlayer() {
        return new PlayerJudgeResults(dealer.judgeAllPlayersResult(players));
    }

    public List<Card> findHandCardsByName(String participantName) {
        Participant participant = findParticipantByName(participantName);
        return participant.getCards();
    }

    private Participant findParticipantByName(String participantName) {
        if (Objects.equals(participantName, dealer.getName())) {
            return dealer;
        }
        return players.findPlayerBy(participantName);
    }
}
