package blackjack.view;

import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.CardDto;
import blackjack.dto.DealerBetResultDto;
import blackjack.dto.DealerMoreCardsDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerBetResultDto;
import blackjack.dto.PlayerCardsDto;
import blackjack.dto.PlayerHandsScoreDto;
import blackjack.dto.StartCardsDto;
import blackjack.view.format.CardNumberFormat;
import blackjack.view.format.CardShapeFormat;
import java.util.List;

public class OutputView {

    private static final String DELIMITER = ", ";

    public void printStartCards(final StartCardsDto startCards) {
        final List<PlayerCardsDto> playersCards = startCards.playersCards();
        final PlayerCardsDto dealerCards = startCards.dealerCards();

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", dealerCards.name(),
                String.join(DELIMITER, playersCards.stream().map(PlayerCardsDto::name).toList()),
                startCards.eachCardCount());

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

    public void printFinalResult(final FinalHandsScoreDto finalHandsScore, final BetRevenueResultDto betRevenueResult) {
        printCardScore(finalHandsScore);
        printBetResult(betRevenueResult);
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

    private void printBetResult(final BetRevenueResultDto betResult) {
        System.out.printf("%n## 최종 수익%n");
        printDealerBetResult(betResult.dealerBetResults());
        printPlayersBetResult(betResult.playersBetResult());
    }

    private void printDealerBetResult(final DealerBetResultDto dealerBetResult) {
        System.out.printf("%s: %f%n", dealerBetResult.name(), dealerBetResult.revenue());

    }

    private void printPlayersBetResult(final List<PlayerBetResultDto> playersWinningResult) {
        playersWinningResult.forEach(playerWinningResult ->
                System.out.printf("%s: %f%n", playerWinningResult.name(), playerWinningResult.revenue()));
    }

    public void printDealerMoreCard(final DealerMoreCardsDto dealerMoreCard) {
        int count = dealerMoreCard.count();

        while (count-- > 0) {
            System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n", dealerMoreCard.name(), dealerMoreCard.limitScore());
        }
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
