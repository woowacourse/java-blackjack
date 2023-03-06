package domain.participant;

import domain.PlayerGameResult;
import domain.card.Deck;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;
    private static final String ERROR_PLAYER_COUNT = "[ERROR] 플레이어의 수는 1 ~ 7 이내여야 합니다";
    private static final String ERROR_DUPLICATED_NAME = "[ERROR] 플레이어의 이름은 중복될 수 없습니다";

    private final Participant dealer;
    private final List<Participant> players;

    private Participants(Participant dealer, List<Participant> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(List<String> playersName, Deck deck) {
        validate(playersName);

        List<Participant> players = playersName.stream()
                .map(name -> Participant.player(name, deck))
                .collect(Collectors.toList());

        return new Participants(Participant.dealer(deck), players);
    }

    private static void validate(List<String> names) {
        validatePlayersCount(names);
        validateDuplication(names);
    }

    private static void validatePlayersCount(List<String> names) {
        if (names.size() < MIN_PLAYER_COUNT || names.size() > MAX_PLAYER_COUNT) {
            throw new IllegalArgumentException(ERROR_PLAYER_COUNT);
        }
    }

    private static void validateDuplication(List<String> names) {
        long removedDistinctCount = names.stream()
                .map(String::trim)
                .distinct()
                .count();

        if (removedDistinctCount != names.size()) {
            throw new IllegalArgumentException(ERROR_DUPLICATED_NAME);
        }
    }

    public Optional<Participant> getNextTurnPlayer() {
        return players.stream()
                .filter(player -> !player.isStand() && !player.isBust())
                .findFirst();
    }

    public void playDealerTurn(Deck deck) {
        while (!dealer.isStand() && !dealer.isBust()) {
            dealer.addCard(deck.pollAvailableCard());
        }
    }

    public boolean isDealerStand() {
        return dealer.isStand() || dealer.isBust();
    }

    public Map<String, PlayerGameResult> getResult() {
        Map<String, PlayerGameResult> result = new LinkedHashMap<>();

        int dealerScore = dealer.calculateScore();
        for (Participant player : players) {
            int playerScore = player.calculateScore();
            result.put(player.getName(), PlayerGameResult.of(playerScore, dealerScore));
        }

        return result;
    }

    public Participant getDealer() {
        return dealer;
    }

    public List<Participant> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
