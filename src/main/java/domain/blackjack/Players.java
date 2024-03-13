package domain.blackjack;

import domain.card.Deck;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Players {
    private final List<Player> players;

    public Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(playerName -> Player.from(playerName, HoldingCards.of()))
                .toList();
    }

    public Map<String, GameResult> calculateGameResultsWithAsMap(Dealer dealer) {
        return players.stream()
                .collect(Collectors.toMap(Player::getRawName, player -> player.calculateGameResult(dealer)));
    }

    List<GameResult> calculateGameResultsWith(Dealer dealer) {
        return players.stream()
                .map(player -> player.calculateGameResult(dealer))
                .toList();
    }

    public void forEach(Consumer<Player> consumer) {
        players.forEach(consumer);
    }

    public List<String> getPlayerNames() {
        return players.stream().map(Player::getRawName).toList();
    }

    void draw(Deck deck, Consumer<Player> doAfterEachPlayerDraw, Function<String, Boolean> playerWantDraw) {
        for (Player player : players) {
            playerDraw(deck, doAfterEachPlayerDraw, playerWantDraw, player);
        }
    }

    void drawOnce(Deck deck) {
        for (Player player : players) {
            player.drawRandom(deck);
        }
    }

    private void playerDraw(Deck deck, Consumer<Player> doAfterEachPlayerDraw, Function<String, Boolean> playerWantDraw,
                            Player player) {
        boolean hasNextDrawChance = true;
        while (hasNextDrawChance) {
            hasNextDrawChance = playerTryDrawOnce(deck, player, playerWantDraw);
            doAfterEachPlayerDraw.accept(player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Player player, Function<String, Boolean> playerWantDraw) {
        boolean needToDraw = playerWantDraw.apply(player.getRawName());
        DrawResult drawResult = null;
        if (needToDraw) {
            drawResult = player.drawRandom(deck);
        }
        if (drawResult == null) {
            return false;
        }
        return drawResult.hasNextChance();
    }
}
