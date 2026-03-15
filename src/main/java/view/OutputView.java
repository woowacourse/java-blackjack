package view;

import domain.card.Card;
import dto.*;
import view.message.RankMessage;
import view.message.SuitMessage;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    private static final String COMMA_SEPARATOR = ", ";
    private static final String DEALER_DISPLAY_NAME = "딜러";

    public void showInitialHandsOfParticipants(DealerDto dealerDto, PlayersDto playersDto) {
        String playerNames = playersDto.getPlayers()
                .stream()
                .map(PlayerDto::getName)
                .collect(Collectors.joining(COMMA_SEPARATOR));

        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", DEALER_DISPLAY_NAME, playerNames);

        Card firstCard = dealerDto.getFirstOpenCard();
        System.out.printf("%s카드: %s%s\n", DEALER_DISPLAY_NAME, RankMessage.of(firstCard.getRank()), SuitMessage.of(firstCard.getSuit()));

        for (PlayerDto playerInfo : playersDto.getPlayers()) {
            String name = playerInfo.getName();
            List<Card> cards = playerInfo.getCards();
            System.out.printf("%s카드: %s\n", name, formatCards(cards));
        }

        System.out.println();
    }

    public void showPlayerHand(PlayerDto playerDto) {
        List<Card> cards = playerDto.getCards();
        System.out.printf("%s카드: %s\n", playerDto.getName(), formatCards(cards));
    }

    public void showDealerHitMessage() {
        System.out.printf("\n%s는 16이하라 한장의 카드를 더 받았습니다.\n", DEALER_DISPLAY_NAME);
    }

    public void showDealerStandMessage() {
        System.out.printf("\n%s는 17이상이므로 카드를 받지 않았습니다.\n", DEALER_DISPLAY_NAME);
    }

    public void showDealerHand(DealerDto dealerDto) {
        System.out.printf("%s카드: %s\n", DEALER_DISPLAY_NAME, formatCards(dealerDto.getDealerHand()));
    }

    public void showHandResultsOfParticipants(DealerDto dealerDto, PlayersDto playersDto) {
        System.out.printf("\n%s카드: %s - 결과: %d\n", DEALER_DISPLAY_NAME, formatCards(dealerDto.getDealerHand()), dealerDto.getScore());

        for (PlayerDto playerInfo : playersDto.getPlayers()) {
            String name = playerInfo.getName();
            List<Card> cards = playerInfo.getCards();
            int score = playerInfo.getScore();

            System.out.printf("%s카드: %s - 결과: %d\n", name, formatCards(cards), score);
        }
    }

    public void showProfitResult(ProfitResultDto profitResultDto) {
        System.out.println("\n## 최종 수익");

        System.out.printf("%s: %,d\n", DEALER_DISPLAY_NAME, profitResultDto.getDealerProfitResult().intValue());

        for (Map.Entry<String, BigDecimal> playersProfitResult : profitResultDto.getPlayersProfitResult().entrySet()) {
            String name = playersProfitResult.getKey();
            BigDecimal profit = playersProfitResult.getValue();
            System.out.printf("%s: %,d\n", name, profit.intValue());
        }
    }

    private String formatCards(List<Card> cards) {
        return cards.stream()
                .map(card -> RankMessage.of(card.getRank()) + SuitMessage.of(card.getSuit()))
                .collect(java.util.stream.Collectors.joining(COMMA_SEPARATOR));
    }

    public static void printErrorMessage(String message) {
        System.out.println(message);
    }
}
