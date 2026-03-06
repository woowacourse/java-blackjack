package view;

import domain.Dealer;
import domain.Player;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JScrollBar;

public class ResultView {
    public void printparticipantsCards(List<Player> players, Dealer dealer) {
        String collect = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(", "));

        System.out.println("딜러와 " + collect + "에게 2장을 나누었습니다.");

        System.out.println("딜러카드: " + dealer);
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player);
        }
    }

    public void printDealerHitStand(boolean value){
        if(value){
            System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 17이상이라 카드를 받지 않았습니다.");
    }

    public void printResult(List<Player> players, Dealer dealer) {
        System.out.println("딜러카드: " + dealer + " - 결과: " + dealer.getTotalSum());

        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player + " - 결과: " + player.getTotalSum());
        }
    }


    public void printFinalResult(List<Player> players, Dealer dealer){
        int sum = dealer.getTotalSum();
        StringBuilder sb = new StringBuilder();

        int playerWinCount = 0;
        for (Player player : players) {
            boolean isWin = player.isPlayerWin(sum);
            if(isWin){
                playerWinCount+=1;
            }
        }
        sb.append("딜러: " + (players.size()-playerWinCount) + "승 " + playerWinCount+ "패\n");

        for (Player player : players) {
            sb.append(player.getName() + ": ");
            boolean isWin = player.isPlayerWin(sum);
            if(isWin){
                sb.append("승\n");
                continue;
            }
            sb.append("패\n");
        }
        System.out.println(sb);
    }

}
