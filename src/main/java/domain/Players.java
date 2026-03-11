package domain;

import common.ErrorMessage;
import java.util.List;
import java.util.Optional;

public class Players {
    private static final int MAX_PLAYER_NUMBER = 5;

    private final List<Player> players;
    private int orderCursor = 0;

    private Players(List<Player> players) {
        this.players = players;
    }

    public static Players of(List<String> playerNames, Deck totalDeck) {
        return new Players(createPlayers(playerNames, totalDeck));
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
        return new Player(name, twoCards.get(0), twoCards.get(1));
    }

    private static void validateNameUniqueness(List<String> playerNames) {
        long uniqueCount = playerNames.stream().distinct().count();
        if (uniqueCount != playerNames.size()) {
            throw new IllegalArgumentException(ErrorMessage.NAME_UNIQUENESS_ERR.toString());
        }
    }

    private static void validateUserCountLimit(List<String> playerNames) {
        if (playerNames.size() > MAX_PLAYER_NUMBER) {
            throw new IllegalArgumentException(ErrorMessage.MAX_PLAYER_ERROR.getMessage());
        }
    }

    public Optional<Player> findCurrentUser() {
        if (this.orderCursor < this.players.size()) {
            return Optional.of(players.get(orderCursor));
        }
        return Optional.empty();
    }

    public void next() {
        orderCursor++;
    }

//    public void hitPlayer(Player targetPlayer, Supplier<Card> cardSupplier) {
//        targetPlayer.hit(cardSupplier);
//    }

//    public List<Player> getPlayers() {
//        return players;
//    }

//    public Map<String, List<Card>> getDecksPerPlayer() {
//        Map<String, List<Card>> decksPerUser = new LinkedHashMap<>();
//        for (Player player : players) {
//            decksPerUser.put(
//                    player.getName(),
//                    player.getDeck().getCards()
//            );
//        }
//        return decksPerUser;
//    }
}
