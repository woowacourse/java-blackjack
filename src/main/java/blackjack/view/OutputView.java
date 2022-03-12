package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.WinResult;
import blackjack.domain.card.Card;
import blackjack.domain.dto.ParticipantInitialResponse;
import blackjack.domain.dto.ParticipantResponse;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }

    public static void printStartMessage(ParticipantInitialResponse dealer, List<ParticipantResponse> players) {
        List<String> playerNames = players.stream()
                .map(ParticipantResponse::getName)
                .collect(Collectors.toUnmodifiableList());
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.%n", dealer.getName(), String.join(", ", playerNames));
        printInitialDealerCards(dealer);
        players.forEach(OutputView::printParticipantCards);
    }

    public static void printInitialDealerCards(ParticipantInitialResponse participant) {
        String cardsInfo = createCardsString(participant.getCards());
        System.out.printf("%s 카드: %s%n", participant.getName(), cardsInfo);
    }

    public static void printParticipantCards(ParticipantResponse participant) {
        String cardsInfo = createCardsString(participant.getCards());
        System.out.printf("%s 카드: %s - 합계: %d%n", participant.getName(), cardsInfo, participant.getScore());
    }

    private static String createCardsString(List<Card> cards) {
        return cards.stream()
                .map(OutputView::createCardInfoString)
                .collect(Collectors.joining(", "));
    }

    private static String createCardInfoString(Card card) {
        return card.getDenomination().getName() + card.getPattern().getName();
    }

    public static void printBlackJackMessage(String name) {
        System.out.printf("%s는 블랙잭입니다!!%n", name);
    }

    public static void printBustMessage(String name) {
        System.out.printf("%s의 카드의 합이 21을 넘었습니다.%n", name);
    }

    public static void printDealerHitMessage() {
        System.out.printf("%n딜러는 16이하라 한 장의 카드를 더 받았습니다.%n");
    }

    public static void printCardResultMessage() {
        System.out.printf("%n## 최종 카드%n");
    }

    public static void printWinResult(WinResult winResult) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinResult(winResult.getDealerResult());
        printPlayersWinResult(winResult.getPlayersResult());
    }

    private static void printDealerWinResult(Map<Outcome, Integer> dealerResult) {
        System.out.printf("딜러: %s%n", createDealerWinResultString(dealerResult));
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
        System.out.printf("%s: %s%n", playerName, outcome.getName());
    }
}
