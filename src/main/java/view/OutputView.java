package view;

import static domain.participant.Dealer.INIT_HANDS_SIZE;
import static domain.participant.Dealer.THRESHOLD;

import constants.ErrorCode;
import domain.amount.EarnAmount;
import domain.participant.Player;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import view.message.ErrorCodeMessage;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String FORM = "%s카드: %s%n";
    private static final String TOTAL_SUM_FORM = "%s 카드: %s - 결과: %d%n";
    private static final String RESULT_FORM = "%s: %,d%n";
    private static final String ERROR_FORM = "[ERROR] %s%n";


    public void printStartDeal(final DealerHandsDto dealerHandsDto, final ParticipantsDto participantsDto) {
        final String dealerCard = dealerHandsDto.getDisplayedCard();

        final List<String> playerNames = participantsDto.getNames();
        System.out.printf("딜러와 %s 에게 %d장을 나누었습니다.%n", format(playerNames), INIT_HANDS_SIZE);
        System.out.printf("딜러: %s%n", dealerCard);

        for (ParticipantDto participantDto : participantsDto.getPlayers()) {
            System.out.printf(FORM, participantDto.name(), format(participantDto.cards()));
        }
        System.out.print(LINE);
    }

    public void printHands(final ParticipantDto participantDto) {
        System.out.printf(FORM, participantDto.name(), format(participantDto.cards()));
    }

    public void printDealerTurnMessage() {
        System.out.printf("딜러는 %d이하라 한장의 카드를 더 받았습니다.%n%n", THRESHOLD);
    }

    public void printHandsResult(final ParticipantsDto participantsDto) {
        for (ParticipantDto participantDto : participantsDto.getPlayers()) {
            System.out.printf(TOTAL_SUM_FORM, participantDto.name(), format(participantDto.cards()),
                    participantDto.totalSum());
        }
        System.out.print(LINE);
    }

    public void printGameResult(final Map<Player, EarnAmount> playerAmountMap, final EarnAmount earnAmount) {
        System.out.println("## 최종 수익");
        System.out.printf("딜러: %,d%n", earnAmount.getValue());

        for (Entry<Player, EarnAmount> entry : playerAmountMap.entrySet()) {
            System.out.printf(RESULT_FORM, entry.getKey().getName(), entry.getValue().getValue());
        }
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }

    public void printBust() {
        System.out.printf("BUST%n");
    }

    public void printBlackJack(final String name) {
        System.out.printf("%s 축하드립니다! BLACK JACK!!!%n", name);
    }

    public void printException(final ErrorCode errorCode) {
        System.out.printf(ERROR_FORM, ErrorCodeMessage.from(errorCode).getMessage());
    }

    public void printPlayerEndMessage(final boolean isBust) {
        if (isBust) {
            printBust();
            return;
        }
        System.out.println("카드의 합이 21이라 더 이상 카드를 받을 수 없습니다.");
    }

    public void printDealerBlackJack() {
        System.out.println("딜러가 블랙잭!!!");
    }
}
