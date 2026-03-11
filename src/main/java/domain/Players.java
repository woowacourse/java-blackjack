package domain;

import common.ErrorMessage;
import dto.ParticipantDto;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, Deck totalDeck) {
        return new Players(
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
        return Player.from(name, Hand.of(twoCards.get(0), twoCards.get(1)));
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

    public void executeHit(Player player, Supplier<Card> cardSupplier) {
        int index = players.indexOf(player);
        if (index != -1) {
            players.set(index, player.hit(cardSupplier));
        }
    }

    public void executeStand(Player player) {
        int index = players.indexOf(player);
        if (index != -1) {
            players.set(index, player.stand());
        }
    }

    public List<ParticipantDto> getInitialStates() {
        return players.stream()
                .map(ParticipantDto::consistWithInitialInfo)
                .toList();
    }
//    public void hitPlayer(Player targetPlayer, Supplier<Card> cardSupplier) {
//        targetPlayer.hit(cardSupplier);
//    }

//    public List<Player> getPlayers() {
//        return players;
//    }

    public Map<String, List<Card>> getDecksPerPlayer() {
        Map<String, List<Card>> decksPerUser = new LinkedHashMap<>();
        for (Player player : players) {
            decksPerUser.put(player.getName(), player.showOwnCards());
        }
        return decksPerUser;
    }
}
