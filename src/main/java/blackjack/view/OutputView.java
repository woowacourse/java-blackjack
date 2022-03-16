package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Score;
import blackjack.domain.ScoreResult;
import blackjack.dto.CardDto;
import blackjack.dto.ParticipantDto;

public class OutputView {

    private OutputView() {
    }

    public static void printInitGameMessage(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        System.out.printf("%s와 %s에게 2장을 나누었습니다.", dealerDto.getName(),
                participantDtos.stream().map(ParticipantDto::getName).collect(Collectors.joining(", ")));
        System.out.println();
    }

    public static void printOpenCard(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        CardDto dealerOpenCard = dealerDto.getCards().get(0);
        System.out.printf("%s: %s%s", dealerDto.getName(), dealerOpenCard.getCardNumber(),
                dealerOpenCard.getCardPattern());
        System.out.println();
        for (ParticipantDto participantDto : participantDtos) {
            printPlayerCards(participantDto);
        }
    }

    public static void printPlayersResult(List<ParticipantDto> participantDtos, ParticipantDto dealerDto) {
        System.out.println();
        printPlayerResult(dealerDto);

        for (ParticipantDto participantDto : participantDtos) {
            printPlayerResult(participantDto);
        }
    }

    private static void printPlayerResult(ParticipantDto dealerDto) {
        System.out.printf("%s: %s - 결과: %d%n",
                dealerDto.getName(),
                dealerDto.getCards()
                        .stream()
                        .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                        .collect(Collectors.joining(", ")),
                dealerDto.getTotalNumber());
    }

    public static void printPlayerCards(ParticipantDto participantDto) {
        System.out.printf("%s: %s%n", participantDto.getName(),
                participantDto.getCards()
                        .stream()
                        .map(cardDto -> cardDto.getCardNumber() + cardDto.getCardPattern())
                        .collect(Collectors.joining(", ")));
    }

    public static void printDealerDrawMessage() {
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResult(ScoreResult result) {
        System.out.println("## 최종 승패");
        System.out.print("딜러: ");
        for (Score score : Score.values()) {
            int dealerScoreCount = result.getDealerScoreCount(score);
            printResultByScore(score, dealerScoreCount);
        }
        System.out.println();
        for (Map.Entry<String, String> entry : result.getPlayerResult().entrySet()) {
            System.out.printf("%s: %s%n", entry.getKey(), entry.getValue());
        }
    }

    private static void printResultByScore(Score score, int dealerScoreCount) {
        if (dealerScoreCount > 0) {
            System.out.printf("%d%s ", dealerScoreCount, score.getValue());
        }
    }

}
