package blackjack.view;

import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_BUST_MESSAGE = "- %s은(는) %d 점으로 버스트 됐습니다.";
    private static final String TOTAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_DRAW_LOSE_STATUS_MESSAGE = "%s: %s";
    private static final String HIT_RESULT_MESSAGE = "%s: %s - 결과: %d";

    public static void printInitCard(Map<String, List<CardDto>> cardStatus) {
        cardStatus.forEach((key, value) -> System.out.println(key + ": " + joinCardString(value)));
    }

    public static void printPresentStatus(String name, List<CardDto> cardDtos, int score, boolean isBust) {
        System.out.println();
        StringBuilder messageBuilder = new StringBuilder(String.format(WIN_DRAW_LOSE_STATUS_MESSAGE, name, joinCardString(cardDtos)));
        if (isBust) {
            messageBuilder.append(String.format(PLAYER_BUST_MESSAGE, name, score));
        }
        System.out.println(messageBuilder);
    }

    public static void printHitResult(Map<String, HitResultDto> hitResults) {
        System.out.println();
        hitResults.forEach((playerName, hitResult) -> System.out.printf(HIT_RESULT_MESSAGE + "\n", playerName,
                joinCardString(hitResult.getCards()), hitResult.getScore()));
    }

    private static String joinCardString(List<CardDto> cardDtos) {
        return cardDtos.stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(", "));
    }

    public static void printResult(List<WinDrawLoseDto> winDrawLoseDtos) {
        System.out.println();
        System.out.println(TOTAL_RESULT_MESSAGE);
        winDrawLoseDtos.forEach(
                winDrawLoseDto -> System.out.printf(WIN_DRAW_LOSE_STATUS_MESSAGE + "\n", winDrawLoseDto.getName(),
                        winDrawLoseDto.getWinDrawLoseString()));
    }
}
