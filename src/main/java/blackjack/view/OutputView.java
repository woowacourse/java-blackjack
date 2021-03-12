package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.result.GameResult;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    public static String NEW_LINE = System.lineSeparator();

    public static void printInitSetting(final List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getNameAsString)
                .collect(Collectors.toList());
        System.out.printf(NEW_LINE + "딜러와 %s 에게 2장의 카드 나누어주었습니다.%n", String.join(", ", playerNames));
    }

    public static void printInitCards(final Dealer dealer, final List<Player> players) {
        printDealerInitCard(dealer);
        printPlayersInitCards(players);
    }

    public static void printParticipantCards(final Participant participant) {
        System.out.print(participant.getNameAsString() + "카드: ");
        List<String> playersCards = participant
                .getCardsAsList()
                .stream()
                .map(card -> card.getFaceValueAsInt() + card.getSuitAsString()).collect(Collectors.toList());
        System.out.print(String.join(", ", playersCards));
    }

    public static void printDealerReceiveMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        OutputView.printNewLine();
    }

    public static void printSummary(final GameResult gameResult) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        System.out.printf("%d승 %d무 %d패%n",
                gameResult.getDealerWinCounts(), gameResult.getDealerDrawCounts(), gameResult.getDealerLoseCounts());

        gameResult.getGameResult()
                .forEach((player, result) -> System.out.println(player.getNameAsString() + ": " + result.getResultAsString()));
    }

    public static void printResult(final List<Participant> participants) {
        for (Participant participant : participants) {
            printParticipantCards(participant);
            System.out.println(" - 결과: " + participant.getScore());
        }
        printNewLine();
    }

    public static void printNewLine() {
        System.out.print(NEW_LINE);
    }

    private static void printDealerInitCard(final Dealer dealer) {
        System.out.print("딜러: ");
        List<Card> dealerCards = dealer.getInitCardsAsList();
        dealerCards.forEach(dealerCard -> System.out.println(dealerCard.getFaceValueAsInt() + dealerCard.getSuitAsString()));
    }

    private static void printPlayersInitCards(final List<Player> players) {
        for (Player player : players) {
            printParticipantCards(player);
            printNewLine();
        }
    }

    public static void printProfits(final List<Player> players) {
        printNewLine();
        System.out.println("## 최종 수익");
        for (Player player : players) {
            System.out.printf("%s: %d", player.getNameAsString(), (int) player.getProfit());
            printNewLine();
        }
    }

    public static void printIllegalArgumentError(IllegalArgumentException e) {
        System.out.println(e.getMessage());
    }

    public static void printNumberFormatExceptionError(NumberFormatException e) {
        System.out.println(e.getMessage().replaceFirst(".*For input string: ", "해당 입력은 숫자가 아닙니다: "));
    }
}
