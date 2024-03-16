package domain.blackjack;

import domain.card.Deck;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

public class Players {
    private final List<Player> players;

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

    void draw(Deck deck, PlayerDrawAfterCallBack playerDrawAfterCallBack, DrawConfirmation drawConfirmation) {
        for (Player player : players) {
            playerDraw(deck, playerDrawAfterCallBack, drawConfirmation, player);
        }
    }

    private void playerDraw(Deck deck, PlayerDrawAfterCallBack playerDrawAfterCallBack,
                            DrawConfirmation drawConfirmation,
                            Player player) {
        boolean hasNextDrawChance = true;
        while (hasNextDrawChance) {
            hasNextDrawChance = playerTryDrawOnce(deck, player, drawConfirmation);
            playerDrawAfterCallBack.afterDrawProcess(player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Player player, DrawConfirmation drawConfirmation) {
        boolean needToDraw = drawConfirmation.isDrawDesired(player.getRawName());
        DrawResult drawResult = null;
        if (needToDraw) {
            drawResult = player.draw(deck);
        }
        if (drawResult == null) {
            return false;
        }
        return drawResult.hasNextChance();
    }

    void drawOnce(Deck deck) {
        for (Player player : players) {
            player.draw(deck);
        }
    }

    List<EarningMoney> calculatePlayersEarningMoney(Dealer dealer) {
        return players.stream()
                .map(player -> {
                    GameResult gameResult = GameResultCalculator.calculate(player, dealer);
                    return player.calculateEarningMoney(gameResult);
                })
                .toList();
    }
}
