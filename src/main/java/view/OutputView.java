package view;

import domain.user.Dealer;

import java.util.Map;

import static domain.DealerRule.DRAW_MAX_SCORE;

public class OutputView {
    public static void printNameFormat() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public static void printCardFormat(String name) {
        System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printDrawTurn(String name, String[] names) {
        String playerNames = String.join(", ", names);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(name)
                .append("와 ")
                .append(playerNames)
                .append("에게 2장의 카드를 나누었습니다.");

        System.out.println(stringBuilder.toString());
    }

    public static void printStatus(String status) {
        System.out.println(status);
    }

    public static void printAutoDraw(Dealer dealer) {
        System.out.println(dealer.getName() + "는" + DRAW_MAX_SCORE + "이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printStatusWithScore(String status, int score) {
        System.out.println(status + " - 결과 : " + score);
    }

    public static void printGameResult(Map<String, Boolean> gameResult, Map<String, Integer> dealerResult) {
        System.out.println("## 최종 승패\n");
        for (String name : dealerResult.keySet()) {
            System.out.println(name + ": " + dealerResult.get(name) + "승 " + (gameResult.size() - dealerResult.get(name)) + "패");
        }
        for (String name : gameResult.keySet()) {
            System.out.print(name + ": ");
            if (gameResult.get(name)) {
                System.out.println("승");
            } else {
                System.out.println("패");
            }
        }
    }
}
