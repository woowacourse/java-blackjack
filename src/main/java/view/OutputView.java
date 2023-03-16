package view;

import dto.ParticipantDto;
import dto.ResultDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String ENTER_LINE = System.lineSeparator();

    private OutputView() {
    }

    public static void printStartMessage(List<ParticipantDto> ParticipantDtos) {
        List<String> playerNames = ParticipantDtos.stream()
                .map(ParticipantDto -> ParticipantDto.getName())
                .collect(Collectors.toList());
        System.out.println(ENTER_LINE + "딜러와 " + String.join(", ", playerNames) + "에게 2장을 나누었습니다.");
    }

    public static void printDealerCard(ParticipantDto participantDto) {
        System.out.println(participantDto.getName() + ": " + participantDto.getDealerFirstCard());
    }

    public static void printPlayersCard(List<ParticipantDto> participantDtos) {
        participantDtos.stream()
                .map(ParticipantDto -> printPlayerHand(ParticipantDto))
                .forEach(System.out::println);
        System.out.println();
    }

    private static String printPlayerHand(ParticipantDto participantDto) {
        List<String> cards = participantDto.getCards();
        return participantDto.getName() + "카드: " + String.join(", ", cards);
    }

    public static void printPlayerCard(ParticipantDto participantDto) {
        System.out.println(participantDto.getName() + "카드: " + String.join(", ", participantDto.getCards()));
    }

    public static void printDealerHit() {
        System.out.println(ENTER_LINE + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printAllHands(ParticipantDto participantDto, List<ParticipantDto> participantDtos) {
        printDealerHands(participantDto);
        printPlayerHands(participantDtos);
    }

    private static void printDealerHands(final ParticipantDto participantDto) {
        List<String> dealerCards = participantDto.getCards();
        System.out.println(ENTER_LINE + participantDto.getName() + " 카드: " + dealerCards.stream().
                collect(Collectors.joining(", ")) + " - 결과: " + participantDto.getScore());
    }

    private static void printPlayerHands(List<ParticipantDto> participantDtos) {
        participantDtos.stream()
                .map(ParticipantDto -> ParticipantDto.getName() + "카드: " +
                        ParticipantDto.getCards().stream()
                                .collect(Collectors.joining(", ")) + " - 결과: " + ParticipantDto.getScore())
                .forEach(System.out::println);
    }

    public static void printBettingResult(ResultDto resultDto) {
        System.out.println(ENTER_LINE + "## 최종 수익");
        printDealerResult(resultDto.getDealerResult());
        printPlayersResult(resultDto.getPlayerResult());
    }

    private static void printDealerResult(int dealerBettingResult) {
        System.out.println("딜러: " + dealerBettingResult);
    }


    private static void printPlayersResult(Map<String, Integer> bettingResult) {
        bettingResult.forEach((playerName, betAmount) -> System.out.println(playerName + ": " + betAmount));
    }

    public static void printError(Exception e) {
        System.out.println(e.getMessage());
    }
}
