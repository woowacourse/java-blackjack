package view;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import domain.result.BlackjackResult;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printInitCards(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayersName();
        String names = playerNames.stream()
                .reduce((a, b) -> String.join(", ", a, b))
                .orElse("");

        System.out.println("딜러와 " + names + "에게 2장을 나누었습니다.");

        Card dealerCard = dealer.getOwnedCards().getFirst();
        String dealerCardContent = dealerCard.getNumberValue() + dealerCard.getShapeValue();
        System.out.printf("딜러카드: %s\n", dealerCardContent);

        for (String name : playerNames) {
            Player player = players.findByName(name);
            System.out.printf("%s카드: %s\n", name, getCardContents(player.getOwnedCards()));
        }
        System.out.println();
    }

    public void printCardsByName(Player player) {
        System.out.printf("%s카드: %s\n", player.getName(), getCardContents(player.getOwnedCards()));
    }

    public void printDealerReceived() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public void printFinalCardsContent(Dealer dealer, Players players) {
        System.out.printf("딜러카드: %s - 결과: %d\n", getCardContents(dealer.getOwnedCards()), dealer.getScore());

        List<String> playerNames = players.getPlayersName();
        for (String name : playerNames) {
            Player player = players.findByName(name);
            System.out.printf("%s카드: %s - 결과: %d\n", name, getCardContents(player.getOwnedCards()), player.getScore());
        }
        System.out.println();
    }

    public void printResult(Dealer dealer, Players players) {
        System.out.println("## 최종 승패");

        Map<String, BlackjackResult> playerResults = calculatePlayerResults(dealer, players);
        printDealerResult(playerResults);
        printPlayerResults(playerResults);
    }

    private Map<String, BlackjackResult> calculatePlayerResults(Dealer dealer, Players players) {
        Map<String, BlackjackResult> playerResults = new LinkedHashMap<>();

        for (String name : players.getPlayersName()) {
            Player player = players.findByName(name);
            BlackjackResult result = player.getBlackjackResult(dealer);
            playerResults.put(name, result);
        }

        return playerResults;
    }

    private void printDealerResult(Map<String, BlackjackResult> playerResults) {
        int dealerWinCount = (int) playerResults.values().stream()
                .filter(result -> result == BlackjackResult.LOSE)
                .count();
        int dealerLoseCount = (int) playerResults.values().stream()
                .filter(result -> result == BlackjackResult.WIN)
                .count();
        int dealerDrawCount = (int) playerResults.values().stream()
                .filter(result -> result == BlackjackResult.DRAW)
                .count();

        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패 " + dealerDrawCount + "무");
    }

    private void printPlayerResults(Map<String, BlackjackResult> playerResults) {
        playerResults.forEach((name, result) ->
                System.out.println(name + ": " + result.getValue()));
    }

    private String getCardContents(List<Card> cards) {
        return cards
                .stream()
                .map(card -> card.getNumberValue() + card.getShapeValue())
                .reduce((a, b) -> String.join(", ", a, b))
                .orElse("");
    }
}
