package model.view;

import java.util.List;
import model.player.Player;
import model.player.Players;

public class OutputView {

    public OutputView() {
    }

    public void printDistributeCards(Players players) {
        System.out.print("딜러와 ");
        List<String> playerNames = players.getPlayers().stream().map(Player::getName).toList();
        System.out.print(String.join(", ", playerNames));
        System.out.println("에게 2장을 나누었습니다.");
    }
}
