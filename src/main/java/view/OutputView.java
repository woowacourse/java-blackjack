package view;

import domain.constant.GameResult;
import domain.game.GameResults;
import domain.participant.PlayerName;
import dto.DealerHandStatusDto;
import dto.PlayerHandStatusDto;
import dto.PlayingCardDto;

import java.util.List;

import static domain.constant.GameResult.*;
import static view.OutputFormatConverter.*;

public class OutputView {

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printFirstDrawStatus(final PlayingCardDto dealerCard, final List<PlayerHandStatusDto> playerHandStatuses) {
        System.out.println("딜러와 " + createPrintNamesMessage(playerHandStatuses) + "에게 2장을 나누었습니다.");
        System.out.println(convertDealerCardToPrintMessage(dealerCard));
        playerHandStatuses.forEach(playerHandStatus ->
                System.out.println(
                        convertPlayerCardsToPrintMessage(playerHandStatus.playerName(),
                                playerHandStatus.playingCards())));
        System.out.println();
    }

    private static String createPrintNamesMessage(final List<PlayerHandStatusDto> playerHandStatuses) {
        List<String> playerNames = playerHandStatuses.stream()
                .map(playerHandStatus ->
                        playerHandStatus.playerName().value()).toList();
        return String.join(", ", playerNames);
    }

    private static String convertDealerCardToPrintMessage(final PlayingCardDto dealerCard) {
        return "딜러카드: " + convertPlayingCardValueToString(dealerCard.playingCardValue())
                + convertPlayingCardShapeToString(dealerCard.playingCardShape());
    }

    private static String convertPlayerCardsToPrintMessage(final PlayerName playerName, final List<PlayingCardDto> playerCards) {
        return playerName.value() + ": " + String.join(", ", convertPlayingCardsToPrintMessage(playerCards));
    }

    private static String convertPlayingCardsToPrintMessage(final List<PlayingCardDto> playerCards) {
        List<String> list = playerCards.stream()
                .map(OutputView::convertPlayingCardToPrintMessage)
                .toList();
        return String.join(", ", list);
    }

    private static String convertPlayingCardToPrintMessage(final PlayingCardDto playingCard) {
        return convertPlayingCardValueToString(playingCard.playingCardValue())
                + convertPlayingCardShapeToString(playingCard.playingCardShape());
    }

    public static void printPlayerDrawStatus(final PlayerHandStatusDto playerHandStatuses) {
        System.out.println(
                convertPlayerCardsToPrintMessage(playerHandStatuses.playerName(),
                        playerHandStatuses.playingCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printFinalHandStatus(
            final DealerHandStatusDto dealerHandStatus,
            final List<PlayerHandStatusDto> playerHandStatuses
    ) {
        System.out.println("딜러 카드: " + convertPlayingCardsToPrintMessage(dealerHandStatus.playingCards())
                + " - 결과: " + dealerHandStatus.playerTotalScore().value());
        playerHandStatuses.forEach(playerHandStatus -> {
            System.out.println(convertPlayerCardsToPrintMessage(playerHandStatus.playerName(), playerHandStatus.playingCards())
                    + " - 결과: " + playerHandStatus.playerTotalScore().value());
        });
        System.out.println();
    }

    public static void printGameResult(final GameResults gameResults) {
        System.out.println("## 최종 승패");
        List<GameResult> dealerGameResults = gameResults.getDealerGameResults();
        System.out.println("딜러: "
                + getDealerGameResultCount(dealerGameResults, WIN) + "승 "
                + getDealerGameResultCount(dealerGameResults, LOSE) + "패 "
                + getDealerGameResultCount(dealerGameResults, DRAW) + "무");
        gameResults.getPlayerGameResults()
                .forEach((key, value) ->
                        System.out.println(convertPlayerGameResultToPrintMessage(key, value)));
    }

    private static int getDealerGameResultCount(final List<GameResult> dealerGameResults, final GameResult findResult) {
        return (int) dealerGameResults.stream()
                .filter(gameResult -> gameResult == findResult)
                .count();
    }

    private static String convertPlayerGameResultToPrintMessage(final PlayerName playerName, final GameResult gameResult) {
        return playerName.value() + ": " + convertGameResultToString(gameResult);
    }
}
