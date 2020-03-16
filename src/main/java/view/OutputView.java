package view;

import domain.GameResult;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import domain.user.User;

import java.util.Map;
import java.util.stream.Collectors;

import static domain.rule.DealerRule.DRAW_MAX_SCORE;

public class OutputView {
    public static void printNameFormat() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printCardFormat(User user) {
        System.out.println(user.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printFirstDrawFormat(Dealer dealer, Players players) {
        String playerNames = players.get()
                .stream()
                .map(Player::getName)
                .collect(Collectors.joining(","));
        StringBuilder stringBuilder = new StringBuilder()
                .append(dealer.getName())
                .append("와 ")
                .append(playerNames)
                .append("에게 2장의 카드를 나누었습니다.");

        System.out.println(stringBuilder);
    }

    public static void printStatus(User user) {
        System.out.println(user.getStatus());
    }

    public static void printStatus(Players players) {
        for (Player player : players.get()) {
            System.out.println(player.getStatus());
        }
    }

    public static void printDealerDraw(Dealer dealer) {
        System.out.println(dealer.getName() + "는" + DRAW_MAX_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printStatusWithScore(User user) {
        System.out.println(user.getStatus() + " - 결과 : " + user.getScore());
    }

    public static void printStatusWithScore(Players players) {
        for (Player player : players.get()) {
            printStatusWithScore(player);
        }
    }

    public static void printGameResult(GameResult gameResult, Dealer dealer) {
        System.out.println("## 최종 승패\n");
        System.out.println(dealer.getName() + ": " + gameResult.calculateDealerWins() + "승 " + gameResult.calculateDealerLoses() + "패");

        Map<String, Boolean> playerResult = gameResult.getPlayerResult();
        for (String name : playerResult.keySet()) {
            System.out.print(name + ": ");
            System.out.println(makeResultWord(playerResult.get(name)));
        }
    }

    private static String makeResultWord(Boolean result) {
        if (result) {
            return "승";
        }
        return "패";
    }
}
