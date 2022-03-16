package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;
import blackjack.dto.ParticipantsDto;
import blackjack.dto.ProfitResultDto;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String HANDOUT_MESSAGE = "딜러와 %s에게 2장의 카드를 나누어 주었습니다.";
    private static final String CARD_INFORMATION_FORMAT = "%s카드: %s";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PARTICIPANT_POINT_RESULT = " - 결과: %d";
    private static final String RESULT_DELIMITER = ": ";
    private static final String PROFIT_TITLE_MESSAGE = "## 최종 수익";

    public static void printInitialCardInformation(ParticipantsDto participants) {
        List<String> participantName = participants.getPlayers().stream()
            .map(ParticipantDto::getName)
            .collect(Collectors.toList());

        System.out.printf("\n" + HANDOUT_MESSAGE + "\n", String.join(NAME_DELIMITER, participantName));
        printInitialDealerCardInformation(participants.getDealer());
        printInitialPlayersCardInformation(participants.getPlayers());
        System.out.println();
    }

    private static void printInitialDealerCardInformation(ParticipantDto dealer) {
        CardDto dealerFirstCard = dealer.getCards().get(0);

        System.out.printf(CARD_INFORMATION_FORMAT, dealer.getName(),
            dealerFirstCard.getDenomination() + dealerFirstCard.getSuit());
        System.out.println();
    }

    private static void printInitialPlayersCardInformation(List<ParticipantDto> players) {
        for (ParticipantDto player : players) {
            printPlayerCardInformation(player);
        }
    }

    public static void printPlayerCardInformation(ParticipantDto player) {
        printCards(player);
        System.out.println();
    }

    public static void printCards(ParticipantDto participant) {
        List<String> participantCardInfo = participant.getCards()
            .stream()
            .map(x -> x.getDenomination() + x.getSuit())
            .collect(Collectors.toList());

        String cardInfo = String.join(NAME_DELIMITER, participantCardInfo);
        System.out.printf(CARD_INFORMATION_FORMAT, participant.getName(), cardInfo);
    }

    public static void printDealerHitMessage() {
        System.out.println();
        System.out.print(DEALER_HIT_MESSAGE);
    }

    public static void printCardsAndPoint(ParticipantsDto participants) {
        System.out.println();
        printCards(participants.getDealer());
        printPoint(participants.getDealer());
        for (ParticipantDto player : participants.getPlayers()) {
            printCards(player);
            printPoint(player);
        }
    }

    private static void printPoint(ParticipantDto participant) {
        System.out.printf(PARTICIPANT_POINT_RESULT, participant.getScore());
        System.out.println();
    }

    public static void printProfitResult(ProfitResultDto profitResult) {
        System.out.println("\n" + PROFIT_TITLE_MESSAGE);
        for (Map.Entry<ParticipantDto, Integer> entry : profitResult.getResult().entrySet()) {
            System.out.print(entry.getKey().getName() + RESULT_DELIMITER + entry.getValue() + "\n");
        }
    }
}
