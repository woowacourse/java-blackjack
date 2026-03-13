package view;

import java.util.List;
import java.util.Map;
import model.card.Card;
import model.judgement.DealerResult;
import model.judgement.PlayerResult;
import model.judgement.Profit;
import model.paticipant.Dealer;
import model.paticipant.Participant;
import model.paticipant.Player;
import model.paticipant.Players;

public class OutputView {

    private OutputView() {
    }

    public static void printCardOpen(Players players) {
        List<String> names = players.getPlayers()
                .stream()
                .map(Player::getName)
                .toList();

        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    public static void printCardByPlayers(Players players) {
        players.getPlayers().forEach(OutputView::printCardByPlayer);
        System.out.println();
    }

    public static void printCardByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println(dealer.getName() + ": " + card);
    }

    public static void printCardByPlayer(Player player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s카드: %s%n", player.getName(), String.join(", ", cards));
    }


    public static void printCardByPlayerWithScore(Participant participant) {
        int sum = participant.calculateTotalScore();
        List<String> cards = participant.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s 카드: %s - 결과: %d%n", participant.getName(), String.join(", ", cards), sum);
    }

    private static String convert(Card card) {
        return card.value().getSymbol() + card.shape().getShape();
    }

    public static void printToOpenDealerNewCard(Dealer dealer) {
        System.out.println();
        System.out.printf("%s는 16 이하라 한장의 카드를 더 받았습니다.%n", dealer.getName());
    }

    public static void printBlank() {
        System.out.println();
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printResultByDealer(DealerResult dealerResult) {
        System.out.print("딜러: ");
        StringBuilder result = new StringBuilder();
        if (dealerResult.winCount() > 0) {
            result.append(dealerResult.winCount()).append("승 ");
        }
        if (dealerResult.drawCount() > 0) {
            result.append(dealerResult.drawCount()).append("무 ");
        }
        if (dealerResult.loseCount() > 0) {
            result.append(dealerResult.loseCount()).append("패");
        }
        System.out.println(result.toString().trim());
    }

    public static void printResultByPlayers(PlayerResult playerResult) {
        playerResult.getResult()
                .forEach((player, status) -> System.out.printf("%s: %s%n", player.getName(), status.getName()));
    }

    public static void printFinalProfitHeader() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public static void printProfitByDealer(Profit profit) {
        System.out.printf("딜러: %d%n", profit.value());
    }

    public static void printProfitByPlayers(Map<Player, Profit> profits) {
        profits.forEach((player, profit) -> 
                System.out.printf("%s: %d%n", player.getName(), profit.value())
        );
    }
}