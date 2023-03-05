package view;

import dto.response.DrawnCardsInfo;
import dto.response.ParticipantResult;
import dto.response.WinLoseResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";
    private static final String NEW_LINE = System.getProperty("line.separator");
    public static final String WIN = "승";
    public static final String LOSE = "패";

    public void printCardSplitMessage(final List<DrawnCardsInfo> infos) {
        String names = infos.stream()
                .filter(info -> !info.getName().equals("딜러"))
                .map(info -> info.getName())
                .collect(Collectors.joining(DELIMITER));

        System.out.println(NEW_LINE + "딜러와 " + names + "에게 2장을 나누었습니다.");

        infos.forEach(info -> System.out.println(info.getName() + ": " + getCardsInfo(info.getDrawnCards())));
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

    public void printParticipantResults(final List<ParticipantResult> results) {
        System.out.println();
        results.stream()
                .forEach(result -> System.out.println(
                        result.getName() + " 카드: " + getCardsInfo(result.getDrawnCards()) + " - 결과: "
                                + result.getScore()));
    }

    public void printWinLoseResult(final String dealerName,
                                   final List<WinLoseResult> winLoseResults) {
        System.out.println(NEW_LINE + "## 최종 승패");

        int dealerLoseCount = (int) winLoseResults.stream()
                .filter(WinLoseResult::isWin)
                .count();
        int dealerWinCount = winLoseResults.size() - dealerLoseCount;

        System.out.println(dealerName + ": " + dealerWinCount + "승 " + dealerLoseCount + "패");
        winLoseResults.forEach(result -> printPlayerWinLoseResult(result));
    }

    private void printPlayerWinLoseResult(WinLoseResult result) {
        System.out.println(result.getName() + ": " + getWinLoseFormat(result.isWin()));
    }

    private String getWinLoseFormat(boolean isWin) {
        if (isWin) {
            return WIN;
        }
        return LOSE;
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }
}
