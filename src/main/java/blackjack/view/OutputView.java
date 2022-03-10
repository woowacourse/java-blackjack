package blackjack.view;

import blackjack.domain.dto.CardDto;
import blackjack.domain.dto.HitResultDto;
import blackjack.domain.dto.WinDrawLoseDto;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PLAYER_BUST_MESSAGE = "%s은(는) %d점으로 버스트 됐습니다.\n";
    private static final String PLAYER_BLACKJACK_MESSAGE = "%s은(는) 블랙잭입니다. 히트를 마무리합니다.\n";
    private static final String TOTAL_RESULT_MESSAGE = "## 최종 승패";
    private static final String WIN_DRAW_LOSE_STATUS_MESSAGE = "%s: %s";
    private static final String HIT_RESULT_MESSAGE = "%s: %s - 결과: %d";

    public static void printInitCard(Map<String, List<CardDto>> cardStatus) {
        cardStatus.forEach((key, value) -> System.out.println(key + ": " + printCard(value)));
    }

    public static void printPresentStatus(String name, List<CardDto> cardDtos) {
        System.out.println(name + ": " + printCard(cardDtos));
    }

    private static String printCard(List<CardDto> cardDtos) {
        return cardDtos.stream()
                .map(card -> card.getDenomination() + card.getSuit())
                .collect(Collectors.joining(", "));
    }

    public static void printBustPlayer(String name, int calculateScore) {
        System.out.printf(PLAYER_BUST_MESSAGE, name, calculateScore);
    }

    public static void printBlackjackPlayer(String name) {
        System.out.printf(PLAYER_BLACKJACK_MESSAGE, name);
    }

    public static void printHitResult(List<HitResultDto> hitResultDtos) {
        hitResultDtos.forEach(hitResultDto -> System.out.printf(HIT_RESULT_MESSAGE, hitResultDto.getName(),
                printCard(hitResultDto.getCards()), hitResultDto.getScore()));
    }

    public static void printResult(List<WinDrawLoseDto> winDrawLoseDtos) {
        System.out.println(TOTAL_RESULT_MESSAGE);
        winDrawLoseDtos.forEach(
                winDrawLoseDto -> System.out.printf(WIN_DRAW_LOSE_STATUS_MESSAGE, winDrawLoseDto.getName(),
                        winDrawLoseDto.getWinDrawLoseString()));
    }
}
