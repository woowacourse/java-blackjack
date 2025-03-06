package view;

import domain.Card;
import domain.Cards;
import domain.Dealer;
import domain.Number;
import domain.Participant;
import domain.Player;
import domain.ResultStatus;
import domain.Symbol;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printInitialParticipant(Dealer dealer, List<Player> allPlayers) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.%n", dealer.getName(), findPlayerNames(allPlayers));
        printInitialDealerCard(dealer);
        printAllPlayerCards(allPlayers);
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static void printAllPlayerCards(List<Player> allPlayers) {
        for (Player player : allPlayers) {
            System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
        }
    }

    private static String findPlayerNames(List<Player> allPlayers) {
        return allPlayers.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(", "));
    }

    private static void printInitialDealerCard(Dealer dealer) {
        System.out.printf("%s카드: %s%n", dealer.getName(), convertCardToMessage(dealer.getInitialCard()));
    }

    private static String convertCardsToMessage(Cards cards) {
        List<Card> allCards = cards.getCards();
        return allCards.stream()
            .map(OutputView::convertCardToMessage)
            .collect(Collectors.joining(", "));
    }

    private static String convertCardToMessage(Card card) {
        Symbol symbol = card.getSymbol();
        Number number = card.getNumber();
        return number.getFaceValue() + symbol.getName();
    }

    public static void printPlayerCard(Player player) {
        System.out.printf("%s카드: %s%n", player.getName(), convertCardsToMessage(player.getCards()));
    }

    public static void printFinalParticipant(Dealer dealer, List<Player> allPlayers) {
        printFinalDealerCard(dealer);
        printFinalAllPlayersCards(allPlayers);
    }

    private static void printFinalDealerCard(Dealer dealer) {
        System.out.printf("%s카드: %s - 결과: %d%n",
                dealer.getName(),
                convertCardsToMessage(dealer.getCards()),
                dealer.getTotalNumberSum());
    }

    private static void printFinalAllPlayersCards(List<Player> allPlayers) {
        for (Player player : allPlayers) {
            System.out.printf("%s카드: %s - 결과: %d%n",
                    player.getName(),
                    convertCardsToMessage(player.getCards()),
                    player.getTotalNumberSum());
        }
    }

    public static void printGameResult(Map<String, ResultStatus> result) {
        Map<ResultStatus, Integer> counts = countStatusResult(result);
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d패 %d무%n",
            counts.get(ResultStatus.LOSE), counts.get(ResultStatus.WIN), counts.get(ResultStatus.PUSH));
        for (String name : result.keySet()) {
            System.out.printf("%s: %d승 %d패 %d무%n",
                name, counts.get(ResultStatus.WIN), counts.get(ResultStatus.LOSE), counts.get(ResultStatus.PUSH));
        }
    }

    private static Map<ResultStatus, Integer> countStatusResult(Map<String, ResultStatus> result) {
        Map<ResultStatus, Integer> counts = ResultStatus.initMap();
        for (String playerName : result.keySet()) {
            ResultStatus resultStatus = result.get(playerName);
            counts.put(resultStatus, counts.get(resultStatus) + 1);
        }
        return counts;
    }
}
