package blackjack.domain;

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

    public static void judgeResult(Dealer dealer, Players players) {
        players.getPlayers().stream()
                .filter(Player::isBust)
                .forEach(player -> {
                    dealer.win();
                    player.lose();
                });

        List<Player> playerList = players.getPlayers().stream()
                .filter(player -> !player.isBust())
                .collect(Collectors.toList());

        if (dealer.isBust()) {
            playerList.forEach(Player::win);
            return;
        }

        playerList.forEach(player -> {
            if (dealer.getCards().calculateScore() > player.getCards().calculateScore()) {
                dealer.win();
                player.lose();
            }
            if (dealer.getCards().calculateScore() == player.getCards().calculateScore()) {
                dealer.draw();
                player.draw();
            }
            if (dealer.getCards().calculateScore() < player.getCards().calculateScore()) {
                dealer.lose();
                player.win();
            }
        });
    }
}
