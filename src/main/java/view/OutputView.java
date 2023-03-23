package view;

import domain.card.Card;
import domain.game.GameResult;
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
        System.out.println();
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
        System.out.println("## 최종 수익");
        printDealerProfit(gameResult.getDealerProfit());
        printPlayerProfits(gameResult.getPlayerProfits());
    }

    private void printDealerProfit(final int dealerProfit) {
        printEachProfit("딜러", dealerProfit);
    }

    private void printPlayerProfits(final Map<String, Integer> playerProfits) {
        playerProfits.forEach(this::printEachProfit);
    }

    private void printEachProfit(final String name, final int profit) {
        System.out.println(name + ": " + profit);
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
