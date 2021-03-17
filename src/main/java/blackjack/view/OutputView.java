package blackjack.view;

import blackjack.controller.dto.PlayerStatusDto;
import blackjack.controller.dto.RoundStatusDto;
import blackjack.domain.Result;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String TWO_CARDS_DEAL_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_STATUS_MESSAGE = "%s: %s";
    private static final String DELIMITER = ", ";
    private static final String GAME_RESULT_MESSAGE = "%s카드 : %s - 결과: %d";

    public static void showPlayCardStatus(String name, List<String> cards) {
        System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE, name, String.join(DELIMITER, cards)));
    }

    public static void showDealerAddCard(int turnOverCount) {
        System.out.println(String.format("딜러는 %d이하라 한장의 카드를 더 받았습니다.", turnOverCount));
        nextLine();
    }

    public static void showFinalStatus(RoundStatusDto statusDto) {
        List<PlayerStatusDto> playerStatusDto = statusDto.getPlayerStatusDto();
        System.out.println(String.format(GAME_RESULT_MESSAGE,
                statusDto.getDealerName(),
                String.join(DELIMITER, statusDto.getDealerCardStatus()),
                statusDto.getDealerScore()));
        playerStatusDto.forEach(dto ->
                System.out.println(String.format(GAME_RESULT_MESSAGE,
                        dto.getPlayerName(),
                        String.join(DELIMITER, dto.getPlayerCardStatus()),
                        dto.getPlayerScore())));
        nextLine();
    }

    public static void showInitialStatus(RoundStatusDto roundStatusDto) {
        String dealerName = roundStatusDto.getDealerName();
        List<String> dealerCardStatus = roundStatusDto.getDealerCardStatus();
        List<PlayerStatusDto> playerStatusDto = roundStatusDto.getPlayerStatusDto();

        String playerNames = playerStatusDto.stream()
                .map(PlayerStatusDto::getPlayerName)
                .collect(Collectors.joining(DELIMITER));

        System.out.println(String.format(TWO_CARDS_DEAL_OUT_MESSAGE, dealerName, playerNames));
        showDealerStatus(dealerName, dealerCardStatus);
        showPlayersStatus(playerStatusDto);
        nextLine();
    }

    private static void nextLine() {
        System.out.println();
    }

    private static void showPlayersStatus(List<PlayerStatusDto> playerStatusDto) {
        playerStatusDto.forEach(dto ->
                System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE,
                        dto.getPlayerName(),
                        String.join(DELIMITER, dto.getPlayerCardStatus()))));
    }

    private static void showDealerStatus(String dealerName, List<String> dealerCardStatus) {
        System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE,
                dealerName,
                String.join(DELIMITER, dealerCardStatus)));
    }

    public static void showOutcomes(Result result) {
        System.out.println("## 최종 승패");
        System.out.println(String.format("딜러: %f", result.getDealerProfit()));
        Map<String, Double> playerProfits = result.getPlayerProfits();
        for (String playerName : playerProfits.keySet()) {
            System.out.println(String.format("%s: %f", playerName, playerProfits.get(playerName)));
        }
    }
}
