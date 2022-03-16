package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String HANDOUT_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어 주었습니다.";
    private static final String CARD_INFORMATION_FORMAT = "%s카드: %s";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PARTICIPANT_POINT_RESULT = " - 결과: %d";
    private static final String PARTICIPANT_WINNING_RESULT_MESSAGE = "## 최종 승패";
    private static final String DEALER_DIRECTION = "딜러:";
    private static final String RESULT_DELIMITER = ": ";
    private static final String PROFIT_TITLE_MESSAGE = "## 최종 수익";

    public static void printInitialCardInformation(Participants participants) {
        List<String> participantName = participants.getPlayers().stream()
            .map(Participant::getName)
            .collect(Collectors.toList());

        System.out.printf("\n" + HANDOUT_MESSAGE + "\n", String.join(NAME_DELIMITER, participantName));

        printInitialDealerCardInformation(participants.getDealer());
        printInitialPlayersCardInformation(participants.getPlayers());
        System.out.println();
    }

    private static void printInitialDealerCardInformation(Dealer dealer) {
        Card dealerFirstCard = dealer.getFirstCard();

        System.out.printf(CARD_INFORMATION_FORMAT, dealer.getName(),
            dealerFirstCard.getDenomination().getName() + dealerFirstCard.getSuit().getName());

        System.out.println();
    }

    private static void printInitialPlayersCardInformation(List<Player> players) {
        for (Player player : players) {
            printPlayerCardInformation(player);
        }
    }

    public static void printPlayerCardInformation(Player player) {
        printCards(player);
        System.out.println();
    }

    public static void printCards(Participant participant) {
        List<String> participantCardInfo = participant.getCards()
            .stream()
            .map(x -> x.getDenomination().getName() + x.getSuit().getName())
            .collect(Collectors.toList());

        String cardInfo = String.join(NAME_DELIMITER, participantCardInfo);

        System.out.printf(CARD_INFORMATION_FORMAT, participant.getName(), cardInfo);
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.print(DEALER_HIT_MESSAGE);
    }

    public static void printCardsAndPoint(Participants participants) {
        System.out.println();
        printCards(participants.getDealer());
        printPoint(participants.getDealer());
        for (Player player : participants.getPlayers()) {
            printCards(player);
            printPoint(player);
        }
    }

    private static void printPoint(Participant participant) {
        System.out.printf(PARTICIPANT_POINT_RESULT, participant.getScore());
        System.out.println();
    }

    public static void printProfitResult(Map<Participant, Integer> profitResult) {
        System.out.println("\n" + PROFIT_TITLE_MESSAGE);
        for (Participant participant : profitResult.keySet()) {
            System.out.print(
                participant.getName() + RESULT_DELIMITER + profitResult.get(participant) + "\n"
            );
        }
    }
}
