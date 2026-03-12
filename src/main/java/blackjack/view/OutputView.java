package blackjack.view;

import static blackjack.global.ParticipantConstants.DEALER_DISTRIBUTE_COUNT;
import static blackjack.global.ParticipantConstants.DEALER_HIT_THRESHOLD;

import blackjack.domain.participant.Participant;
import blackjack.dto.FinalProfitsDto;
import blackjack.dto.FinalStatusDto;
import blackjack.dto.StartMessageDto;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]: ";

    public static void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printStartMessage(final StartMessageDto dto) {

        System.out.printf("딜러와 %s에게 %d장을 나누었습니다.\n",
            String.join(", ", dto.playerNicknames()), DEALER_DISTRIBUTE_COUNT);

        System.out.printf("딜러카드: %s\n", String.join(", ", dto.dealerOpenedCardNames()));
        dto.playerCardNamesMap().forEach((playerNickname, cardNames) ->
            System.out.printf("%s카드: %s\n", playerNickname, String.join(", ", cardNames)));
    }

    public static void printCardStatus(final Participant participant) {
        System.out.println(participantHandFormat(participant));
    }


    public static void printDealerHitMessage() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_HIT_THRESHOLD);
    }

    public static void printFinalStatus(final FinalStatusDto dto) {
        dto.participantNicknames().forEach(nickname ->
            System.out.printf("%s 카드: %s - 결과: %s\n",
                nickname,
                String.join(", ", dto.participantCardNamesMap().get(nickname)),
                dto.participantScoreMap().get(nickname)));
    }

    public static void printFinalProfits(final FinalProfitsDto dto) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d\n", dto.dealerProfit());
        dto.nicknameProfitMap().forEach((nickname, profit) -> {
            System.out.printf("%s: %d\n", nickname, profit);
        });
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
