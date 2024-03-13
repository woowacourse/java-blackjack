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

    private static final String FORM = "%s카드: %s" + System.lineSeparator();
    private static final String RESULT_FORM = "%s: %s" + System.lineSeparator();

    public void printInitHands(final DealerHandsDto dealerHandsDto, final ParticipantsDto participantsDto) {
        final String dealerCard = dealerHandsDto.getCard();

        final List<String> playerNames = participantsDto.getNames();
        System.out.printf("%s와 %s 에게 %d장을 나누었습니다.%n", dealerHandsDto.name(), format(playerNames), INIT_HANDS_SIZE);
        System.out.printf(RESULT_FORM, dealerHandsDto.name(), dealerCard);

        for (ParticipantDto participantDto : participantsDto.getParticipants()) {
            System.out.printf(FORM, participantDto.name(), format(participantDto.cards()));
        }
        System.out.print(System.lineSeparator());
    }

    public void printHands(final ParticipantDto participantDto) {
        System.out.printf(FORM, participantDto.name(), format(participantDto.cards()));
    }

    public void printDealerHandsChangedMessage(final String name) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n%n", name, THRESHOLD);
    }

    public void printHandsResult(final ParticipantsDto participantsDto) {
        for (ParticipantDto participantDto : participantsDto.getParticipants()) {
            System.out.printf("%s 카드: %s - 결과: %d%n", participantDto.name(), format(participantDto.cards()),
                    participantDto.totalSum());
        }
        System.out.print(System.lineSeparator());
    }

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<Player, Result> playerResult) {
        System.out.printf("## 최종결과" + System.lineSeparator() + RESULT_FORM, "딜러", format(dealerResult));
        for (Map.Entry<Player, Result> entry : playerResult.entrySet()) {
            System.out.printf(RESULT_FORM, entry.getKey().getName(), entry.getValue().getValue());
        }
    }

    public void printBust() {
        System.out.printf("BUST" + System.lineSeparator());
    }

    public void printBlackJack() {
        System.out.printf("BLACK JACK!!!" + System.lineSeparator());
    }

    public void printDealerEndMessage(boolean isBust) {
        if (isBust) {
            printBust();
            return;
        }

        printBlackJack();
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
}
