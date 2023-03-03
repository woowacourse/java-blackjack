package view;

import dto.DealerResult;
import dto.DrawnCardsInfo;
import dto.GameResult;
import dto.WinLoseResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.getProperty("line.separator");

    public void printCardSplitMessage(final List<DrawnCardsInfo> infos) {
        String names = infos.stream()
                .filter(info -> !info.getName().equals("딜러"))
                .map(info -> info.getName())
                .collect(Collectors.joining(DELIMITER));

        System.out.println(NEW_LINE + "딜러와 " + names + "에게 2장을 나누었습니다.");

        infos.stream()
                .forEach(info -> System.out.println(info.getName() + ": " + getCardsInfo(info.getDrawnCards())));
        System.out.println();
    }

    public void printPlayerCardInfo(final DrawnCardsInfo info) {
        System.out.println(info.getName() + "카드: " + getCardsInfo(info.getDrawnCards()));
    }

    private String getCardsInfo(final List<String> cards) {

        return cards.stream()
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerCardPickMessage() {
        System.out.println(NEW_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(final List<GameResult> results) {
        System.out.println();
        results.stream()
                .forEach(result -> System.out.println(
                        result.getName() + " 카드: " + getCardsInfo(result.getDrawnCards()) + " - 결과: "
                                + result.getScore()));
    }

    public void printResult(final List<WinLoseResult> winLoseResults,
                            final DealerResult dealerResult) {
        System.out.println(NEW_LINE + "## 최종 승패");

        System.out.println(
                dealerResult.getName() + ": " + dealerResult.getWinCount() + "승 " + dealerResult.getLoseCount() + "패");

        winLoseResults.forEach(result -> System.out.println(result.getName() + ": " + isWin(result.isWin())));
    }

    private String isWin(boolean isWin) {
        if (isWin) {
            return "승";
        }
        return "패";
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
