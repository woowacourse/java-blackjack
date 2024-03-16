package blackjack.view;

import blackjack.domain.BlackjackGame;
import blackjack.domain.bet.BetRevenue;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.user.Dealer;
import blackjack.domain.user.UserName;
import blackjack.dto.BetRevenueResultDto;
import blackjack.dto.FinalHandsScoreDto;
import blackjack.dto.PlayerBetResultDto;
import blackjack.dto.PlayerHandsScoreDto;
import blackjack.view.format.CardNumberFormat;
import blackjack.view.format.CardShapeFormat;
import java.util.List;
import java.util.Map;

public class OutputView {

    private static final String DELIMITER = ", ";

    public static void printStartCards(Map<UserName, Hands> playersHands, Hands dealerHands) {
        System.out.printf("%n%s와 %s에게 %d장을 나누었습니다.%n", Dealer.DEALER_NAME,
                String.join(DELIMITER, playersHands.keySet().stream().map(UserName::getName).toList()),
                BlackjackGame.START_CARD_COUNT);

        System.out.printf("%s: %s%n", Dealer.DEALER_NAME, convertToCardsFormat(dealerHands));

        playersHands.forEach(OutputView::printPlayerCard);
        System.out.printf("%n");
    }

    private static String convertToCardsFormat(Hands hands) {
        return String.join(DELIMITER, hands.getCards().stream().map(OutputView::convertToCardFormat).toList());
    }

    private static String convertToCardFormat(Card card) {
        return CardNumberFormat.from(card.number()).getFormat() + CardShapeFormat.from(card.shape()).getFormat();
    }

    public static void printPlayerCard(UserName name, Hands hands) {
        System.out.printf("%s카드: %s%n", name.getName(), convertToCardsFormat(hands));
    }

    public static void printFinalResult(FinalHandsScoreDto HandsScore, BetRevenueResultDto betRevenueResult) {
        printCardScore(HandsScore);
        printBetResult(betRevenueResult);
    }

    private static void printCardScore(FinalHandsScoreDto HandsScore) {
        printDealerCardScore(HandsScore.dealerHands());
        printPlayersCardScore(HandsScore.playersHandsScore());
    }

    private static void printDealerCardScore(Hands dealerHands) {
        System.out.printf("%n%s 카드: %s - 결과 : %d%n", Dealer.DEALER_NAME,
                convertToCardsFormat(dealerHands), dealerHands.calculateScore().toInt());
    }

    private static void printPlayersCardScore(List<PlayerHandsScoreDto> playersHandsScore) {
        playersHandsScore.forEach(OutputView::printPlayerCardScore);
    }

    private static void printPlayerCardScore(PlayerHandsScoreDto playerHandsScore) {
        System.out.printf("%s 카드: %s - 결과 : %d%n", playerHandsScore.getName(),
                convertToCardsFormat(playerHandsScore.hands()), playerHandsScore.handsScore());
    }

    private static void printBetResult(BetRevenueResultDto betResult) {
        System.out.printf("%n## 최종 수익%n");
        printDealerBetResult(betResult.dealerRevenue());
        printPlayersBetResult(betResult.playersBetResult());
    }

    private static void printDealerBetResult(BetRevenue dealerBetRevenue) {
        System.out.printf("%s: %.1f%n", Dealer.DEALER_NAME, dealerBetRevenue.value());
    }

    private static void printPlayersBetResult(List<PlayerBetResultDto> playersWinningResult) {
        playersWinningResult.forEach(
                playerWinningResult -> System.out.printf("%s: %.1f%n", playerWinningResult.getName(),
                        playerWinningResult.revenue().value()));
    }

    public static void printDealerMoreCard(int count) {
        while (count-- > 0) {
            System.out.printf("%s는 %d이하라 한장의 카드를 더 받았습니다.%n", Dealer.DEALER_NAME, Dealer.NEED_CARD_SCORE_MAX.toInt());
        }
    }
}
