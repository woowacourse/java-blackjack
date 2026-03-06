package view;

import domain.Dealer;
import domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printparticipantsCards(List<Player> players, Dealer dealer) {
        String collect = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));

        System.out.println("딜러와 " + collect + "에게 2장을 나누었습니다.");
    }
}
