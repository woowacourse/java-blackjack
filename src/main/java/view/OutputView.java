package view;

import domain.card.Card;
import dto.*;
import view.message.RankMessage;
import view.message.SuitMessage;

import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String COMMA_SEPARATOR = ", ";
    private static final String DEALER_DISPLAY_NAME = "딜러";

    public void showInitialHandsOfParticipants(DealerDto dealerDto, PlayersDto playersDto) {
        String playerNames = String.join(COMMA_SEPARATOR, playersDto.getPlayersHand().keySet());
        System.out.printf("\n%s와 %s에게 2장을 나누었습니다.\n", DEALER_DISPLAY_NAME, playerNames);

        Card firstCard = dealerDto.getFirstOpenCard();
        System.out.printf("%s카드: %s%s\n", DEALER_DISPLAY_NAME, RankMessage.of(firstCard.getRank()), SuitMessage.of(firstCard.getSuit()));

        for (Map.Entry<String, List<Card>> playerInfo : playersDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            System.out.printf("%s카드: %s\n", name, formatCards(cards));
        }

        System.out.println();
    }

    public void showPlayerHand(PlayerDto playerDto) {
        List<Card> cards = playerDto.getPlayerHand();
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

        for (Map.Entry<String, List<Card>> playerInfo : playersDto.getPlayersHand().entrySet()) {
            String name = playerInfo.getKey();
            List<Card> cards = playerInfo.getValue();
            int score = playersDto.getPlayersScore().get(name);

            System.out.printf("%s카드: %s - 결과: %d\n", name, formatCards(cards), score);
        }
    }

    public void showProfitResult(ProfitResultDto profitResultDto) {
        System.out.println("\n## 최종 수익");

        System.out.printf("%s: %,d\n", DEALER_DISPLAY_NAME, profitResultDto.getDealerProfitResult());

        for (Map.Entry<String, Integer> playersProfitResult : profitResultDto.getPlayersProfitResult().entrySet()) {
            String name = playersProfitResult.getKey();
            int profit = playersProfitResult.getValue();
            System.out.printf("%s: %,d\n", name, profit);
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
