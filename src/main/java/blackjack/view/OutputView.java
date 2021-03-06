package blackjack.view;

import blackjack.controller.dto.PlayerCardsDTO;
import blackjack.controller.dto.PlayerResultDTO;
import blackjack.domain.ResultType;
import blackjack.domain.player.Name;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";

    private static void printPlayerCards(PlayerCardsDTO playerCardsDTO) {
        System.out.print(playerCardsDTO.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerCardsDTO.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.print(stringJoiner.toString());
    }

    public static void printPlayerCardsAndNewLine(PlayerCardsDTO playerCardsDTO) {
        printPlayerCards(playerCardsDTO);
        System.out.println();
    }

    public static void printGiveTwoCardsMessage(List<PlayerCardsDTO> usersCardsDTOs,
        PlayerCardsDTO dealerCardsDTO) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        usersCardsDTOs.forEach(playerCardsDTO -> stringJoiner.add(playerCardsDTO.getName()));
        System.out.println("딜러와 " + stringJoiner.toString() + "에게 2장을 나누었습니다.");
        printPlayerCardsAndNewLine(dealerCardsDTO);
        usersCardsDTOs.forEach(OutputView::printPlayerCardsAndNewLine);
        System.out.println();
    }

    private static void printUserFinalCards(PlayerResultDTO playerDTO) {
        printPlayerCards(new PlayerCardsDTO(new Name(playerDTO.getName()), playerDTO.getCards()));
        System.out.println(" - 결과: " + playerDTO.getScore());
    }

    public static void printFinalCardsMessage(List<PlayerResultDTO> playerDTOs, PlayerResultDTO dealerDTO) {
        System.out.println();
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTOs.forEach(playerDTO -> stringJoiner.add(playerDTO.getName()));
        printUserFinalCards(dealerDTO);
        playerDTOs.forEach(OutputView::printUserFinalCards);
    }

    public static void printDealerDrawCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printResultMessage(Map<Name, ResultType> result) {
        System.out.println();
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
