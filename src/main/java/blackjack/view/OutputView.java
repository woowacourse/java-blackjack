package blackjack.view;

import blackjack.domain.Outcome;
import blackjack.domain.card.Card;
import blackjack.view.dto.PlayerStatusDto;
import blackjack.view.dto.RoundStatusDto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String TWO_CARDS_DEAL_OUT_MESSAGE = "%s와 %s에게 2장을 나누었습니다.";
    private static final String PARTICIPANT_STATUS_MESSAGE = "%s: %s";
    private static final String DELIMITER = ", ";
    private static final String DEALER_CARD_ADD_MESSAGE = "딜러는 %d이하라 한장의 카드를 더 받았습니다.";
    private static final String GAME_RESULT_MESSAGE = "%s카드 : %s - 결과: %d";
    private static final String DEALER_RESULT_MESSAGE = "딜러: %d승 %d패 %d무";
    private static final String DEALER = "딜러";
    private static final String RESULT_MESSAGE = "\n## 최종 승패";

    public static void showPlayCardStatus(String name, List<Card> cards) {
        String text = String.format(PARTICIPANT_STATUS_MESSAGE, name, cards.stream()
                .map(card -> card.getCardStatus())
                .collect(Collectors.joining(DELIMITER)));
        System.out.println(text);
    }

    public static void showDealerAddCard(int turnOverCount) {
        System.out.println(String.format(DEALER_CARD_ADD_MESSAGE, turnOverCount));
    }

    public static void showFinalStatus(RoundStatusDto statusDto) {
        List<PlayerStatusDto> playerStatusDto = statusDto.getPlayerStatusDto();
        System.out.println(String.format(GAME_RESULT_MESSAGE,
                statusDto.getDealerName(),
                statusDto.getDealerCardStatus().stream().collect(Collectors.joining(DELIMITER)),
                statusDto.getDealerScore()));
        playerStatusDto.forEach(dto ->
                System.out.println(String.format(GAME_RESULT_MESSAGE,
                        dto.getPlayerName(),
                        dto.getPlayerCardStatus().stream().collect(Collectors.joining(DELIMITER)),
                        dto.getPlayerScore())));
    }

    public static void showInitialStatus(RoundStatusDto roundStatusDto) {
        String dealerName = roundStatusDto.getDealerName();
        List<String> dealerCardStatus = roundStatusDto.getDealerCardStatus();
        List<PlayerStatusDto> playerStatusDto = roundStatusDto.getPlayerStatusDto();

        String playerNames = playerStatusDto.stream()
                .map(dto -> dto.getPlayerName())
                .collect(Collectors.joining(DELIMITER));
        System.out.println(String.format(TWO_CARDS_DEAL_OUT_MESSAGE, dealerName, playerNames));
        System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE,
                dealerName,
                dealerCardStatus.stream().collect(Collectors.joining(DELIMITER))));
        playerStatusDto.forEach(dto ->
                System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE,
                        dto.getPlayerName(),
                        dto.getPlayerCardStatus().stream().collect(Collectors.joining(DELIMITER)))));
    }

    public static void showOutComes(Map<String, List<Outcome>> outcomes) {
        System.out.println(RESULT_MESSAGE);
        List<Outcome> dealerOutcomes = outcomes.remove(DEALER);
        System.out.println(String.format(DEALER_RESULT_MESSAGE,
                findWinCount(dealerOutcomes), findLoseCount(dealerOutcomes), findDrawCount(dealerOutcomes)));

        outcomes.keySet().forEach(name ->
                System.out.println(String.format(PARTICIPANT_STATUS_MESSAGE, name, outcomes.get(name).get(0).getName())));
    }

    private static int findWinCount(List<Outcome> dealerOutcomes) {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isWin)
                .count();
    }

    private static int findLoseCount(List<Outcome> dealerOutcomes) {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isLose)
                .count();
    }

    private static int findDrawCount(List<Outcome> dealerOutcomes) {
        return (int) dealerOutcomes.stream()
                .filter(Outcome::isDraw)
                .count();
    }
}
