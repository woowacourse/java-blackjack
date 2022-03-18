package domain;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static domain.MockCard.*;
import static org.assertj.core.api.Assertions.assertThat;

public class GameResultTest {
    @ParameterizedTest
    @DisplayName("플레이어 수익 반환 테스트")
    @MethodSource("provideForPlayer")
    void getPlayerProfit(final double expectedRate, final List<Card> playerCards, final List<Card> dealerCards) {
        final Player player = new Player("칙촉");
        player.setBettingMoney(new BettingMoney(1000));
        for (final Card playerCard : playerCards) {
            player.drawCard(playerCard);
        }

        final Dealer dealer = new Dealer();
        for (final Card dealerCard : dealerCards) {
            dealer.drawCard(dealerCard);
        }

        final int expected = (int) (expectedRate * player.getBettingMoney());
        assertThat(GameResult.calculatePlayerProfit(player, dealer)).isEqualTo(expected);
    }

    public static Stream<Arguments> provideForPlayer() {
        return Stream.of(
                Arguments.of(1.5,
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD),
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, HEART_TEN_CARD)),
                Arguments.of(1,
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD),
                        List.of(HEART_FOUR_CARD, SPADE_NINE_CARD, HEART_TEN_CARD)),
                Arguments.of(1,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(SPADE_NINE_CARD, SPADE_NINE_CARD)),
                Arguments.of(-1,
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, SPADE_NINE_CARD),
                        List.of(HEART_TEN_CARD, HEART_TEN_CARD, SPADE_NINE_CARD)),
                Arguments.of(-1,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD)),
                Arguments.of(-1,
                        List.of(HEART_TEN_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD)),
                Arguments.of(0,
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD),
                        List.of(CLUB_ACE_CARD, HEART_TEN_CARD)),
                Arguments.of(0,
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD),
                        List.of(CLUB_ACE_CARD, SPADE_NINE_CARD)));
    }

    @ParameterizedTest
    @DisplayName("딜러 수익 반환 테스트")
    @MethodSource("provideForPlayer")
    void getDealerProfit(final double playerProfitRate, final List<Card> playerCards, final List<Card> dealerCards) {
        final Player player = new Player("칙촉");
        player.setBettingMoney(new BettingMoney(1000));
        for (final Card playerCard : playerCards) {
            player.drawCard(playerCard);
        }

        final Dealer dealer = new Dealer();
        for (final Card dealerCard : dealerCards) {
            dealer.drawCard(dealerCard);
        }

        final int expected = -1 * (int) (playerProfitRate * player.getBettingMoney());
        assertThat(GameResult.calculateDealerProfit(dealer, player)).isEqualTo(expected);
    }
}
