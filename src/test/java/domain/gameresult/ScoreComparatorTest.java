package domain.gameresult;

import domain.card.Card;
import domain.card.Denomination;
import domain.card.Suit;
import domain.player.Bet;
import domain.player.Player;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static domain.Textures.*;
import static org.assertj.core.api.Assertions.assertThat;

class ScoreComparatorTest {
    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 높으면 참가자는 베팅액을 잃는다.")
    void givenDealerWinningFromPlayer() {
        Player dealer = makeDealerWithCards(List.of(DIAMOND_KING));
        Player gambler = makePlayerWithCards(List.of(DIAMOND_NINE));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어보다 점수가 낮으면 참가자는 베팅액을 가져간다.")
    void givenDealerLosingFromPlayer() {
        Player dealer = makeDealerWithCards(List.of(DIAMOND_NINE));
        Player gambler = makePlayerWithCards(List.of(DIAMOND_ACE));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("딜러가 상대 플레이어와 점수가 같으면 참가자는 베팅액을 그대로 돌려받는다.")
    void givenDealerDrawingWithPlayer() {
        Player dealer = makeDealerWithCards(List.of(DIAMOND_NINE));
        Player gambler = makePlayerWithCards(List.of(HEART_NINE));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(0);
    }

    @Test
    @DisplayName("참가자만 버스트가 일어나면 참가자는 배팅액을 모두 잃는다.")
    void givenParticipantBust() {
        Player dealer = makeDealerWithCards(List.of(new Card(Suit.DIAMOND, Denomination.NINE)));
        Player gambler = makePlayerWithCards(List.of(
                new Card(Suit.HEART, Denomination.NINE),
                new Card(Suit.HEART, Denomination.SEVEN),
                new Card(Suit.DIAMOND, Denomination.SEVEN)
        ));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(-3000);
    }

    @Test
    @DisplayName("딜러만 버스트가 일어나면 참가자는 승리해 베팅액을 가져간다.")
    void givenDealerBust() {
        Player dealer = makePlayerWithCards(List.of(HEART_NINE, HEART_SEVEN, DIAMOND_SEVEN));
        Player gambler = makePlayerWithCards(List.of(DIAMOND_NINE));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("딜러와 참가자 둘 다 버스트가 일어나도 참가자는 승리해 배팅액을 가져간다.")
    void givenDealerParticipantBothBust() {
        Player dealer = makePlayerWithCards(
                List.of(HEART_NINE, HEART_SEVEN, DIAMOND_SEVEN)
        );
        Player gambler = makePlayerWithCards(
                List.of(HEART_NINE, HEART_SEVEN, DIAMOND_SEVEN)
        );

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(3000);
    }

    @Test
    @DisplayName("참가자가 블랙잭이 일어나면 배팅한 금액의 1.5배를 가져간다.")
    void givenParticipantBlackjack() {
        Player dealer = makeDealerWithCards(
                List.of(HEART_SEVEN, DIAMOND_SEVEN)
        );
        Player gambler = makePlayerWithCards(List.of(HEART_ACE, DIAMOND_KING));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(4500);
    }

    @Test
    @DisplayName("2장을 초과한 카드로 21점이 되었다면 블랙잭으로 인정하지 않는다.")
    void givenParticipantNotBlackjack() {
        Player dealer = makeDealerWithCards(
                List.of(HEART_SEVEN, DIAMOND_SEVEN)
        );
        Player gambler = makePlayerWithCards(
                List.of(HEART_EIGHT, HEART_NINE, SPADE_FOUR)
        );

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue())
                .as("21점이긴 하나, 블랙잭이 아니므로 1.5배를 곱하지 않는다.")
                .isEqualTo(3000);
    }

    @Test
    @DisplayName("참가자와 딜러 둘 다 블랙잭이 일어나면 배팅한 금액을 그대로 돌려받는다.")
    void givenParticipantDealerBlackjack() {
        Player dealer = makeDealerWithCards(List.of(SPADE_ACE, SPADE_KING));
        Player gambler = makeDealerWithCards(List.of(HEART_ACE, DIAMOND_KING));

        Bet bet = ScoreComparator.compare(dealer, gambler);

        assertThat(bet.getValue()).isEqualTo(0);
    }
}