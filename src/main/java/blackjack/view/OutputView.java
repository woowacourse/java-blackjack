package blackjack.view;

import blackjack.domain.GameResult;
import blackjack.domain.human.Dealer;
import blackjack.domain.human.Human;
import blackjack.domain.human.Player;
import blackjack.domain.human.Players;

public class OutputView {

    public static void printInitCardState(final Players players, final Dealer dealer) {
        System.out.printf(System.lineSeparator() + "%s와 %s에게 2장의 카드를 나누었습니다."
                        + System.lineSeparator(),
                dealer.getName(), players.getPlayerNames());
    }

    public static void printHumanCardState(final Human human) {
        String result = human.getName() + "카드: " + human.getCards().toString();
        System.out.println(result);
    }

    public static void printInitCards(final Players players, final Dealer dealer) {
        OutputView.printInitCardState(players, dealer);
        OutputView.printHumanCardState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardState(player);
        }
        System.out.println();
    }

    public static void printCardAndPoint(final Players players, final Dealer dealer) {
        System.out.println();
        OutputView.printHumanCardPointState(dealer);
        for (Player player : players.getPlayers()) {
            OutputView.printHumanCardPointState(player);
        }
    }

    public static void printHumanCardPointState(final Human human) {
        String result = human.getName() + "카드: " + human.getCards().toString() + " - 결과 : " + human.getPoint();
        System.out.println(result);
    }

    public static void printDealerCardAdded() {
        System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(final Players players) {
        System.out.println(System.lineSeparator() + "## 최종 승패");
        StringBuilder dealerResult = new StringBuilder();
        int win = 0;
        int draw = 0;
        int lose = 0;
        for (Player player : players.getPlayers()) {
            String playerStatus = player.getName() + ": ";
            playerStatus += player.getResult().getText();
            dealerResult.append(playerStatus).append(System.lineSeparator());
            if (player.getResult().equals(GameResult.WIN)) {
                lose++;
                continue;
            }
            if (player.getResult().equals(GameResult.LOSE)) {
                win++;
                continue;
            }
            draw++;
        }
        String start = "딜러: " + win + "승 " + draw + "무 " + lose + "패" + System.lineSeparator();
        System.out.println(start + dealerResult);
    }
}
