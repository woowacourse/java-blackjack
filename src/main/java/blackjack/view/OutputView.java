package blackjack.view;

import blackjack.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final int FIRST_CARD_INDEX = 0;
    private static final String DELIMITER = ", ";
    private static final String NEWLINE = System.getProperty("line.separator");

    private OutputView() {
    }

    public static void printDefaultCardMessage(Dealer dealer, List<Player> players) {
        String playerNames = players.stream()
                                    .map(Player::getName)
                                    .collect(Collectors.joining(DELIMITER));
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

    public static void printFinalBetProfits(Map<String, BetAmount> finalBetProfits) {
        printEmptyLine();
        System.out.println("## 최종 수익");
        finalBetProfits.forEach((name, betProfit) -> {
            System.out.println(name + ": " + betProfit.getBetAmount());
        });
    }

    private static void printEmptyLine() {
        System.out.println();
    }
}
