package view;

import game.GameResult;
import card.Card;
import user.User;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    public void displayDealerAddCard() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
    }

    public void displayOpenCards(String name, List<Card> cards) {
        List<String> printCards = cards.stream()
                .map(card -> CardConverter.createTrumpCard(card.cardShape(), card.cardNumber())).toList();
        System.out.print(name + "카드: " + String.join(", ", printCards) + "\n");
    }

    public void displayDealerGameResult(int winCount, int loseCount, int drawCount) {
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러: %d승 %d패 %d 무승부\n", winCount, loseCount, drawCount);
    }

    public void displayGameResult(Map<User, GameResult> gameResult) {
        gameResult.forEach((key, value) -> displayUserGameResult(
                key.getName(),
                convertGameResult(value)));

        System.out.println();
    }

    private String convertGameResult(GameResult value) {
        if (value.equals(GameResult.WIN)) {
            return "승";
        }
        if (value.equals(GameResult.LOSE)) {
            return "패";
        }
        return "무승부";
    }

    private void displayUserGameResult(String name, String gameResult) {
        System.out.println(name + ": " + gameResult);
    }

    public void displayOpenCardsResult(String name, List<Card> printCards, int score) {
        List<String> displayCards = printCards.stream()
                .map(card -> CardConverter.createTrumpCard(
                        card.cardShape(),
                        card.cardNumber()
                )).toList();
        System.out.print(name + "카드: " + String.join(", ", displayCards + (" - 결과: " + score)) + "\n");
    }

    public void displayRewards(Map<User, Long> rewards) {
        System.out.println("\n## 최종 수익");
        for (Entry<User, Long> rewardEntry : rewards.entrySet()) {
            System.out.println(rewardEntry.getKey().getName() + ": " + rewardEntry.getValue());
        }
    }

    public void displayError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
