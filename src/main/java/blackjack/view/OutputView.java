package blackjack.view;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final OutputView INSTANCE = new OutputView();
    private static final String ERROR = "[ERROR] : ";

    private OutputView() {
    }

    public static OutputView getInstance() {
        return INSTANCE;
    }

    public void printRequestPlayerNames() {
        System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
    }

    public void printInitialCardDistribution(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printDealerCard(String cardName) {
        System.out.println("딜러: " + cardName);
    }

    public void printEachPlayerCards(String playerName, List<String> cardNames) {
        System.out.println(playerName + "카드: " + String.join(", ", cardNames));
    }

    public void printRequestIntention(String playerName) {
        System.out.println(playerName + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public void printDealerReceived() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printFinalCards(String name, List<String> cardNames, int score) {
        System.out.println(name + "카드: " + String.join(", ", cardNames) + " - 결과: " + score);
    }

    public void printDealerResults(Map<String, Integer> dealerResults){
        System.out.print(System.lineSeparator() + "## 최종 승패" + System.lineSeparator() + "딜러: ");

        dealerResults.keySet().forEach(result -> {
            if (dealerResults.get(result) > 0){
                System.out.print(dealerResults.get(result) + result);
            }
        });

        System.out.println();
    }

    public void printPlayerResult(String playerName, String playerResult) {
        System.out.println(playerName + ": " + playerResult);
    }

    public void printErrorMessage(String errorMessage) {
        System.out.println(ERROR + errorMessage);
    }
}
