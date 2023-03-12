package domain.participant;

import domain.card.Deck;

import java.util.*;
import java.util.stream.Collectors;

public class Participants {

    private static final String ERROR_PLAYER_COUNT = "[ERROR] 플레이어의 수는 1 ~ 7 이내여야 합니다";
    private static final String ERROR_DUPLICATED_NAME = "[ERROR] 플레이어의 이름은 중복될 수 없습니다";
    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;
    private static final double BLACKJACK_OUTCOME_RATE = 1.5;
    private static final int LOSE_RATE = -1;

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(Dealer dealer, List<Player> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public static Participants of(List<String> playersName, Deck deck) {
        validate(playersName);

        List<Player> players = playersName.stream()
                .map(name -> Player.from(name, deck))
                .collect(Collectors.toList());

        return new Participants(Dealer.from(deck), players);
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

    public Optional<Player> getNextTurnPlayer() {
        return players.stream()
                .filter(player -> !player.isStand() && !player.isBust())
                .findFirst();
    }

    public void playDealerTurn(Deck deck) {
        while (!dealer.isStand() && !dealer.isBust()) {
            dealer.addCard(deck.pollAvailableCard());
        }
    }

    public Map<String, Integer> getPlayersResult() {
        Map<String, Integer> result = new LinkedHashMap<>();

        int dealerScore = dealer.calculateScore();
        for (Player player : players) {
            result.put(player.getName(), getPlayerResult(player, dealerScore));
        }

        return result;
    }

    private int getPlayerResult(Player player, int dealerScore) {
        final int playerScore = player.calculateScore();

        if (player.isBust()) {
            return player.getBettingAmount() * LOSE_RATE;
        }
        if (playerScore == dealerScore) {
            return 0;
        }
        if (player.isBlackjack()) {
            return (int) (player.getBettingAmount() * BLACKJACK_OUTCOME_RATE);
        }
        if (dealer.isBust() || playerScore > dealerScore) {
            return player.getBettingAmount();
        }
        return player.getBettingAmount() * LOSE_RATE;
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public boolean isDealerStand() {
        return dealer.isStand() || dealer.isBust();
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
