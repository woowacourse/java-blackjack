package view;

import domain.PlayerResult;
import domain.gamer.dto.GamerDto;
import domain.gamer.dto.GamerWithScoreDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String DELIMITER = ", ";

    public static void printInitGamersState(GamerDto dealerDto, List<GamerDto> playerDtos, int initCardsSize) {
        String dealerName = dealerDto.getName();
        String playerNames = playerDtos.stream()
                .map(GamerDto::getName)
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s와 %s에게 %d장의 카드를 나누었습니다.\n", dealerName, playerNames, initCardsSize);
        printGamerCardsState(dealerDto);
        for (GamerDto playerDto : playerDtos) {
            printGamerCardsState(playerDto);
        }
    }

    public static void printGamerCardsState(GamerDto gamerDto) {
        String gamerCards = gamerDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s: %s\n", gamerDto.getName(), gamerCards);
    }

    public static void printDealerHit(int hitThreshold) {
        System.out.println(String.format("딜러의 점수가 %d미만이라 한 장의 카드를 더 받습니다.\n", hitThreshold));
    }

    public static void printGamerCardsStateWithScore(GamerWithScoreDto gamerWithScoreDto) {
        String gamerCards = gamerWithScoreDto.getCards().stream()
                .map(card -> card.getSymbol().getWord() + card.getType().getPattern())
                .collect(Collectors.joining(DELIMITER));
        System.out.printf("%s: %s - 결과: %d\n", gamerWithScoreDto.getName(), gamerCards, gamerWithScoreDto.getScore());
    }

    public static void printGameResult(Map<PlayerResult, List<GamerDto>> gameResults) {
        System.out.println("\n## 최종 승패");
        System.out.printf("딜러 : %d승, %d무, %d패 \n", gameResults.get(PlayerResult.LOSE).size(),
                gameResults.get(PlayerResult.DRAW).size(), gameResults.get(PlayerResult.WIN).size());
        for (PlayerResult playerResult : PlayerResult.values()) {
            printEachResult(gameResults.get(playerResult), playerResult);
        }
    }

    private static void printEachResult(List<GamerDto> playerDtos, PlayerResult playerResult) {
        for (GamerDto playerDto : playerDtos) {
            System.out.println(playerDto.getName() + " : " + playerResult.getName());
        }
    }
}
