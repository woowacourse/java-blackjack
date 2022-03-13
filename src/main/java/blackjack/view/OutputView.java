package blackjack.view;

import blackjack.domain.Judgement;
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

    public static void printWinResultMessage() {
        System.out.println();
        System.out.println("## 최종 승패");
    }

    public static void printDealerWinResult(String dealerName, Map<Judgement, Integer> dealerResult) {
        System.out.printf("딜러: %s", createDealerWinResultString(dealerResult));
        System.out.println();
    }

    private static String createDealerWinResultString(Map<Judgement, Integer> dealerResult) {
        return dealerResult.keySet()
            .stream()
            .map(judgement -> String.format("%d%s", dealerResult.get(judgement), judgement.getName()))
            .collect(Collectors.joining(" "));
    }

    public static void printPlayersWinResult(Map<String, Judgement> playersResult) {
        playersResult.keySet()
            .forEach(playerName -> printPlayerWinResult(playerName, playersResult.get(playerName)));
    }

    private static void printPlayerWinResult(String playerName, Judgement judgement) {
        System.out.printf("%s: %s", playerName, judgement.getName());
        System.out.println();
    }
}
