package view;

import controller.dto.GameResult;
import controller.dto.HandStatus;
import java.util.List;

public class OutputView {
    public void printAfterStartGame(final List<HandStatus> statuses) {
        System.out.println();
        StringBuilder builder = new StringBuilder("딜러와 ");

        List<String> playerNames = getPlayerNames(statuses);
        builder.append(String.join(", ", playerNames));
        builder.append("에게 2장을 나누었습니다.\n");

        for (HandStatus handStatus : statuses) {
            builder.append(handStatus.getCardInitStatus());
            builder.append("\n");
        }

        System.out.print(builder);
    }

    private List<String> getPlayerNames(final List<HandStatus> statuses) {
        return statuses.subList(1, statuses.size()).stream()
                .map(HandStatus::name)
                .toList();
    }

    public void printCardStatus(final HandStatus status) {
        System.out.print(status.getCardInitStatus());
    }

    public void printDealerPickMessage(final int count) {
        System.out.println();
        for (int index = 0; index < count; index++) {
            System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
        }
    }

    public void printResult(final List<HandStatus> currentHandStatuses, final List<Integer> scores) {
        System.out.println();
        for (int i = 0; i < scores.size(); i++) {
            HandStatus handStatus = currentHandStatuses.get(i);
            System.out.println(handStatus.getCardFinalStatus() + " - 결과: " + scores.get(i));
        }
    }

    // TODO: indent 줄이기
    public void printGameResult(final GameResult results) {
        System.out.println();
        System.out.println("## 최종 승패");
        int loseCount = 0;
        for (int i = 0; i < results.results().size(); i++) {
            if (results.results().get(i).isWin()) {
                loseCount++;
            }
        }
        System.out.println("딜러: " + (results.results().size() - loseCount) + "승 " + loseCount + "패");
        for (int i = 0; i < results.results().size(); i++) {
            System.out.println(results.results().get(i).name() + ": " + checkIsWin(results.results().get(i).isWin()));
        }
    }

    private String checkIsWin(final boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }
}
