package blackjack.view;

import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import blackjack.domain.player.PlayerDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";

    private static void printUserCards(PlayerDto playerDto) {
        System.out.print(playerDto.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDto.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.print(stringJoiner.toString());
    }

    public static void printUserInitialCards(PlayerDto playerDto) {
        printUserCards(playerDto);
        System.out.println();
    }

    public static void printGiveTwoCardsMessage(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDtos.forEach(playerDto -> stringJoiner.add(playerDto.getName()));
        System.out.println("딜러와 " + stringJoiner.toString() + "에게 2장을 나누었습니다.");
        printUserInitialCards(dealerDto);
        playerDtos.forEach(OutputView::printUserInitialCards);
        System.out.println();
    }

    private static void printUserFinalCards(PlayerDto playerDto) {
        printUserCards(playerDto);
        System.out.println(" - 결과: " + playerDto.getValue());
    }

    public static void printFinalCardsMessage(List<PlayerDto> playerDtos, PlayerDto dealerDto) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDtos.forEach(playerDto -> stringJoiner.add(playerDto.getName()));
        printUserFinalCards(dealerDto);
        playerDtos.forEach(OutputView::printUserFinalCards);
        System.out.println();
    }

    public static void printDealerDrawCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printResultMessage(Map<Name, ResultType> result) {
        System.out.println("## 최종 승패");
        Map<ResultType, Integer> resultCount = new HashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        printPlayersResultMessage(result, stringBuilder);
        for (Name name : result.keySet()) {
            resultCount.put(result.get(name), resultCount.getOrDefault(result.get(name), 0) + 1);
        }
        printDealerResultMessage(resultCount);
        System.out.print(stringBuilder.toString());
    }

    private static void printPlayersResultMessage(Map<Name, ResultType> result,
        StringBuilder stringBuilder) {
        for (Name name : result.keySet()) {
            stringBuilder
                .append(name.getName())
                .append(": ")
                .append(result.get(name).getResult())
                .append(NEW_LINE);
        }
    }

    private static void printDealerResultMessage(Map<ResultType, Integer> resultCount) {
        System.out.println("딜러: " +
            resultCount.getOrDefault(ResultType.LOSS, 0) + "승 " +
            resultCount.getOrDefault(ResultType.WIN, 0) + "패 " +
            resultCount.getOrDefault(ResultType.DRAW, 0) + "무승부");
    }
}
