package view;

import domain.Card;
import domain.GameResult;
import domain.Winning;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printDealerCardWithHidden(final Card card) {
        System.out.println("딜러: " + getCardName(card));
    }

    public void printPlayerCards(final Map<String, List<Card>> playerToCard) {
        playerToCard.forEach((playerName, cards) ->
            System.out.println(getEachPlayerCards(playerName, cards)));
    }

    public void printDealerCardWithScore(final List<Card> cards, final int score) {
        printCardWithScore("딜러 ", cards, score);
    }

    public void printPlayerCardWithScore(final Map<String, List<Card>> playerToCard,
        final Map<String, Integer> playerToScore) {
        playerToCard.forEach((playerName, cards) -> {
            int score = playerToScore.get(playerName);
            printCardWithScore(playerName, cards, score);
        });
    }

    public void printEachPlayerCards(final String playerName, final List<Card> cards) {
        System.out.println(getEachPlayerCards(playerName, cards));
    }

    private void printCardWithScore(final String playerName, final List<Card> cards, final int score) {
        System.out.println(getEachPlayerCards(playerName, cards) + " - 결과: " + score);
    }

    private String getEachPlayerCards(final String playerName, final List<Card> cards) {
        StringBuilder stringBuilder = new StringBuilder(playerName);
        stringBuilder.append("카드: ");
        List<String> cardNames = new ArrayList<>();
        for (Card card : cards) {
            cardNames.add(getCardName(card));
        }
        stringBuilder.append(String.join(", ", cardNames));
        return stringBuilder.toString();
    }

    private String getCardName(final Card card) {
        List<String> cardName = card.getCardName();
        return cardName.get(0) + cardName.get(1);
    }

    public void printGameResult(final GameResult gameResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerResult(gameResult.getDealerResult());
        printPlayerResults(gameResult.getPlayerResults());
    }

    private void printDealerResult(final Map<Winning, Integer> dealerResult) {
        StringBuilder dealerResultMessage = new StringBuilder("딜러: ");
        for (Winning winning : dealerResult.keySet()) {
            int count = dealerResult.getOrDefault(winning, 0);
            dealerResultMessage.append(getDealerEachResult(winning.getName(), count));
        }
        System.out.println(dealerResultMessage);
    }

    private String getDealerEachResult(final String winning, final int count) {
        if (count != 0) {
            return count + winning + " ";
        }
        return "";
    }

    private void printPlayerResults(final Map<String, Winning> playerResults) {
        playerResults.forEach((playerName, winning) ->
            System.out.println(playerName + ": " + winning.getName()));
    }

    public void printDealerHitMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printInitMessage(List<String> playerNames) {
        System.out.println();
        System.out.println("딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public void printErrorMessage(String message) {
        System.out.println(message);
    }
}
