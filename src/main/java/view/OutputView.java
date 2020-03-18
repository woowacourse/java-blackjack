package view;

import domain.gamer.dto.GamerMoneyDto;
import domain.gamer.dto.GamerCardsDto;
import domain.gamer.dto.GamerCardsWithScoreDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";

    public static void printInitGamersState(List<GamerCardsDto> gamerCardsDtos) {
        String gamerNames = gamerCardsDtos.stream()
                .map(GamerCardsDto::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("\n%s에게 2장의 카드를 나누었습니다.", gamerNames));
        for (GamerCardsDto playerDto : gamerCardsDtos) {
            printGamerCardsState(playerDto);
        }
    }

    public static void printGamerCardsState(GamerCardsDto gamerCardsDto) {
        String gamerCards = gamerCardsDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("%s: %s", gamerCardsDto.getName(), gamerCards));
    }

    public static void printDealerHit(int hitThreshold) {
        System.out.println(String.format("\n딜러의 점수가 %d미만이라 한 장의 카드를 더 받습니다.", hitThreshold));
    }

    public static void printGamerCardsStateWithScores(List<GamerCardsWithScoreDto> gamerCardsWithScoreDtos) {
        System.out.println();
        for (GamerCardsWithScoreDto gamerCardsWithScoreDto : gamerCardsWithScoreDtos) {
            printGamerCardsStateWithScore(gamerCardsWithScoreDto);
        }
    }

    private static void printGamerCardsStateWithScore(GamerCardsWithScoreDto gamerCardsWithScoreDto) {
        String gamerCards = gamerCardsWithScoreDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format("%s: %s - 결과: %d",
                gamerCardsWithScoreDto.getName(), gamerCards, gamerCardsWithScoreDto.getScore()));
    }

    public static void printResult(List<GamerMoneyDto> gamerMoneyDtos) {
        System.out.println("\n## 최종 수익");
        for (GamerMoneyDto gamerMoneyDto : gamerMoneyDtos) {
            printEachResult(gamerMoneyDto);
        }
    }

    private static void printEachResult(GamerMoneyDto gamerMoneyDto) {
        System.out.println(gamerMoneyDto.getName() + " : " + gamerMoneyDto.getMoney());
    }
}
