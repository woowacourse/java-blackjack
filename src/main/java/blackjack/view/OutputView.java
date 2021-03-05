package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.vo.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final int FIRST_CARD_INDEX = 0;
    private static final String DELIMITER = ", ";

    private OutputView() {
    }

    public static void printCardDistributionMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                .map(Player::getName)
                .collect(Collectors.joining(DELIMITER));
        printNewLine();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.", dealer.getName(), playerNames);
        printNewLine();
        printDealerDefaultCard(dealer);
        printPlayerAllDefaultCards(players);
    }

    private static void printDealerDefaultCard(Dealer dealer) {
        List<Card> dealerCards = dealer.getCards();
        Card firstCardOfDealer = dealerCards.get(FIRST_CARD_INDEX);
        System.out.printf("%s : %s", dealer.getName(), parseCardInformation(firstCardOfDealer));
        printNewLine();
    }

    private static String parseCardInformation(Card card) {
        return card.getSymbol().getName() + card.getShape().getName();
    }

    private static void printPlayerAllDefaultCards(List<Player> players) {
        players.forEach(OutputView::printEachPlayerCards);
        printNewLine();
    }

    public static void printEachPlayerCards(Player player) {
        String cardsInformation = parseCardsInformation(player.getCards());
        System.out.printf("%s 카드: %s", player.getName(), cardsInformation);
        printNewLine();
    }

    private static String parseCardsInformation(List<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardInformation)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerDrawingMessage(Dealer dealer) {
        printNewLine();
        System.out.printf("%s는 16이하라 한 장의 카드를 더 받았습니다.", dealer.getName());
        printNewLine();
    }


    public static void printFinalCardsAndScore(Dealer dealer, List<Player> players) {
        printNewLine();
        printParticipantCardWithScore(dealer);
        players.forEach(OutputView::printParticipantCardWithScore);
    }

    private static void printParticipantCardWithScore(Participant participant) {
        String cardsInformation = parseCardsInformation(participant.getCards());
        System.out.printf("%s 카드: %s - 결과: %d", participant.getName(), cardsInformation, participant.calculateFinalScore());
        printNewLine();
    }

    public static void printFinalResult(Dealer dealer, List<Player> players) {
        printNewLine();
        System.out.println("## 최종 승패");
        Map<Result, Long> map = dealer.aggregateResultStatistics(players);
        long winCounts = map.get(Result.WIN);
        long lossCounts = map.get(Result.LOSE);
        long drawCounts = map.get(Result.DRAW);
        System.out.printf("%s: %d승 %d무 %d패", dealer.getName(), winCounts, drawCounts, lossCounts);
        printNewLine();
        players.forEach(player -> printEachPlayerFinalResult(player, dealer));
    }

    private static void printEachPlayerFinalResult(Player player, Dealer dealer) {
        Result result = player.judgeResult(dealer);
        System.out.printf("%s: %s", player.getName(), result.getName());
        printNewLine();
    }

    private static void printNewLine() {
        System.out.println();
    }
}
