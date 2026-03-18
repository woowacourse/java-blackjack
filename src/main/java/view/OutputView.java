package view;

import dto.BetProfitDto;
import dto.CardDto;
import dto.DealerCardDto;
import dto.DealerCardWithScoreDto;
import dto.PlayerCardDto;
import dto.PlayerCardWithScoreDto;
import dto.PlayerProfitDto;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String LINE_SEPARATOR = "\n";
    private static final String STRING_JOIN_DELIMITER = ", ";
    private static final String DISTRIBUTE_INITIAL_CARD = "딜러와 %s에게 2장을 나누었습니다.";
    private static final String DEALER_CARD = "딜러카드: %s";
    private static final String PLAYER_CARD = "%s카드: %s";
    private static final String SCORE = " - 결과: %d";
    private static final String DEALER_DRAW = "딜러는 16이하라 한장의 카드를 더 받았습니다.";
    private static final String FINAL_PROFIT = "## 최종 수익";
    private static final String DEALER_PROFIT = "딜러: %d";
    private static final String PLAYER_PROFIT = "%s: %d";

    public void printParticipantCards(DealerCardDto dealerCardDto, List<PlayerCardDto> playerCardDtos) {
        String playerNames = playerCardDtos.stream()
                .map(PlayerCardDto::name)
                .collect(Collectors.joining(", "));

        System.out.printf(LINE_SEPARATOR + DISTRIBUTE_INITIAL_CARD + LINE_SEPARATOR, playerNames);
        printDealerFirstCard(dealerCardDto);
        for (PlayerCardDto playerCardDto : playerCardDtos) {
            printPlayerCard(playerCardDto);
        }
        System.out.print(LINE_SEPARATOR);
    }

    private void printDealerFirstCard(DealerCardDto dealerCardDto) {
        System.out.printf(DEALER_CARD + LINE_SEPARATOR, dealerCardDto.cards().getFirst().cardName());
    }

    public void printPlayerCard(PlayerCardDto playerCardDto) {
        String card = collectCards(playerCardDto.cards());

        System.out.printf(PLAYER_CARD + LINE_SEPARATOR, playerCardDto.name(), card);
    }

    public void printDealerCardWithScore(DealerCardWithScoreDto dealerCardWithScoreDto) {
        String card = collectCards(dealerCardWithScoreDto.cards());

        System.out.printf(LINE_SEPARATOR + DEALER_CARD + SCORE + LINE_SEPARATOR, card, dealerCardWithScoreDto.score());
    }

    public void printPlayerCardWithScore(PlayerCardWithScoreDto playerCardWithScoreDto) {
        String card = collectCards(playerCardWithScoreDto.cards());

        System.out.printf(PLAYER_CARD + SCORE + LINE_SEPARATOR,
                playerCardWithScoreDto.name(),
                card,
                playerCardWithScoreDto.score());
    }

    private String collectCards(List<CardDto> cards) {
        return cards.stream()
                .map(CardDto::cardName)
                .collect(Collectors.joining(STRING_JOIN_DELIMITER));
    }

    public void printDealerHit() {
        System.out.println(LINE_SEPARATOR + DEALER_DRAW);
    }

    public void printProfit(BetProfitDto betProfitDto) {
        System.out.println(LINE_SEPARATOR + FINAL_PROFIT);
        System.out.printf(DEALER_PROFIT + LINE_SEPARATOR, betProfitDto.dealerProfit());

        for (PlayerProfitDto playerProfitDto : betProfitDto.playerProfits()) {
            System.out.printf(PLAYER_PROFIT + LINE_SEPARATOR, playerProfitDto.name(), playerProfitDto.profit());
        }
    }
}
