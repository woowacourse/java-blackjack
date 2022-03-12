package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.WinResult;
import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printFatalErrorMessage(String message) {
        System.err.println(message);
        System.err.println("심각한 오류가 발생하여 프로그램을 종료합니다.");
    }

    public static void printStartMessage(Dealer dealer, List<Player> players) {
        List<String> playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.toUnmodifiableList());
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(), String.join(", ", playerNames));
        System.out.println();
    }

    public static void printDealerFirstCard(Dealer dealer) {
        Card firstCard = dealer.getCards().get(0);
        System.out.printf("%s: %s", dealer.getName(), createCardInfoString(firstCard));
        System.out.println();
    }

    public static void printParticipantCards(Participant participant, int score) {
        String cardsInfo = participant.getCards()
                .stream()
                .map(OutputView::createCardInfoString)
                .collect(Collectors.joining(", "));

        System.out.printf("%s카드: %s - 합계: %d", participant.getName(), cardsInfo, score);
        System.out.println();
    }

    private static String createCardInfoString(Card card) {
        return card.getDenomination().getName() + card.getPattern().getName();
    }

    public static void printBlackJackMessage(String name) {
        System.out.printf("%s는 블랙잭입니다!!", name);
        System.out.println();
    }

    public static void printBustMessage(String name) {
        System.out.printf("%s의 카드의 합이 21을 넘었습니다.", name);
        System.out.println();
    }

    public static void printDealerHitMessage() {
        System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void printCardResultMessage() {
        System.out.println();
        System.out.println("## 최종 카드");
    }

    public static void printWinResult(WinResult winResult) {
        System.out.println();
        System.out.println("## 최종 승패");
        printDealerWinResult(winResult.getDealerResult());
        printPlayersWinResult(winResult.getPlayersResult());
    }

    private static void printDealerWinResult(Map<Outcome, Integer> dealerResult) {
        System.out.printf("딜러: %s", createDealerWinResultString(dealerResult));
        System.out.println();
    }

    private static String createDealerWinResultString(Map<Outcome, Integer> dealerResult) {
        return dealerResult.keySet()
                .stream()
                .filter(outcome -> dealerResult.get(outcome) > 0)
                .map(outcome -> String.format("%d%s", dealerResult.get(outcome), outcome.getName()))
                .collect(Collectors.joining(" "));
    }

    private static void printPlayersWinResult(Map<String, Outcome> playersResult) {
        playersResult.keySet()
                .forEach(playerName -> printPlayerWinResult(playerName, playersResult.get(playerName)));
    }

    private static void printPlayerWinResult(String playerName, Outcome outcome) {
        System.out.printf("%s: %s", playerName, outcome.getName());
        System.out.println();
    }
}
