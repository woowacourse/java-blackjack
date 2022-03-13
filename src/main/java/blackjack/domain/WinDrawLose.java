package blackjack.domain;

import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import java.util.List;
import java.util.stream.Collectors;

public enum WinDrawLose {
    WIN("승"),
    DRAW("무"),
    LOSE("패"),
    ;

    private final String name;

    WinDrawLose(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static void judge(Player dealer, Players players) {
        playerBust(dealer, players);
        List<Player> playerList = extractNonBustPlayers(players);
        if (dealer.isBust()) {
            playerList.forEach(Player::win);
            return;
        }
        calculateWinDrawLose(dealer, playerList);
    }

    private static void playerBust(Player dealer, Players players) {
        players.getPlayers().stream()
                .filter(Player::isBust)
                .forEach(player -> {
                    dealer.win();
                    player.lose();
                });
    }

    private static List<Player> extractNonBustPlayers(Players players) {
        return players.getPlayers().stream()
                .filter(player -> !player.isBust())
                .collect(Collectors.toList());
    }

    private static void calculateWinDrawLose(Player dealer, List<Player> playerList) {
        playerList.forEach(player -> {
            checkDealerWin(dealer, player);
            checkDraw(dealer, player);
            checkPlayerWin(dealer, player);
        });
    }

    private static void checkDealerWin(Player dealer, Player player) {
        if (dealer.getCards().calculateScore() > player.getCards().calculateScore()) {
            dealer.win();
            player.lose();
        }
    }

    private static void checkDraw(Player dealer, Player player) {
        if (dealer.getCards().calculateScore() == player.getCards().calculateScore()) {
            dealer.draw();
            player.draw();
        }
    }

    private static void checkPlayerWin(Player dealer, Player player) {
        if (dealer.getCards().calculateScore() < player.getCards().calculateScore()) {
            dealer.lose();
            player.win();
        }
    }
}
