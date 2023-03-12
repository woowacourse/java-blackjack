package domain.participant;

import domain.card.Deck;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_PLAYER_COUNT = 1;
    private static final int MAX_PLAYER_COUNT = 7;
    private static final String ERROR_PLAYER_COUNT = "[ERROR] 플레이어의 수는 1 ~ 7 이내여야 합니다";
    private static final String ERROR_DUPLICATED_NAME = "[ERROR] 플레이어의 이름은 중복될 수 없습니다";

    private final Dealer dealer;
    private final List<Player> players;

    private Participants(List<Player> players) {
        this.dealer = new Dealer();
        this.players = players;
    }

    public static Participants of(List<String> playersName) {
        validate(playersName);

        List<Player> players = playersName.stream()
                .map(Player::from)
                .collect(Collectors.toList());

        return new Participants(players);
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

    public void initHand(Deck deck) {
        dealer.initHand(deck.pollTwoCards());
        players.forEach(player -> player.initHand(deck.pollTwoCards()));
    }

    public void playDealerTurn(Deck deck) {
        dealer.addCard(deck.pollAvailableCard());
    }

    public boolean canDealerHit() {
        return dealer.canHit();
    }

    public boolean isDealerBust() {
        return dealer.isBust();
    }

    public int getDealerScore() {
        return dealer.calculateScore();
    }

    public boolean isDealerBlackjack() {
        return dealer.isBlackjack();
    }

    public Map<Player, Integer> getBettingResult() {
        Map<Player, Integer> betResult = new LinkedHashMap<>();
        for (Player player : getPlayers()) {
            PlayerGameResult playerGameResult = getPlayerGameResult(player);
            int reward = playerGameResult.calculateBenefit(player.getBetAmount());
            betResult.put(player, reward);
        }

        return new LinkedHashMap<>(betResult);
    }

    private PlayerGameResult getPlayerGameResult(Player player) {
        if (isPlayerBlackjack(player)) {
            return PlayerGameResult.BLACKJACK;
        }
        if (isPlayerWin(player)) {
            return PlayerGameResult.WIN;
        }
        if (isDraw(player)) {
            return PlayerGameResult.DRAW;
        }
        return PlayerGameResult.LOSE;
    }

    private boolean isPlayerBlackjack(Player player) {
        return player.isBlackjack() && !isDealerBlackjack();
    }

    private boolean isPlayerWin(Player player) {
        return (player.calculateScore() > getDealerScore() && !player.isBust()) || isDealerBust();
    }

    private boolean isDraw(Player player) {
        return player.calculateScore() == getDealerScore();
    }

    public List<String> getPlayersName() {
        return players.stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return Collections.unmodifiableList(players);
    }
}
