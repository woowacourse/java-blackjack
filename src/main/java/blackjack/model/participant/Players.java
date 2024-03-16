package blackjack.model.participant;

import blackjack.dto.NameCardsScore;
import blackjack.model.deck.Card;
import blackjack.model.result.Referee;
import blackjack.model.result.ResultCommand;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Players {
    private static final int MAXIMUM_PLAYER_SIZE = 10;
    static final String CAN_NOT_DUPLICATED_NAME = "중복되는 이름을 입력할 수 없습니다.";
    static final String OUT_OF_PLAYERS_SIZE_BOUND = "참여할 인원의 수는 최소 1명 최대 10명이어야 합니다.";

    private final List<Player> players;

    private Players(final List<Player> players) {
        validateNotDuplicateName(players);
        validatePlayerSize(players);
        this.players = players;
    }

    public static Players from(final List<String> names) {
        return names.stream()
                .map(Player::new)
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::new));
    }

    private void validateNotDuplicateName(final List<Player> players) {
        Set<Player> uniquePlayer = new HashSet<>(players);
        if (players.size() != uniquePlayer.size()) {
            throw new IllegalArgumentException(CAN_NOT_DUPLICATED_NAME);
        }
    }

    private void validatePlayerSize(final List<Player> players) {
        if (players.isEmpty() || players.size() > MAXIMUM_PLAYER_SIZE) {
            throw new IllegalArgumentException(OUT_OF_PLAYERS_SIZE_BOUND);
        }
    }

    public List<NameCardsScore> collectFinalResults() {
        return players.stream()
                .map(player -> new NameCardsScore(player.getName(), player.openCards(), player.notifyScore()))
                .toList();
    }

    public void receiveInitialCards(Supplier<List<Card>> initialCards) {
        for (Player player : players) {
            player.receiveInitialCards(initialCards.get());
        }
    }

    public void playEachPlayerTurns(BiConsumer<Player, Dealer> playTurn, Dealer dealer) {
        for (Player player : players) {
            playTurn.accept(player, dealer);
        }
    }

    public Map<Player, ResultCommand> matchPlayerResultCommands(final Referee referee) {
        final Map<Player, ResultCommand> playerResultCommands = new LinkedHashMap<>();
        for (Player player : players) {
            playerResultCommands.put(player, referee.judgePlayerResult(player));
        }
        return playerResultCommands;
    }

    public Stream<Player> stream() {
        return players.stream();
    }

    public List<String> getNames() {
        return players.stream()
                .map(Player::getName)
                .toList();
    }
}
