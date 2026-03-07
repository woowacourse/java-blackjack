package view;

import static domain.Constant.DEALER_HIT_STAND_BOUNDARY;
import static domain.Constant.DEFAULT_HAND_NUMBER;
import static domain.Constant.DELIMITER;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.Result;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {
    public void printParticipantsCards(List<Player> players, Dealer dealer) {
        String collect = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println("\n딜러와 " + collect + "에게 " + DEFAULT_HAND_NUMBER + "장을 나누었습니다.");

        // 밍구) 이것도 printDealerCards 메서드로 뺄건지?
        System.out.println("딜러카드: " + dealer.getFirstCard());
        printPlayerCards(players);

        System.out.println();
    }

    private static void printPlayerCards(List<Player> players) {
        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player);
        }
    }

    // 밍구) 중복된 것 같음
    public void printPlayerCards(Player player) {
        System.out.println(player.getName() + "카드: " + player);
    }

    public void printDealerHitStand(boolean value) {
        if (value) {
            System.out.println("\n딜러는 " + DEALER_HIT_STAND_BOUNDARY + "이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("\n딜러는 17이상이라 카드를 받지 않았습니다.");
    }

    public void printResult(List<Player> players, Dealer dealer) {
        System.out.println("\n딜러카드: " + dealer + " - 결과: " + dealer.getTotalSum());

        for (Player player : players) {
            System.out.println(player.getName() + "카드: " + player + " - 결과: " + player.getTotalSum());
        }
    }


    public void printFinalResult(List<Player> players, Dealer dealer) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append("## 최종 승패\n");

        int playerWinCount = 0;
        int playerLoseCount = 0;
        int playerDrawCount = 0;

        for (Player player : players) {
            Result result = Result.judge(player.getTotalSum(), dealer.getTotalSum());
            if (result == Result.WIN) {
                playerWinCount += 1;
            }
            if (result == Result.LOSE) {
                playerLoseCount += 1;
            }
            if (result == Result.DRAW) {
                playerDrawCount += 1;
            }

        }
        sb.append("딜러: " + playerLoseCount + "승 " + playerWinCount + "패 " + playerDrawCount + "무\n");

        for (Player player : players) {
            sb.append(player.getName() + ": ");
            Result result = Result.judge(player.getTotalSum(), dealer.getTotalSum());
            if (result == Result.WIN) {
                sb.append("승\n");
                continue;
            }
            if (result == Result.LOSE) {
                sb.append("패\n");
                continue;
            }
            sb.append("무\n");
        }
        System.out.println(sb);
    }


}
