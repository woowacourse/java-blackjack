package blackjack.view;

import static blackjack.domain.Dealer.DEALER_HIT_THRESHOLD;

import blackjack.domain.Dealer;
import blackjack.domain.GameResult;
import blackjack.domain.Participant;
import blackjack.domain.Participants;
import blackjack.domain.Player;
import blackjack.dto.FinalResultDto;
import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]: ";
    private static final String START_NUMBER_OF_CARD = "2";

    public static void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printStartMessage(final List<Player> players, final Dealer dealer) {
        List<String> playerNicknames = players.stream()
            .map(Player::getNickname)
            .toList();

        System.out.printf("딜러와 %s에게 %s장을 나누었습니다.\n",
            String.join(", ", playerNicknames), START_NUMBER_OF_CARD);

        System.out.printf("딜러카드: %s\n", String.join(", ", dealer.getOpenCardNames()));

        players.forEach(OutputView::printCardStatus);
    }

    public static void printCardStatus(final Participant participant) {
        System.out.println(participantHandFormat(participant));
    }


    public static void printDealerHitMessage() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_HIT_THRESHOLD);
    }

    public static void printFinalStatus(final Participants participants) {
        final String delimiter = " - ";
        participants.all().forEach(participant ->
            System.out.println(participantHandFormat(participant) + delimiter +
                participantScoreResultFormat(participant))
        );
    }

    public static void printFinalResult(final FinalResultDto dto) {
        System.out.println("## 최종 승패");
        System.out.printf("딜러: %d승 %d무 %d패\n",
            dto.dealerWinCount(), dto.dealerDrawCount(), dto.dealerLoseCount());
        dto.playerGameResultMap()
            .forEach((key, value) ->
                System.out.printf("%s: %s\n", key, value.getName()));
    }

    private static String participantHandFormat(final Participant participant) {
        return String.format("%s카드: %s",
            participant.getNickname(),
            String.join(", ", participant.getAllCardNames())
        );
    }

    private static String participantScoreResultFormat(final Participant participant) {
        return String.format("결과: %d", participant.getScore());
    }
}
