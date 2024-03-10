package blackjack.view;

import blackjack.dto.CardDto;
import blackjack.dto.DealerWinningResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.PlayerHandsScoreDto;
import blackjack.dto.PlayerWinningResultDto;
import blackjack.dto.StartCardsDto;
import blackjack.dto.WinningResultDto;
import blackjack.view.format.CardNumberFormat;
import blackjack.view.format.CardShapeFormat;
import blackjack.view.format.WinningStatusFormat;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {

    private static final String DELIMITER = ", ";

    public void printStartCards(final StartCardsDto startCards) {
        final List<PlayerCardsDto> playersCards = startCards.playersCards();
        final PlayerCardsDto dealerCards = startCards.dealerCards();

        System.out.printf("%n%s와 %s에게 2장을 나누었습니다.%n", dealerCards.name(),
                String.join(DELIMITER, playersCards.stream().map(PlayerCardsDto::name).toList()));

        System.out.printf("%s: %s%n", dealerCards.name(),
                convertToCardsFormat(dealerCards.cards()));

        playersCards.forEach(this::printPlayerCard);
    }

    private String convertToCardFormat(final CardDto card) {
        return CardNumberFormat.valueOf(card.number()).getFormat() +
                CardShapeFormat.valueOf(card.shape()).getFormat();
    }

    private String convertToCardsFormat(final List<CardDto> cards) {
        return String.join(DELIMITER, cards.stream().map(this::convertToCardFormat).toList());
    }

    public void printPlayerCard(final PlayerCardsDto playerCards) {
        System.out.printf("%s카드: %s%n", playerCards.name(), convertToCardsFormat(playerCards.cards()));
    }

    public void printFinalResult(final FinalHandsScoreDto finalHandsScore, final WinningResultDto winningResult) {
        printCardScore(finalHandsScore);
        printWinningResult(winningResult);
    }

    private void printCardScore(final FinalHandsScoreDto finalHandsScore) {
        printDealerCardScore(finalHandsScore.dealerHandsScore());
        printPlayersCardScore(finalHandsScore.playersHandsScore());
    }

    private void printDealerCardScore(final PlayerHandsScoreDto dealerCardScore) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n",
                dealerCardScore.name(),
                convertToCardsFormat(dealerCardScore.handsScore().cards()),
                dealerCardScore.handsScore().score());
    }

    private void printPlayersCardScore(final List<PlayerHandsScoreDto> playersHandsScore) {
        playersHandsScore.forEach(this::printPlayerCardScore);
    }

    private void printPlayerCardScore(final PlayerHandsScoreDto playerHandsScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n",
                playerHandsScore.name(),
                convertToCardsFormat(playerHandsScore.handsScore().cards()),
                playerHandsScore.handsScore().score());
    }

    private void printWinningResult(final WinningResultDto winningResult) {
        System.out.printf("%n## 최종 승패%n");
        printDealerWinningResult(winningResult.dealerWinningResults(), winningResult.dealerName());
        printPlayersWinningResult(winningResult.playersWinningResult());
    }

    private void printDealerWinningResult(final List<DealerWinningResultDto> dealerWinningResult, final String dealerName) {
        System.out.printf("%s: %s%n", dealerName, convertToWinningResult(dealerWinningResult));

    }

    private String convertToWinningResult(final List<DealerWinningResultDto> dealerWinningResults) {
        return dealerWinningResults.stream()
                .map(winningResult ->
                        winningResult.count() + WinningStatusFormat.valueOf(winningResult.winStatus()).getFormat())
                .collect(Collectors.joining(" "));
    }

    private void printPlayersWinningResult(final List<PlayerWinningResultDto> playersWinningResult) {
        playersWinningResult.forEach(playerWinningResult ->
                System.out.printf("%s: %s%n",
                        playerWinningResult.name(),
                        WinningStatusFormat.valueOf(playerWinningResult.winnStatus()).getFormat()));

    }

    public void printDealerMoreCard(int count, final String dealerName) {
        while (count-- > 0) {
            System.out.printf("%s는 16이하라 한장의 카드를 더 받았습니다.%n", dealerName);
        }
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
