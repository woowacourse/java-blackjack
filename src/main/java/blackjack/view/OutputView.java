package blackjack.view;

import static blackjack.global.ParticipantConstants.DEALER_DISTRIBUTE_COUNT;
import static blackjack.global.ParticipantConstants.DEALER_HIT_THRESHOLD;

import blackjack.dto.*;

import java.util.List;

public class OutputView {

    private static final String ERROR_PREFIX = "[ERROR]: ";

    public static void printErrorMessage(final String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printStartMessage(final StartMessageDto startMessageDto) {
        System.out.printf("딜러와 %s에게 %d장을 나누었습니다.\n",
            String.join(", ", startMessageDto.playerNicknames()), DEALER_DISTRIBUTE_COUNT);

        startMessageDto.participantCardDtosMap().forEach((nickname, cardDtos) ->
            System.out.println(participantHandFormat(nickname, cardDtos)));
    }

    public static void printCardStatus(final CardStatusDto cardStatusDto) {
        System.out.println(participantHandFormat(cardStatusDto.nickname(), cardStatusDto.cardDtos()));
    }


    public static void printDealerHitMessage() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.\n", DEALER_HIT_THRESHOLD);
    }

    public static void printFinalStatus(final FinalStatusDto finalStatusDto) {
        for (final String nickname : finalStatusDto.nicknames()) {
            System.out.printf("%s - 결과: %s\n",
                participantHandFormat(nickname, finalStatusDto.nicknameCardDtosMap().get(nickname)),
                finalStatusDto.nicknameScoreMap().get(nickname));
        }

    }

    public static void printFinalProfits(final FinalProfitsDto finalProfitsDto) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %d\n", finalProfitsDto.dealerProfit());
        finalProfitsDto.nicknameProfitMap().forEach((nickname, profit) ->
            System.out.printf("%s: %d\n", nickname, profit));
    }

    private static String participantHandFormat(final String nickname, final List<CardDto> cardDtos) {
        final List<String> cardNames = cardDtos.stream()
            .map(OutputView::cardFormat)
            .toList();

        return String.format("%s카드: %s",
            nickname, String.join(", ", cardNames));
    }

    private static String cardFormat(final CardDto cardDto) {
        return cardDto.denomination() + cardDto.suit();
    }
}
