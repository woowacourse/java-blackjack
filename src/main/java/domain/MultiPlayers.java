package domain;

import common.ErrorMessage;
import domain.state.GameState;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MultiPlayers {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final Map<String, Player> players;
    private final Queue<String> notBettingPlayers;
    private final Queue<String> playablePlayers;

    private MultiPlayers(Map<String, Player> players, List<String> playerNames) {
        this.players = players;
        this.notBettingPlayers = new LinkedList<>(playerNames);
        this.playablePlayers = new LinkedList<>(playerNames);
    }

    public static MultiPlayers of(List<String> playerNames, Deck totalDeck) {
        validateUserCountLimit(playerNames);
        validateNameUniqueness(playerNames);

        Map<String, Player> newPlayers = new LinkedHashMap<>();
        for (String name : playerNames) {
            newPlayers.put(name, createNewPlayer(totalDeck, name));
        }

        return new MultiPlayers(newPlayers, playerNames);
    }

    private static Player createNewPlayer(Deck totalDeck, String name) {
        List<Card> twoCards = totalDeck.drawTwoCards();
        Hand newPlayerHand = Hand.of(twoCards.get(0), twoCards.get(1));
        return Player.from(
                name,
                GameState.createPlayerInitialGameState(newPlayerHand)
        );
    }

    private static void validateNameUniqueness(List<String> playerNames) {
        long uniqueCount = playerNames.stream().distinct().count();
        if (uniqueCount != playerNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_UNIQUENESS_ERR.getMessage());
        }
    }

    private static void validateUserCountLimit(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.MAX_PLAYER_ERROR.getMessage());
        }
    }

    public Optional<Player> getNextBetPlayer() {
        if (notBettingPlayers.isEmpty()) {
            return Optional.empty();
        }
        String notYetBetPlayerName = notBettingPlayers.peek();

        return Optional.of(
                players.get(notYetBetPlayerName)
        );
    }

    public Optional<Player> getNextPlayablePlayer() {
        if (playablePlayers.isEmpty()) {
            return Optional.empty();
        }
        String playablePlayerName = playablePlayers.peek();

        return Optional.of(
                players.get(playablePlayerName)
        );
    }

//    public Optional<Player> findNotBetPlayer() {
//        return players.values().stream()
//                .filter(player -> !player.isBet())
//                .findFirst();
//    }

    public void executeBet(Player player, int value) {
        String playerName = player.getName();
        applyAction(playerName, p -> p.bet(value));
        notBettingPlayers.remove(playerName);
    }
//
//    public Optional<Player> findNotStayPlayer() {
//        return players.values().stream()
//                .filter(Player::isPlayable)
//                .findFirst();
//    }

    public Player executeHit(Player player, Supplier<Card> cardSupplier) {
        String playerName = player.getName();

        Player newPlayer = applyAction(playerName, p -> p.hit(cardSupplier));
        if (newPlayer.isFinished()) {
            playablePlayers.remove(playerName);
        }

        return newPlayer;
    }

    public Player executeStand(Player player) {
        String playerName = player.getName();
        Player newPlayer = applyAction(playerName, Player::stand);
        playablePlayers.remove(playerName);
        return newPlayer;
    }

    public List<ParticipantDto> getInitialStates() {
        return players.values().stream()
                .map(ParticipantDto::consistWithInitialInfo)
                .toList();
    }

    public List<PlayerResultDto> checkPlayersGameResult(Dealer dealer) {
        return players.values().stream()
                .map(player -> PlayerResultDto.from(player, dealer))
                .toList();
    }

    private Player applyAction(String name, UnaryOperator<Player> action) {
        Player player = players.get(name);
        if (player == null) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NOT_FOUND.getMessage());
        }

        Player updatedPlayer = action.apply(player);
        players.put(name, updatedPlayer);
        return updatedPlayer;
    }
}