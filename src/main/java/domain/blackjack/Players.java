package domain.blackjack;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {
    private final List<Player> players;

    Players(List<String> playerNames) {
        this.players = playerNames.stream()
                .map(playerName -> Player.from(playerName, HoldingCards.of()))
                .toList();
    }

    public Players(List<String> playersName, List<Integer> battingMoneys) {
        List<Player> players = new ArrayList<>();
        for (int index = 0; index < playersName.size(); index++) {
            String playerName = playersName.get(index);
            int battingMoney = battingMoneys.get(index);
            players.add(Player.from(playerName, HoldingCards.of(), battingMoney));
        }
        this.players = Collections.unmodifiableList(players);
    }

    List<GameResult> calculateGameResultsWith(Dealer dealer) {
        return players.stream()
                .map(player -> GameResultCalculator.calculate(player, dealer))
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

    List<Integer> calculatePlayersEarnMoney(Dealer dealer) {
        return players.stream()
                .map(player -> {
                    GameResult gameResult = GameResultCalculator.calculate(player, dealer);
                    return EarnMoneyCalculator.calculateEarnMoney(player.getBettingMoney(), gameResult);
                })
                .toList();
    }
}
