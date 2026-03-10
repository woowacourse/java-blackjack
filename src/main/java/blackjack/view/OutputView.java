package blackjack.view;

import blackjack.domain.*;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OutputView {

    public void printPlayerCardStatus(Player player, List<Card> cards) {
        List<String> cardNames = cards.stream()
                .map(Card::getCardName)
                .toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cardNames));
    }

    public void printFirstCardStatus(Dealer dealer, Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName).toList();
        System.out.println("\n딜러와 " + String.join(", ", playerNames) + "에게 " + "2장을 나누었습니다.");
        System.out.println("딜러카드: " + dealer.getInitialCards());
        for (Player player : players.getPlayers()) {
            printPlayerCardStatus(player, player.getCards());
        }
        System.out.println();
    }

    public void printDealerReceiveCard() {
        System.out.println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public void printScoreResult(Dealer dealer, Players players) {
        printParticipantGameResult(dealer, "\n딜러");
        for (Player player : players.getPlayers()) {
            printParticipantGameResult(player, player.getName());
        }
    }

    public void printBurst(String name) {
        System.out.println(name + "의 점수가 버스트 되었습니다.");
    }

    public void printGameResult(Map<Player, GameResult> gameResult) {
        System.out.println("\n## 최종 승패");
        Map<GameResult, Integer> dealerResult = new LinkedHashMap<>();

        for (GameResult result : gameResult.values()) {
            summaryGameResult(result, dealerResult);
        }
        printDealerGameResult(dealerResult);
        printPlayersGameResult(gameResult);
    }

    private static void printPlayersGameResult(Map<Player, GameResult> gameResult) {
        for (Player player : gameResult.keySet()) {
            System.out.println(player.getName() + ": " + gameResult.get(player).getStatus());
        }
    }

    private static void printDealerGameResult(Map<GameResult, Integer> dealerResult) {
        System.out.print("딜러: ");
        for (GameResult result : GameResult.values()) {
            Integer count = dealerResult.getOrDefault(result, 0);
            if (count != 0) {
                System.out.print(count + result.getStatus() + " ");
            }
        }
        System.out.println();
    }

    private void printParticipantGameResult(Participant participant, String name) {
        List<String> cardNames = participant.getCards().stream()
                .map(Card::getCardName).toList();
        System.out.println(name + "카드: " +
                String.join(", ", cardNames) +
                " - 결과: " + participant.getScore());
    }

    private static void summaryGameResult(GameResult result, Map<GameResult, Integer> dealerResult) {
        if (result.equals(GameResult.WIN)) {
            dealerResult.put(GameResult.LOSE, dealerResult.getOrDefault(GameResult.LOSE, 0) + 1);
        }
        if (result.equals(GameResult.LOSE)) {
            dealerResult.put(GameResult.WIN, dealerResult.getOrDefault(GameResult.WIN, 0) + 1);
        }
        if (result.equals(GameResult.DRAW)) {
            dealerResult.put(GameResult.DRAW, dealerResult.getOrDefault(GameResult.DRAW, 0) + 1);
        }
    }
}
