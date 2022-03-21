package blackjack.view;

import blackjack.domain.*;
import blackjack.domain.dto.BettingResultDto;
import blackjack.domain.dto.ParticipantDto;
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
                participantDto.getCards().stream()
                        .map(Card::cardInfo)
                        .collect(Collectors.joining(", ")));
    }

    public static void showDrawResult(String name, List<Card> cards) {
        System.out.printf(name + "카드: ");
        System.out.println(cards.stream()
                .map(Card::cardInfo)
                .collect(Collectors.joining(", ")));
    }

    public static void showDealerResult(boolean dealerDrawMoreCard) {
        if(dealerDrawMoreCard) {
            System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_NAME);
            return;
        }
        System.out.printf("%s는 17이상이라 카드를 더 받지 않았습니다.%n", Dealer.DEALER_NAME);
    }

    public static void showFinalCardsAndScore(List<ParticipantDto> participantDtos) {
        System.out.println();
        for (ParticipantDto participantDto : participantDtos) {
            System.out.println(participantDto.getName() + "카드" + ": " +
                    participantDto.getCards().stream()
                            .map(Card::cardInfo)
                            .collect(Collectors.joining(", "))
            + " - 결과: " + participantDto.getScore());
        }

    }

    public static void showDealerRevenue(List<BettingResultDto> bettingResultDtos) {
        System.out.printf("%n## 최종 수익%n");
        for (BettingResultDto bettingResultDto : bettingResultDtos) {
            System.out.printf("%s: %d%n", bettingResultDto.getName(), bettingResultDto.getRevenue());
        }
    }

}
