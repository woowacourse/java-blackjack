package view;

import static domain.participant.Dealer.INIT_HANDS_SIZE;
import static domain.participant.Dealer.THRESHOLD;

import domain.participant.Player;
import dto.hands.DealerHandsDto;
import dto.hands.ParticipantHandsDto;
import dto.hands.ParticipantsHandsDto;
import dto.profit.ParticipantProfitDto;
import dto.profit.ParticipantsProfitDto;
import java.util.List;

public class OutputView {

    private static final String FORM = "%s카드: %s" + System.lineSeparator();
    private static final String RESULT_FORM = "%s: %s" + System.lineSeparator();

    public void printInitHands(final DealerHandsDto dealerHandsDto, final ParticipantsHandsDto participantsHandsDto) {
        final String dealerCard = dealerHandsDto.getCard();

        final List<String> playerNames = participantsHandsDto.getNames();
        System.out.printf("%s와 %s 에게 %d장을 나누었습니다.%n", dealerHandsDto.name(), format(playerNames), INIT_HANDS_SIZE);
        System.out.printf(RESULT_FORM, dealerHandsDto.name(), dealerCard);

        for (ParticipantHandsDto participantHandsDto : participantsHandsDto.getParticipants()) {
            System.out.printf(FORM, participantHandsDto.name(), format(participantHandsDto.cards()));
        }
        System.out.print(System.lineSeparator());
    }

    public void printHands(final ParticipantHandsDto participantHandsDto) {
        System.out.printf(FORM, participantHandsDto.name(), format(participantHandsDto.cards()));
    }

    public void printDealerOneMoreCardMessage(final String name) {
        System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n%n", name, THRESHOLD);
    }

    public void printHandsResult(final ParticipantsHandsDto participantsHandsDto) {
        for (ParticipantHandsDto participantHandsDto : participantsHandsDto.getParticipants()) {
            System.out.printf("%s 카드: %s - 결과: %d%n", participantHandsDto.name(), format(participantHandsDto.cards()),
                    participantHandsDto.totalSum());
        }
        System.out.print(System.lineSeparator());
    }

    public void printProfitResult(final ParticipantProfitDto dealerProfit, final ParticipantsProfitDto playersProfit) {
        System.out.println("## 최종 수익");
        System.out.printf(RESULT_FORM, dealerProfit.name(), dealerProfit.profit());

        for (ParticipantProfitDto playerProfit : playersProfit.participantsProfitDto()) {
            System.out.printf(RESULT_FORM, playerProfit.name(), playerProfit.profit());
        }
    }

    public void printBust() {
        System.out.printf("BUST" + System.lineSeparator());
    }

    public void printBlackJack(final Player player) {
        System.out.printf("%s : BLACK JACK!!!" + System.lineSeparator(), player.getName());
    }

    public void printDealerEndMessage(boolean isBust) {
        if (isBust) {
            printBust();
        }
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }
}
