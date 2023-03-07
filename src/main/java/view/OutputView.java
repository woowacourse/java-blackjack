package view;

import dto.BlackJackResult;
import dto.DealerWinLoseResult;
import dto.DrawnCardsInfo;
import dto.ParticipantResult;
import dto.WinLoseResult;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String RESULT_DELIMITER = ": ";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String WIN = "승";
    private static final String LOSE = "패";

    public void printCardSplitMessage(final List<DrawnCardsInfo> infos) {
        String names = infos.stream()
                .filter(info -> !info.getName().equals("딜러"))
                .map(info -> info.getName())
                .collect(Collectors.joining(NAME_DELIMITER));

        System.out.println(NEW_LINE + "딜러와 " + names + "에게 2장을 나누었습니다.");

        infos.stream()
                .forEach(info -> System.out.println(
                        info.getName() + RESULT_DELIMITER + getCardsInfo(info.getDrawnCards())));
        System.out.println();
    }

    public void printPlayerCardInfo(final DrawnCardsInfo info) {
        System.out.println(info.getName() + "카드: " + getCardsInfo(info.getDrawnCards()));
    }

    private String getCardsInfo(final List<String> cards) {
        return cards.stream()
                .collect(Collectors.joining(NAME_DELIMITER));
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

    public void printWinLoseResult(final List<WinLoseResult> winLoseResults,
                                   final DealerWinLoseResult dealerWinLoseResult) {
        System.out.println(NEW_LINE + "## 최종 수익");

        System.out.println(
                dealerWinLoseResult.getName() + RESULT_DELIMITER + dealerWinLoseResult.getWinCount() + "승 "
                        + dealerWinLoseResult.getLoseCount() + "패");

        winLoseResults.forEach(
                result -> System.out.println(result.getName() + RESULT_DELIMITER + getWinLoseFormat(result.isWin())));
    }

    private String getWinLoseFormat(final boolean isWin) {
        if (isWin) {
            return WIN;
        }
        return LOSE;
    }

    public void printExceptionMessage(final String message) {
        System.out.println(message);
    }

    public void printResult(final List<BlackJackResult> results) {
        System.out.println(NEW_LINE + "## 최종 수익");
        results
                .forEach(result -> System.out.println(result.getName() + ": " + (int) result.getAccount()));
    }
}
