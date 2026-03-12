package view;

import java.util.List;
import model.Card;
import model.Dealer;
import model.Participant;
import model.Player;

public class OutputView {

    public static void printCardOpen(List<Player> players) {
        List<String> names = players.stream()
                .map(Player::getName)
                .toList();
        System.out.println();
        System.out.printf("딜러와 %s에게 2장을 나누었습니다.%n", String.join(", ", names));
    }

    public static void printCardByPlayers(List<Player> players) {
        players.forEach(OutputView::printCardByPlayer);
        System.out.println();
    }

    public static void printCardByDealer(Dealer dealer) {
        Card firstCard = dealer.getCards().getFirst();
        String card = convert(firstCard);
        System.out.println(dealer.getName() + "카드: " + card);
    }

    public static void printCardByPlayer(Player player) {
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s카드: %s%n", player.getName(), String.join(", ", cards));
    }


    public static void printCardByPlayerWithScore(Participant player) {
        int sum = player.calculateTotalScore();
        List<String> cards = player.getCards()
                .stream()
                .map(OutputView::convert)
                .toList();
        System.out.printf("%s카드: %s - 결과: %d%n", player.getName(), String.join(", ", cards), sum);
    }

    private static String convert(Card card) {
        return card.value().getSymbol() + card.shape().getShape();
    }

    public static void printToOpenDealerNewCard(String name) {
        System.out.println();
        System.out.printf("%s는 16 이하라 한장의 카드를 더 받았습니다.%n", name);
    }

    public static void printBlank() {
        System.out.println();
    }

    public static void printFinalResultHeader() {
        System.out.println();
        System.out.println("##최종 승패");
    }

    public static void printDealerResult(int winCount, int loseCount, int drawCount) {
        System.out.print("딜러: ");
        StringBuilder result = new StringBuilder();
        if (winCount > 0) {
            result.append(winCount).append("승 ");
        }
        if (drawCount > 0) {
            result.append(drawCount).append("무 ");
        }
        if (loseCount > 0) {
            result.append(loseCount).append("패");
        }
        System.out.println(result.toString().trim());
    }

    public static void printBettingResultHeader() {
        System.out.println();
        System.out.println("## 최종 수익");
    }

    public static void printBettingResult(List<Participant> participants) {
        for (Participant participant : participants) {
            if (participant instanceof Dealer dealer) {
                System.out.println(participant.getName() + ": " + dealer.getProfit());
            } else if (participant instanceof Player player) {
                System.out.println(participant.getName() + ": " + player.getProfit());
            }
        }
    }

    public static void printErrorMessage(final String message) {
        System.out.printf("%s%n", message);
    }
}