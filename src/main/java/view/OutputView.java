package view;

import domain.GameResult;
import domain.Name;
import dto.CardStatusDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OutputView {
    
    private static final String INITIAL_CARD_DISTRIBUTION = "%s와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_RECEIVE_CARD = "%s는 16이하라 %d장의 카드를 더 받았습니다.";
    private static final String NAME_DELIMITER = ", ";
    private static final String FINAL_SCORE = " - 결과: %d";
    private static final String FINAL_RESULT_TITLE = "## 최종 승패";
    private static final String RESULT_NAME_FORMAT = "%s: ";
    private static final String PARTICIPANT_CARD_STATUS_FORMAT = "%s 카드: %s ";
    private static final int NONE = 0;

    public OutputView() {
    }

    public void printFirstCardDistribution(String dealerName, List<String> playerNames) {
        System.out.printf("\n" + INITIAL_CARD_DISTRIBUTION, dealerName, String.join(NAME_DELIMITER, playerNames));
    }

    public void printCardStatus(String name, List<CardStatusDto> cards) {
        System.out.printf("\n" + PARTICIPANT_CARD_STATUS_FORMAT, name, String.join(NAME_DELIMITER, convertCardStatusToString(cards)));
    }

    private List<String> convertCardStatusToString(List<CardStatusDto> cards) {
        return cards.stream()
                .map(card -> card.getLetterExpression() + card.getShapeName())
                .collect(Collectors.toList());
    }

    public void printDealerMoreCard(String dealerName, int cardCount) {
        if (cardCount != NONE) {
            System.out.printf("\n\n" + DEALER_RECEIVE_CARD + "\n", dealerName, cardCount);
        }
    }

    public void printCardAndScore(String playerName, List<CardStatusDto> cards, int totalScore) {
        printCardStatus(playerName, cards);
        System.out.printf(FINAL_SCORE, totalScore);
    }

    public void printFinalResult(String dealerName, Map<Name, GameResult> result) {
        System.out.println("\n\n" + FINAL_RESULT_TITLE);
        printDealerFinalResult(dealerName, result);
        printPlayersFinalResult(result);
    }

    private void printDealerFinalResult(String dealerName, Map<Name, GameResult> results) {
        int winCount = 0;
        int loseCount = 0;
        int drawCount = 0;
        for (GameResult result: results.values()) {
            winCount = getWinCount(winCount, result);
            drawCount = getDrawCount(drawCount, result);
            loseCount = getLoseCount(loseCount, result);
        }
        printDealerResultCount(dealerName, winCount, loseCount, drawCount);
    }

    private int getLoseCount(int loseCount, GameResult result) {
        if (result == GameResult.WIN) {
            loseCount++;
        }
        return loseCount;
    }

    private int getDrawCount(int drawCount, GameResult result) {
        if (result == GameResult.DRAW) {
            drawCount++;
        }
        return drawCount;
    }

    private int getWinCount(int winCount, GameResult result) {
        if (result == GameResult.LOSE) {
            winCount++;
        }
        return winCount;
    }

    private void printDealerResultCount(String dealerName, int winCount, int loseCount, int drawCount) {
        System.out.printf(RESULT_NAME_FORMAT, dealerName);
        if (winCount != NONE) {
            System.out.print(winCount + GameResult.WIN.getExpression());
        }
        if (drawCount != NONE) {
            System.out.print(drawCount + GameResult.DRAW.getExpression());
        }
        if (loseCount != NONE) {
            System.out.print(loseCount + GameResult.LOSE.getExpression());
        }
    }

    private void printPlayersFinalResult(Map<Name, GameResult> results) {
        System.out.println();
        results.forEach((name, result) -> System.out.printf(RESULT_NAME_FORMAT + result.getExpression() + "\n" , name.getValue()));
    }
    
}
