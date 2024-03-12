package view;

import static domain.participant.Dealer.INIT_HANDS_SIZE;
import static domain.participant.Dealer.THRESHOLD;

import domain.Result;
import domain.participant.Player;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String LINE = System.lineSeparator();
    private static final String FORM = "%s카드: %s%n";
    private static final String TOTAL_SUM_FORM = "%s 카드: %s - 결과: %d%n";
    private static final String RESULT_FORM = "%s: %s%n";


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

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<Player, Result> playerResult) {
        System.out.println("## 최종결과");
        System.out.printf(RESULT_FORM, "딜러", format(dealerResult));
        for (Map.Entry<Player, Result> entry : playerResult.entrySet()) {
            System.out.printf(RESULT_FORM, entry.getKey().getName(), entry.getValue().getValue());
        }
    }

    private String format(final Map<Result, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Result, Integer> entry : dealerResult.entrySet()) {
            stringBuilder.append(entry.getValue()).append(entry.getKey().getValue()).append(" ");
        }

        return stringBuilder.toString();
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }

    public void printBust() {
        System.out.printf("BUST%n");
    }

    public void printBlackJack() {
        System.out.printf("BLACK JACK!!!%n");
    }

    public void printException(final Throwable exception) {
        System.out.println(exception.getMessage());
    }
}
