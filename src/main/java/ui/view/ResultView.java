package ui.view;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;
import ui.dto.CardDto;
import ui.dto.GameResultDto;
import ui.dto.ParticipantCardsDto;
import ui.dto.PlayerDto;
import ui.dto.PlayerDtoWithProfit;

public class ResultView {
    public static final int DEALER_HIT_STAND_BOUNDARY = 16;
    private static final String DELIMITER = ", ";
    private static final int INITIAL_CARD_COUNT = 2;

    public void printParticipantsCards(ParticipantCardsDto dto) {
        printEmptyLine();
        System.out.println(
                "딜러와 " + joinPlayersNameByDelimiter(dto.players()) + "에게 "
                        + INITIAL_CARD_COUNT
                        + "장을 나누었습니다.");
        CardDto dealerCard = dto.dealerFirstCard();
        System.out.println("딜러카드: " + dealerCard.rank().getDisplayValue() + dealerCard.suit().getValue());
        printParticipantsCard(dto.players());
        printEmptyLine();
    }

    private void printEmptyLine() {
        System.out.println();
    }

    private String joinPlayersNameByDelimiter(List<PlayerDto> players) {
        return players.stream()
                .map(PlayerDto::name)
                .collect(Collectors.joining(DELIMITER));
    }

    private void printParticipantsCard(List<PlayerDto> players) {
        for (PlayerDto player : players) {
            printCards(player);
        }
    }

    public void printCards(PlayerDto player) {
        System.out.println(
                player.name() + "카드: " + cardsToString(player.cards()));
    }

    private String cardsToString(List<CardDto> cards) {
        return cards.stream()
                .map(card -> card.rank().getDisplayValue() + card.suit().getValue())
                .collect(Collectors.joining(DELIMITER));
    }

    public void printDealerHitStand(List<Boolean> hitHistory) {
        for (boolean isHit : hitHistory) {
            printDealerHitStand(isHit);
        }
    }

    private void printDealerHitStand(boolean hit) {
        printEmptyLine();
        if (hit) {
            System.out.println("딜러는 " + DEALER_HIT_STAND_BOUNDARY + " 이하라 한장의 카드를 더 받았습니다.");
            return;
        }
        System.out.println("딜러는 " + (DEALER_HIT_STAND_BOUNDARY + 1) + " 이상이라 카드를 받지 않았습니다.");
    }

    public void printGameResult(GameResultDto gameResultDto) {
        printCardsWithResult(gameResultDto);
        printProfits(gameResultDto.dealerProfit(), gameResultDto.players());
    }

    private void printProfits(BigDecimal dealerProfit, List<PlayerDtoWithProfit> players) {
        printEmptyLine();
        System.out.println("## 최종 승패");
        System.out.println("딜러: " + dealerProfit.setScale(0, RoundingMode.DOWN));

        for (PlayerDtoWithProfit profit : players) {
            System.out.println(profit.name() + ": " + profit.profit()
                    .setScale(0, RoundingMode.DOWN));
        }
    }

    private void printCardsWithResult(GameResultDto dto) {
        printEmptyLine();
        System.out.println(
                "딜러카드: " + cardsToString(
                        dto.dealerCards()) + " - 결과: "
                        + dto.dealerScore());

        for (PlayerDtoWithProfit player : dto.players()) {
            printCardWithResult(player);
        }
    }

    private void printCardWithResult(PlayerDtoWithProfit player) {
        System.out.println(
                player.name() + "카드: " + cardsToString(
                        player.cards()) + " - 결과: "
                        + player.score());
    }
}
