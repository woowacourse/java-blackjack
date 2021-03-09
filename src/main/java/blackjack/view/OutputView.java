package blackjack.view;

import blackjack.controller.dto.PlayerCardsDTO;
import blackjack.controller.dto.PlayerResultDTO;
import blackjack.controller.dto.UsersProfitDTO;
import blackjack.domain.player.Name;
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

    public static void printFinalCardsMessage(List<PlayerResultDTO> userDTOs,
        PlayerResultDTO dealerDTO) {
        System.out.println();
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        userDTOs.forEach(userDTO -> stringJoiner.add(userDTO.getName()));
        printUserFinalCards(dealerDTO);
        userDTOs.forEach(OutputView::printUserFinalCards);
    }

    public static void printDealerDrawCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
    }

    public static void printFinalProfits(UsersProfitDTO usersProfitDTO) {
        System.out.println(NEW_LINE + "## 최종 수익");
        Map<Name, Integer> profits = usersProfitDTO.getProfits();
        StringBuilder stringBuilder = new StringBuilder();
        buildUsersProfitMessage(profits, stringBuilder);
        int dealerProfit = 0;
        for (Name name : profits.keySet()) {
            dealerProfit -= profits.get(name);
        }
        System.out.printf("딜러: %d" + NEW_LINE, dealerProfit);
        System.out.print(stringBuilder.toString());
    }

    private static void buildUsersProfitMessage(Map<Name, Integer> result,
        StringBuilder stringBuilder) {
        for (Name name : result.keySet()) {
            stringBuilder
                .append(name.getName())
                .append(": ")
                .append(result.get(name))
                .append(NEW_LINE);
        }
    }
}
