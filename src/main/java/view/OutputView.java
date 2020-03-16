package view;

import dto.ResponsePlayerDTO;
import dto.ResponseWinningResultDTO;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";
    private static final int DEALER_INDEX = 0;
    private static final int START_USER_INDEX = 1;

    private OutputView() {
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printInitialResult(List<ResponsePlayerDTO> result) {
        StringBuilder sb = new StringBuilder();
        sb.append(System.lineSeparator());
        for (int i = 0; i < result.size() - 1; i++) {
            sb.append(result.get(i).getName());
            sb.append(DELIMITER);
        }
        sb.append(result.get(result.size() - 1).getName());
        System.out.println(sb.toString() + "에게 2장의 카드를 나누었습니다.");
        printDealerCard(result);
        printUserCard(result);
        System.out.println();
    }

    private static void printDealerCard(List<ResponsePlayerDTO> result) {
        String firstDealerCard = result.get(DEALER_INDEX).getCardNumbers().substring(0,
                result.get(DEALER_INDEX).getCardNumbers().indexOf(DELIMITER));
        System.out.println(result.get(DEALER_INDEX).getName() + "카드: " + firstDealerCard);
    }

    private static void printUserCard(List<ResponsePlayerDTO> result) {
        for (int i = START_USER_INDEX; i < result.size(); i++) {
            printUserCard(result.get(i));
        }
    }

    public static void printUserCard(ResponsePlayerDTO result) {
        System.out.println(result.getName() + "카드: " + result.getCardNumbers());
    }

    public static void printDealerAdditionalCard() {
        System.out.println("\n딜러는 16이하라 카드를 한장 더 받았습니다.");
    }

    public static void printFinalResult(List<ResponsePlayerDTO> result) {
        System.out.println();
        for (ResponsePlayerDTO responsePlayerDTO : result) {
            System.out.println(responsePlayerDTO.getName() + "카드: "
                    + responsePlayerDTO.getCardNumbers() + " - 결과: " + responsePlayerDTO.getScore());
        }
    }

    public static void printWinningResult(ResponseWinningResultDTO responseWinningResultDTO) {
        System.out.println("\n## 최종 승패");
        Map<String, Boolean> winningPlayer = responseWinningResultDTO.getWinningPlayer();
        for (String result : getWinningResult(winningPlayer)) {
            System.out.println(result);
        }
    }

    public static List<String> getWinningResult(Map<String, Boolean> winningPlayer) {
        int allUserWinCount = (int) winningPlayer.values().stream().filter(win -> win).count();
        int allUserLoseCount = winningPlayer.values().size() - allUserWinCount;
        List<String> result = new ArrayList<>(
                Collections.singletonList("딜러: " + allUserLoseCount + "승 " + allUserWinCount + "패"));
        result.addAll(winningPlayer.entrySet().stream()
                .map(entry -> winString(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList()));
        return result;
    }

    private static String winString(String name, boolean isWin) {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (isWin) {
            sb.append(": 승");
            return sb.toString();
        }
        sb.append(": 패");
        return sb.toString();
    }
}
