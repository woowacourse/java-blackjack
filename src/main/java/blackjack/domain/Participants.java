package blackjack.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private static final int INITIAL_HAND_OUT_COUNT = 2;
    private static final int INITIAL_DEALER_CARD_OPEN_INDEX = 0;

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

    public boolean handCardsByPlayerName(String playerName, List<Card> cards) {
        Player player = players.findPlayerBy(playerName);
        handCardsTo(player, cards);
        return player.isAvailable();
    }

    private void handCardsTo(Participant participant, List<Card> cards) {
        for (Card card : cards) {
            participant.take(card);
        }
    }

    public Map<String, List<Card>> openHandOutCardsByName() {
        Map<String, List<Card>> cardsByParticipants = new LinkedHashMap<>();
        cardsByParticipants.put(dealer.getName(), List.of(extractOneToOpenForDealer()));
        players.players()
                .forEach(player -> cardsByParticipants.put(player.getName(), player.getCards()));
        return cardsByParticipants;
    }

    // TODO dealer에서 직접 반환, 카드를 뽑은 상태인지 검증
    private Card extractOneToOpenForDealer() {
        return dealer.getCards().get(INITIAL_DEALER_CARD_OPEN_INDEX);
    }

    public List<String> findAvailablePlayerNames() {
        return players.findAvailablePlayerNames();
    }

    public Map<String, FinalCards> openFinalCardsByName() {
        Map<String, FinalCards> finalCardsByPlayerName = new LinkedHashMap<>();
        finalCardsByPlayerName.put(dealer.getName(), FinalCards.from(dealer));
        players.players()
                .forEach(player -> finalCardsByPlayerName.put(player.getName(), FinalCards.from(player)));
        return finalCardsByPlayerName;
    }

    // TODO 결과 계산 클래스 분리
    public PlayerJudgeResults computeJudgeResultsByPlayer() {
        PlayerJudgeResults playerJudgeResults = new PlayerJudgeResults();
        for (Player player : players.players()) {
            playerJudgeResults.addResultByPlayerName(player.getName(), dealer.judge(player));
        }
        return playerJudgeResults;
    }

    public List<Card> findHoldingCardsByName(String participantName) {
        if (Objects.equals(participantName, dealer.getName())) {
            return dealer.getCards();
        }
        Player player = players.findPlayerBy(participantName);
        return player.getCards();
    }

    // TODO 불필요한 getter 삭제
    public List<Card> getDealerCards() {
        return new ArrayList<>(dealer.getCards());
    }

    public Dealer getDealer() {
        return dealer;
    }
}
