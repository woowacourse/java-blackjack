package view;

import domain.gamer.dto.GamerDto;
import domain.gamer.dto.GamerWithCardsDto;
import domain.gamer.dto.GamerWithScoreDto;

import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";

    public static void printInitGamersState(GamerWithCardsDto dealerDto, List<GamerWithCardsDto> playerDtos) {
        String dealerName = dealerDto.getName();
        String playerNames = playerDtos.stream()
                .map(GamerWithCardsDto::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s와 %s에게 2장의 카드를 나누었습니다.\n", dealerName, playerNames);
        printGamerCardsState(dealerDto);
        for (GamerWithCardsDto playerDto : playerDtos) {
            printGamerCardsState(playerDto);
        }
    }

    public static void printGamerCardsState(GamerWithCardsDto gamerWithCardsDto) {
        String gamerCards = gamerWithCardsDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s: %s\n", gamerWithCardsDto.getName(), gamerCards);
    }

    public static void printDealerHit(int hitThreshold) {
        System.out.println(String.format("\n딜러의 점수가 %d미만이라 한 장의 카드를 더 받습니다.", hitThreshold));
    }

    public static void printGamerCardsStateWithScores(List<GamerWithScoreDto> gamerWithScoreDtos) {
        System.out.println();
        for (GamerWithScoreDto gamerWithScoreDto : gamerWithScoreDtos) {
            printGamerCardsStateWithScore(gamerWithScoreDto);
        }
    }

    public static void printGamerCardsStateWithScore(GamerWithScoreDto gamerWithScoreDto) {
        String gamerCards = gamerWithScoreDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s: %s - 결과: %d\n", gamerWithScoreDto.getName(), gamerCards, gamerWithScoreDto.getScore());
    }

    public static void printGameResultTitle() {
        System.out.println("\n## 최종 수익");
    }

    public static void printDealerResult(int totalEarning) {
        System.out.println(String.format("딜러 : %d", totalEarning));
    }

    public static void printEachResult(GamerDto playerDto, int earning) {
        System.out.println(playerDto.getName() + " : " + earning);
    }
}
