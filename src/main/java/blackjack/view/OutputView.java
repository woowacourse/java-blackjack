package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.domain.Result;
import blackjack.domain.StatisticResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FIRST_CARD_INDEX = 0;
    private static final String DELIMITER = ", ";
    private static final String NEWLINE = System.getProperty("line.separator");
    private static final long NO_COUNTS_OF_RESULT = 0L;

    private OutputView() {
    }

    public static void printDefaultCardMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                                    .map(Player::getName)
                                    .collect(Collectors.joining(DELIMITER));
        printEmptyLine();
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다." + NEWLINE, dealer.getName(), playerNames);
        printDefaultDealerCard(dealer);
        printDefaultPlayerCards(players);
    }

    private static void printDefaultDealerCard(Dealer dealer) {
        List<Card> dealerCards = dealer.getCards();
        Card firstCardOfDealer = dealerCards.get(FIRST_CARD_INDEX);
        System.out.printf("%s : %s" + NEWLINE, dealer.getName(),
            getCardInformation(firstCardOfDealer));
    }

    private static String getCardInformation(Card card) {
        return card.getSymbol()
                   .getName() + card.getShape()
                                    .getName();
    }

    private static void printDefaultPlayerCards(List<Player> players) {
        players.forEach(OutputView::printEachPlayerCards);
        printEmptyLine();
    }

    public static void printEachPlayerCards(Player player) {
        String cards = getCardsInformation(player.getCards());
        System.out.println(player.getName() + "카드: " + cards);
    }

    private static String getCardsInformation(List<Card> cards) {
        return cards.stream()
                    .map(OutputView::getCardInformation)
                    .collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerDrawingMessage(Dealer dealer) {
        printEmptyLine();
        System.out.println(dealer.getName() + "는 16이하라 한 장의 카드를 더 받았습니다.");
    }

    public static void printFinalCardsAndScore(Participants participants) {
        participants.toList()
                    .forEach(OutputView::printParticipantCardWithScore);
    }

    private static void printParticipantCardWithScore(Participant participant) {
        String cards = getCardsInformation(participant.getCards());
        System.out.println(
            participant.getName() + " 카드: " + cards + " - 결과: "
                + participant.calculateScore());
    }

    public static void printFinalResult(StatisticResult statisticResult) {
        printEmptyLine();
        System.out.println("## 최종 승패");
        Map<Result, Long> dealerStatisticResultMap = statisticResult.aggregateDealerResultAndCount();
        Map<String, Result> playerNameResultMap = statisticResult.getPlayerNameAneResult();
        long winCounts = dealerStatisticResultMap.getOrDefault(Result.WIN, NO_COUNTS_OF_RESULT);
        long lossCounts = dealerStatisticResultMap.getOrDefault(Result.LOSE, NO_COUNTS_OF_RESULT);
        long drawCounts = dealerStatisticResultMap.getOrDefault(Result.DRAW, NO_COUNTS_OF_RESULT);

        System.out.println("딜러: " + winCounts + "승 " + drawCounts + "무 " + lossCounts + "패");
        playerNameResultMap.forEach((playerName, result) -> {
            System.out.println(playerName + ": " + result.getName());
        });
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
