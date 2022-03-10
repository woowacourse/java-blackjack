package blackjack.view;

import blackjack.domain.CardDto;
import blackjack.domain.HitResultDto;
import blackjack.domain.WinDrawLoseDto;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;
import java.util.stream.Collectors;

public class OutputView {

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
        System.out.println(name + "은(는) " + calculateScore + "점으로 버스트 됐습니다.");
    }

    public static void printBlackjackPlayer(String name) {
        System.out.println(name + "은(는) 블랙잭입니다. 히트를 마무리합니다.");
    }

    public static void printHitResult(List<HitResultDto> hitResultDtos) {
        hitResultDtos.forEach(hitResultDto -> System.out.println(hitResultDto.getName() + ": " + printCard(hitResultDto.getCards()) + "- 결과: " + hitResultDto.getScore()));
    }

    public static void printResult(List<WinDrawLoseDto> winDrawLoseDtos) {
        System.out.println("## 최종 승패");
        winDrawLoseDtos.forEach(winDrawLoseDto -> System.out.println(winDrawLoseDto.getName() + ": " + winDrawLoseDto.getWinDrawLoseString()));
    }
}
