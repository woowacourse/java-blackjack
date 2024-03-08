package view;

import domain.Result;
import domain.participant.Player;
import dto.DealerHandsDto;
import dto.ParticipantDto;
import dto.ParticipantsDto;

import java.util.List;
import java.util.Map;

public class OutputView {

    private final String FORM = "%s카드: %s";
    private final String TOTAL_SUM_FORM = "%s 카드: %s - 결과: %d";
    private final String RESULT_FORM = "%s: %s";

    public void printStartDeal(final DealerHandsDto dealerHandsDto, final ParticipantsDto participantsDto) {
        final String dealerCard = dealerHandsDto.getDisplayedCard();

        final List<String> playerNames = participantsDto.getNames();
        System.out.println("딜러와 " + format(playerNames) + " 에게 2장을 나누었습니다.");

        System.out.println("딜러: " + dealerCard);

        for (ParticipantDto participantDto : participantsDto.getPlayers()) {
            System.out.printf(FORM, participantDto.getName(), format(participantDto.getCards()));
            System.out.println();
        }
        System.out.println();
    }

    public void printHands(final ParticipantDto participantDto) {
        System.out.printf(FORM, participantDto.getName(), format(participantDto.getCards()));
        System.out.println();
    }

    public void printDealerTurnMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다."); //TODO 메서드 변경
        System.out.println();
    }

    public void printHandsResult(final ParticipantsDto participantsDto) {
        for (ParticipantDto participantDto : participantsDto.getPlayers()) {
            System.out.printf(TOTAL_SUM_FORM, participantDto.getName(), format(participantDto.getCards()), participantDto.getTotalSum());
            System.out.println();
        }
    }

    public void printGameResult(final Map<Result, Integer> dealerResult, final Map<Player, Result> playerResult) {
        System.out.println("## 최종결과");
        System.out.printf(RESULT_FORM, "딜러", format(dealerResult));
        System.out.println();
        for (Map.Entry<Player, Result> entry : playerResult.entrySet()) {
            System.out.printf(RESULT_FORM, entry.getKey().getName(), entry.getValue().getValue());
            System.out.println();
        }
    }

    private String format(final Map<Result, Integer> dealerResult) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<Result, Integer> entry : dealerResult.entrySet()) {
            stringBuilder.append(entry.getValue() + entry.getKey().getValue() + " ");
        }

        return stringBuilder.toString();
    }

    private String format(final List<String> playerNames) {
        return String.join(", ", playerNames);
    }

    public void printBust() {
        System.out.println("BUST");
        System.out.println();
    }

    public void printBlackJack() {
        System.out.println("BLACK JACK!!!");
        System.out.println();
    }
}
