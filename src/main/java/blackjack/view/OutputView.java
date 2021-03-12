package blackjack.view;

import blackjack.domain.player.Name;
import blackjack.domain.player.dto.resultProfitDTO;
import blackjack.domain.player.dto.PlayerDTO;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class OutputView {
    private static final String NEW_LINE = System.lineSeparator();
    private static final String DELIMITER = ", ";

    private static void printUserCards(PlayerDTO playerDTO) {
        System.out.print(playerDTO.getName() + "카드: ");
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTO.getCards().forEach(card -> stringJoiner.add(card.toString()));
        System.out.print(stringJoiner.toString());
    }

    public static void printUserInitialCards(PlayerDTO playerDTO) {
        printUserCards(playerDTO);
        System.out.println();
    }

    public static void printGiveTwoCardsMessage(List<PlayerDTO> playerDTOs, PlayerDTO dealerDTO) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTOs.forEach(playerDTO -> stringJoiner.add(playerDTO.getName()));
        System.out.println("딜러와 " + stringJoiner.toString() + "에게 2장을 나누었습니다.");
        printUserInitialCards(dealerDTO);
        playerDTOs.forEach(OutputView::printUserInitialCards);
        System.out.println();
    }

    private static void printUserFinalCards(PlayerDTO playerDTO) {
        printUserCards(playerDTO);
        System.out.println(" - 결과: " + playerDTO.getScore());
    }

    public static void printFinalCardsMessage(List<PlayerDTO> playerDTOs, PlayerDTO dealerDTO) {
        StringJoiner stringJoiner = new StringJoiner(DELIMITER);
        playerDTOs.forEach(playerDTO -> stringJoiner.add(playerDTO.getName()));
        printUserFinalCards(dealerDTO);
        playerDTOs.forEach(OutputView::printUserFinalCards);
        System.out.println();
    }

    public static void printDealerDrawCardMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printResultMessage(resultProfitDTO gameResultDTO) {
        System.out.println("## 최종 수익");
        System.out.println("딜러: " + gameResultDTO.getDealerProfit());
        System.out.print(getPlayersResultMessage(gameResultDTO.getUserProfits()));
    }

    private static String getPlayersResultMessage(Map<Name, Integer> result) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Name name : result.keySet()) {
            stringBuilder
                .append(name.getName())
                .append(": ")
                .append(result.get(name))
                .append(NEW_LINE);
        }
        return stringBuilder.toString();
    }
}