package blackjack.view;

import blackjack.domain.Card;
import blackjack.domain.HoldingCard;
import blackjack.domain.ParticipantDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private OutputView() {
    }

    public static void printErrorMessage(Exception exception) {
        System.out.println(exception.getMessage());
    }

    public static void printInitialCardStatus(List<ParticipantDto> participantsDto) {
        System.out.println(participantsDto.stream()
                .map(ParticipantDto::getName)
                .collect(Collectors.joining(", ")) + "에게 2장을 나누었습니다.");

        for (ParticipantDto participantDto : participantsDto) {
            printEachParticipantCard(participantDto);
        }
    }

    private static void printEachParticipantCard(ParticipantDto participantDto) {
        System.out.println(participantDto.getName() + "카드" + ": " +
                participantDto.getCards().getHoldingCard().stream()
                        .map(Card::toString)
                        .collect(Collectors.joining(", ")));
    }

    public static void printPlayerCards(String currentPlayerName, HoldingCard holdingCard) {
        System.out.println(currentPlayerName + "카드" + holdingCard.toString());
    }

    public static void printPlayerFinalCards(List<ParticipantDto> playerFinalCardsAndScore) {
        for (ParticipantDto participantDto : playerFinalCardsAndScore) {
            printEachFinalCards(participantDto);
        }
    }

    private static void printEachFinalCards(ParticipantDto participantDto) {
        System.out.println(
                participantDto.getName() + "카드:" + participantDto.getCards() + "- 결과: " + participantDto.getSum());
    }
}
