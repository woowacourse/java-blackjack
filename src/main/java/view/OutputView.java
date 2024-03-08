package view;

import domain.constant.GameResult;
import domain.dto.DealerHandStatusDto;
import domain.dto.PlayerGameResultDto;
import domain.dto.PlayerHandStatusDto;
import domain.dto.PlayingCardDto;
import domain.participant.PlayerName;

import java.util.List;

import static view.FormatConverter.*;

public class OutputView {

    public static void printErrorMessage(final String message) {
        System.out.println(message);
    }

    public static void printFirstDrawStatus(final PlayingCardDto dealerCardDto, final List<PlayerHandStatusDto> playerHandStatuses) {
        System.out.println("딜러와 " + createNamesMessage(playerHandStatuses) + "에게 2장을 나누었습니다.");
        System.out.println(convertDealerCardToString(dealerCardDto));
        playerHandStatuses.forEach(playerHandStatusDto -> System.out.println(convertPlayerCardsToString(playerHandStatusDto.playerName(), playerHandStatusDto.playingCards())));
        System.out.println();
    }

    private static String createNamesMessage(final List<PlayerHandStatusDto> playerHandStatuses) {
        List<String> playerNames = playerHandStatuses.stream().map(playerHandStatusDto -> playerHandStatusDto.playerName().value()).toList();
        return String.join(", ", playerNames);
    }

    private static String convertDealerCardToString(final PlayingCardDto dealerCardDto) {
        return "딜러카드: " + convertPlayingCardValueToString(dealerCardDto.playingCardValue())
                + convertPlayingCardShapeToString(dealerCardDto.playingCardShape());
    }

    private static String convertPlayerCardsToString(final PlayerName playerName, List<PlayingCardDto> playingCardDtos) {
        return playerName.value() + ": " + String.join(", ", convertPlayingCardsToString(playingCardDtos));
    }

    private static String convertPlayingCardsToString(final List<PlayingCardDto> playingCardDtos) {
        List<String> list = playingCardDtos.stream()
                .map(OutputView::convertPlayingCardToString)
                .toList();
        return String.join(", ", list);
    }

    private static String convertPlayingCardToString(final PlayingCardDto playingCardDto) {
        return convertPlayingCardValueToString(playingCardDto.playingCardValue())
                + convertPlayingCardShapeToString(playingCardDto.playingCardShape());
    }

    public static void printPlayerDrawStatus(final PlayerHandStatusDto playerHandStatusDto) {
        System.out.println(convertPlayerCardsToString(playerHandStatusDto.playerName(), playerHandStatusDto.playingCards()));
    }

    public static void printDealerDrawMessage() {
        System.out.println();
        System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
        System.out.println();
    }

    public static void printFinalHandStatus(final DealerHandStatusDto dealerHandStatusDto, final List<PlayerHandStatusDto> playerHandStatusDtos) {
        System.out.println("딜러 카드: " + convertPlayingCardsToString(dealerHandStatusDto.playingCards()) + " - 결과: " + dealerHandStatusDto.playingCardSum());
        playerHandStatusDtos.forEach(playerHandStatusDto -> System.out.println(
                convertPlayerCardsToString(playerHandStatusDto.playerName(), playerHandStatusDto.playingCards()) + " - 결과: " + playerHandStatusDto.playingCardSum()));
        System.out.println();
    }

    public static void printGameResult(final List<GameResult> dealerGameResult, final List<PlayerGameResultDto> playerGameResultDtos) {
        System.out.println("## 최종 승패");
        long dealerWinCount = dealerGameResult.stream()
                .filter(gameResult -> gameResult == GameResult.WIN)
                .count();
        long dealerLoseCount = dealerGameResult.size() - dealerWinCount;
        System.out.println("딜러: " + dealerWinCount + "승 " + dealerLoseCount + "패");
        playerGameResultDtos.forEach(playerGameResultDto -> System.out.println(getPlayerResult(playerGameResultDto)));
    }

    private static String getPlayerResult(final PlayerGameResultDto playerGameResultDto) {
        return playerGameResultDto.playerName().value() + ": " + convertGameResultToString(playerGameResultDto.gameResult());
    }
}
