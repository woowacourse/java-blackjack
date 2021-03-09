package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.profit.ProfitStatistics;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {
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
        printPlayersAllDefaultCards(players);
    }

    private static void printDealerDefaultCard(Dealer dealer) {
        Set<Card> dealerCards = dealer.getCards();
        Card firstCard = dealerCards.stream()
                .findFirst()
                .orElseThrow(IllegalStateException::new);
        System.out.printf("%s : %s", dealer.getName(), parseCardInformation(firstCard));
        printNewLine();
    }

    private static String parseCardInformation(Card card) {
        return card.getSymbol().getName() + card.getShape().getName();
    }

    private static void printPlayersAllDefaultCards(List<Player> players) {
        players.forEach(OutputView::printEachPlayerCards);
        printNewLine();
    }

    public static void printEachPlayerCards(Player player) {
        String cardsInformation = parseCardsInformation(player.getCards());
        System.out.printf("%s 카드: %s", player.getName(), cardsInformation);
        printNewLine();
    }

    private static String parseCardsInformation(Set<Card> cards) {
        return cards.stream()
                .map(OutputView::parseCardInformation)
                .collect(Collectors.joining(DELIMITER));
    }

    public static void printDealerDrawingMessage(Dealer dealer) {
        printNewLine();
        System.out.printf("%s는 16이하라 카드를 더 받았습니다.", dealer.getName());
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

    public static void printFinalProfitMoney(ProfitStatistics profitStatistics) {
        printNewLine();
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + profitStatistics.calculateDealerProfitMoney());
        profitStatistics.getProfitStatistics()
                .forEach((playerName, profitMoney) -> System.out.println(playerName.getName() + ": " + profitMoney));
    }

    private static void printNewLine() {
        System.out.println();
    }
}
