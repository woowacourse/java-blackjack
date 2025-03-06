package view;

import java.util.List;
import java.util.Map;
import model.Card;
import model.Dealer;
import model.GameResult;
import model.Player;
import model.PlayerWinningResult;
import model.Players;

public class OutputView {

    public static void printInitialDealResult(Dealer dealer, Players players){
        printCardDivision(players);
        Card firstDealerCard = dealer.getParticipantHand().getFirstHand();
        System.out.println("딜러카드: " + firstDealerCard.getCardName());

        for (Player player : players.getPlayers()) {
            List<String> playerCards = player.getHandCards().stream()
                    .map(Card::getCardName)
                    .toList();
            System.out.println(player.getName() + "카드: " + String.join(", ", playerCards));
        }
    }

    private static void printCardDivision(Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .toList();
        System.out.printf("\n딜러와 %s에게 2장을 나누었습니다.\n", String.join(", ", playerNames));
    }

    public static void printHitOrStand(Player player) {
        System.out.println(player.getName() + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
    }

    public static void printHitResult(Player player){
        List<String> cardsName = player.getHandCards().stream().map(Card::getCardName).toList();
        System.out.println(player.getName() + "카드: " + String.join(", ", cardsName));
    }

    public static void printDealerDealResult(){
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalScore(Dealer dealer, Players players) {
        List<String> dealerCardNames = dealer.getHandCards().stream().map(Card::getCardName).toList();
        System.out.print("딜러카드: " + String.join(", ", dealerCardNames));
        System.out.println(" - 결과: " + dealer.getParticipantHand().calculateFinalScore());

        for (Player player : players.getPlayers()) {
            List<String> playerCardNames = player.getHandCards().stream().map(Card::getCardName).toList();
            System.out.print(player.getName() + "카드: " + String.join(", ", playerCardNames));
            System.out.println(" - 결과: " + player.getParticipantHand().calculateFinalScore());
        }
    }

    public static void printDealerFinalResult(Map<GameResult, Integer> dealerWinning) {
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + getGameResultMessage(dealerWinning));
    }

    private static String getGameResultMessage(Map<GameResult, Integer> dealerWinning) {
        String message = "";
        for (GameResult gameResult : GameResult.values()) {
            if (dealerWinning.containsKey(gameResult)) {
                message += (dealerWinning.get(gameResult) + gameResult.getResultMeaning() + " ");
            }
        }
        return message;
    }

    public static void printPlayerFinalResult(Player player, GameResult playerResult) {
        System.out.println(player.getName() + ": " + playerResult.getResultMeaning());
    }

    public static void printPlayerFinalResultV2(PlayerWinningResult playerWinningResult) {
        Map<Player, GameResult> playerResults = playerWinningResult.getResult();
        for (Player player : playerResults.keySet()) {
            GameResult playerResult = playerWinningResult.getResult().get(player);
            System.out.println(player.getName() + ": " + playerResult.getResultMeaning());
        }
    }
}
