package view;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NEW_LINE = System.lineSeparator();

    private OutputView() {}

    public static void printInitialCards(Dealer dealer, List<Player> players) {
        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealer.getParticipantName(), findPlayerNames(players));

        printInitialDealerCard(dealer);
        printInitialPlayersCards(players);
    }

    private static String findPlayerNames(List<Player> players) {
        return players.stream()
                .map(Participant::getParticipantName)
                .collect(Collectors.joining(", "));
    }

    private static void printInitialDealerCard(Dealer dealer) {
        System.out.printf("%s카드: %s%n", dealer.getParticipantName(), convertCardToMessage(dealer.getCards().getFirst()));
    }

    private static void printInitialPlayersCards(List<Player> players) {
        for (Participant player : players) {
            System.out.printf("%s카드: %s%n", player.getParticipantName(), convertCardsToMessage(player.getCards()));
        }
        System.out.print(NEW_LINE);
    }

    public static void printDealerDrawMessage() {
        System.out.print(NEW_LINE);
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    private static String convertCardsToMessage(List<Card> cards) {
        return cards.stream()
                .map(OutputView::convertCardToMessage)
                .collect(Collectors.joining(", "));
    }

    private static String convertCardToMessage(Card card) {
        Suit suit = card.getSuit();
        Rank rank = card.getRank();
        return rank.getFaceValue() + suit.getName();
    }

    public static void printPlayerCard(Participant player) {
        System.out.printf("%s카드: %s%n", player.getParticipantName(), convertCardsToMessage(player.getCards()));
    }

    public static void printFinalCards(Dealer dealer, List<Player> players) {
        printFinalDealerCard(dealer);
        printFinalPlayersCards(players);
    }

    private static void printFinalDealerCard(Dealer dealer) {
        System.out.printf("%n%s카드: %s - 결과: %d%n",
                dealer.getParticipantName(),
                convertCardsToMessage(dealer.getCards()),
                dealer.getTotalRankSum());
    }

    private static void printFinalPlayersCards(List<Player> players) {
        for (Participant player : players) {
            System.out.printf("%s카드: %s - 결과: %d%n",
                    player.getParticipantName(),
                    convertCardsToMessage(player.getCards()),
                    player.getTotalRankSum());
        }
    }

    public static void printProfits(Map<Player, Integer> profits, int profitOfDealer) {
        System.out.print(NEW_LINE);
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d%n", profitOfDealer);
        for (Player player : profits.keySet()) {
            System.out.printf("%s: %d%n", player.getParticipantName(), profits.get(player));
        }
    }
}
