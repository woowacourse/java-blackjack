package domain.participant;

import domain.BetMoney;
import domain.CommonExceptionMessage;
import domain.card.Deck;
import domain.dto.PlayerResult;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Players {
    private static final String ALREADY_EXIST_NAME = "[ERROR] 이름은 중복될 수 없습니다.";

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players from(List<Player> players) {
        validate(players);
        return new Players(List.copyOf(players));
    }

    private static void validate(List<Player> players) {
        validateIsNotNull(players);
        validateIsNotUnique(players);
    }

    private static void validateIsNotNull(List<Player> players) {
        if (players == null) {
            throw new IllegalArgumentException(CommonExceptionMessage.FIELD_CAN_NOT_BE_NULL.getMessage());
        }
    }

    private static void validateIsNotUnique(List<Player> players) {
        long count = players.stream()
                .map(Player::getName)
                .distinct()
                .count();
        if (players.size() != count) {
            throw new IllegalArgumentException(ALREADY_EXIST_NAME);
        }
    }

    public void giveCardsToEachPlayers(Deck deck, int quantity) {
        for (Player player : players) {
            player.addCards(deck.drawWithAmount(quantity));
        }
    }

    public void hitStandEachPlayers(Consumer<Player> hitStandFunc) {
        for (Player player : players) {
            hitStandFunc.accept(player);
        }
    }

    public List<PlayerResult> collectResults(Dealer dealer) {
        List<PlayerResult> playerResults = new ArrayList<>();
        for (Player player : players) {
            BetMoney result = player.judgeResult(dealer);
            playerResults.add(new PlayerResult(player, result));
        }
        return playerResults;
    }

    public List<Player> getPlayers() {
        return players.stream()
                .map(Player::copyOf)
                .toList();
    }
}
