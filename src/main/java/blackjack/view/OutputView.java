package blackjack.view;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.dealer.Dealer;
import blackjack.domain.player.PlayerName;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.rule.state.DealerHitState;
import blackjack.domain.rule.state.State;
import blackjack.dto.BetRevenueResultDto;
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
        final Hands dealerHands = startCards.dealerHands();

        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", Dealer.DEALER_NAME,
                String.join(DELIMITER, playersCards.stream().map(PlayerCardsDto::getName).toList()),
                State.START_CARD_COUNT);

        System.out.printf("%s: %s%n", Dealer.DEALER_NAME, convertToCardsFormat(dealerHands));

        playersCards.forEach(playerCardsDto -> printPlayerCard(playerCardsDto.name(), playerCardsDto.cards()));
        System.out.printf("%n");
    }

    private String convertToCardFormat(final Card card) {
        return CardNumberFormat.from(card.number()).getFormat() + CardShapeFormat.from(card.shape()).getFormat();
    }

    private String convertToCardsFormat(final Hands hands) {
        return String.join(DELIMITER, hands.getCards().stream().map(this::convertToCardFormat).toList());
    }

    public void printPlayerCard(final PlayerName name, final Hands hands) {
        System.out.printf("%s카드: %s%n", name.getName(), convertToCardsFormat(hands));
    }

    public void printFinalResult(final FinalHandsScoreDto finalHandsScore, final BetRevenueResultDto betRevenueResult) {
        printCardScore(finalHandsScore);
        printBetResult(betRevenueResult);
    }

    private void printCardScore(final FinalHandsScoreDto finalHandsScore) {
        printDealerCardScore(finalHandsScore.dealerHands());
        printPlayersCardScore(finalHandsScore.playersHandsScore());
    }

    private void printDealerCardScore(final Hands dealerHands) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n", Dealer.DEALER_NAME,
                convertToCardsFormat(dealerHands), dealerHands.calculateScore().toInt());
    }

    private void printPlayersCardScore(final List<PlayerHandsScoreDto> playersHandsScore) {
        playersHandsScore.forEach(this::printPlayerCardScore);
    }

    private void printPlayerCardScore(final PlayerHandsScoreDto playerHandsScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n", playerHandsScore.getName(),
                convertToCardsFormat(playerHandsScore.hands()), playerHandsScore.handsScore());
    }

    private void printBetResult(final BetRevenueResultDto betResult) {
        System.out.printf("%n## 최종 수익%n");
        printDealerBetResult(betResult.dealerRevenue());
        printPlayersBetResult(betResult.playersBetResult());
    }

    private void printDealerBetResult(final BetRevenue dealerBetRevenue) {
        System.out.printf("%s: %f%n", Dealer.DEALER_NAME, dealerBetRevenue.value());

    }

    private void printPlayersBetResult(final List<PlayerBetResultDto> playersWinningResult) {
        playersWinningResult.forEach(playerWinningResult -> System.out.printf("%s: %f%n", playerWinningResult.getName(),
                playerWinningResult.revenue().value()));
    }

    public void printDealerMoreCard(int count) {
        while (count-- > 0) {
            System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_NAME, DealerHitState.NEED_CARD_NUMBER_MAX);
        }
    }

    public void printError(final String message) {
        System.out.println("[ERROR] " + message);
    }
}
