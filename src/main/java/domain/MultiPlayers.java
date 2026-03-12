package domain;

import common.ErrorMessage;
import domain.state.GameState;
import dto.ParticipantDto;
import dto.PlayerResultDto;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class MultiPlayers {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;

    private MultiPlayers(List<Player> players) {
        this.players = players;
    }

    public static MultiPlayers of(List<String> playerNames, Deck totalDeck) {
        return new MultiPlayers(
                new ArrayList<>(createPlayers(playerNames, totalDeck))
        );
    }

    private static List<Player> createPlayers(List<String> playerNames, Deck totalDeck) {
        validateUserCountLimit(playerNames);
        validateNameUniqueness(playerNames);

        return playerNames.stream()
                .map(name -> createNewPlayer(totalDeck, name))
                .toList();
    }

    private static Player createNewPlayer(Deck totalDeck, String name) {
        List<Card> twoCards = totalDeck.drawTwoCards();
        Hand newPlayerHand = Hand.of(twoCards.get(0), twoCards.get(1));
        return Player.from(
                name,
                GameState.createPlayerInitialGameState(newPlayerHand));
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

    public Optional<Player> findNotStayPlayer() {
        return players.stream()
                .filter(player -> !player.isFinished())
                .findFirst();
    }

    public Player executeHit(Player player, Supplier<Card> cardSupplier) {
        return applyAction(player, p -> p.hit(cardSupplier));
    }

    public Player executeStand(Player player) {
        return applyAction(player, Player::stand);
    }

    public List<ParticipantDto> getInitialStates() {
        return players.stream()
                .map(ParticipantDto::consistWithInitialInfo)
                .toList();
    }

    public List<PlayerResultDto> checkPlayersGameResult(Dealer dealer) {
        return players.stream().map(
                player -> PlayerResultDto.from(player, dealer.gameState)
        ).toList();
    }

    private Player applyAction(Player player, UnaryOperator<Player> action) {
        int index = players.indexOf(player);
        if (index == -1) {
            throw new IllegalArgumentException(ErrorMessage.PLAYER_NOT_FOUND.getMessage());
        }

        Player newPlayer = action.apply(player);
        players.set(index, newPlayer);
        return newPlayer;
    }
}
