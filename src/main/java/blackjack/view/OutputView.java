package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String NAME_DELIMITER = ", ";
    private static final String HANDOUT_MESSAGE = "\n딜러와 %s에게 2장의 카드를 나누어 주었습니다.\n";
    private static final String CARD_INFORMATION_FORMAT = "%s카드: %s";
    private static final String DEALER_HIT_MESSAGE = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String PARTICIPANT_POINT_RESULT = " - 결과: %d";

    public static void printInitialCardInformation(Participants participants) {
        List<String> participantName = participants.getPlayers().stream()
            .map(Player::getName)
            .collect(Collectors.toList());

        System.out.printf(HANDOUT_MESSAGE, String.join(NAME_DELIMITER, participantName));

        printInitialDealerCardInformation(participants.getDealer());
        printInitialPlayersCardInformation(participants.getPlayers());
        System.out.println();
    }

    private static void printInitialPlayersCardInformation(List<Player> players) {
        for (Player player : players) {
            printCards(player);
            System.out.println();
        }
    }

    private static void printInitialDealerCardInformation(Dealer dealer) {
        Card dealerFirstCard = dealer.getFirstCard();

        System.out.printf(CARD_INFORMATION_FORMAT, dealer.getName(),
            dealerFirstCard.getDenominationName() + dealerFirstCard.getSuitName());

        System.out.println();
    }

    public static void printPlayerCardInformation(Player player) {
        printCards(player);
        System.out.println();
    }

    public static void printCards(Participant participant) {
        List<String> participantCardInfo = participant.getCards()
            .stream()
            .map(x -> x.getDenominationName() + x.getSuitName())
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
        for (Participant participant : participants.getParticipants()) {
            printCards(participant);
            printPoint(participant);
        }
    }

    private static void printPoint(Participant participant) {
        System.out.printf(PARTICIPANT_POINT_RESULT, participant.getScore());
        System.out.println();
    }
}
