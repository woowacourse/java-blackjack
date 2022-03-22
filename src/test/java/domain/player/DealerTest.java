package domain.player;

import static domain.CardFixtures.ACE_SPADES;
import static domain.CardFixtures.FIVE_SPADES;
import static domain.CardFixtures.FOUR_SPADES;
import static domain.CardFixtures.KING_HEARTS;
import static domain.CardFixtures.SEVEN_CLUBS;
import static domain.CardFixtures.SIX_HEARTS;
import static domain.CardFixtures.TEN_HEARTS;
import static domain.CardFixtures.TWO_SPADES;
import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.MatchResult;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DealerTest {
    @Test
    @DisplayName("딜러 생성 테스트")
    void dealer_create() {
        // given
        String expected = "dolbum";
        Dealer dealer = new Dealer(expected);

        // when
        String actual = dealer.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("보유한 카드 기준 합산 점수 반환 테스트")
    void supply_card() {
        // given
        Dealer dealer = new Dealer(List.of(FIVE_SPADES, FIVE_SPADES));
        int expected = 10;

        // when
        int actual = dealer.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 현재 합산 16이하인지 확인할 수 있다")
    void checkIfSameOrLessThanSixteen() {
        // given
        Dealer dealer = new Dealer(List.of(FIVE_SPADES, FIVE_SPADES));

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("딜러는 현재 합산 17이상인지 확인할 수 있다")
    void checkIfSameOrGreaterThanSeventeen() {
        // given
        Dealer dealer = new Dealer(List.of(KING_HEARTS, KING_HEARTS));

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트 여부를 확인할 수 있다.")
    void isBust() {
        // given
        Dealer dealer = new Dealer(List.of(KING_HEARTS, KING_HEARTS, KING_HEARTS));

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("딜러는 겜블러 목록을 받아 자신의 승패를 확인할 수 있다")
    void getMatchResults() {
        // given
        Dealer dealer = new Dealer(List.of(ACE_SPADES, FOUR_SPADES));
        Gamblers gamblers = setupGamblers();

        // when
        List<MatchResult> dealerResult = gamblers.getGamblers()
                .stream()
                .map(dealer::match)
                .collect(Collectors.toList());

        // then
        assertThat(dealerResult).isEqualTo(List.of(LOSE, LOSE, WIN));
    }

    private Gamblers setupGamblers() {
        Gambler pobi = new Gambler("pobi", List.of(SIX_HEARTS, TEN_HEARTS));
        Gambler rich = new Gambler("rich", List.of(ACE_SPADES, TEN_HEARTS));
        Gambler dolbum = new Gambler("dolbum", List.of(ACE_SPADES, TWO_SPADES));

        return new Gamblers(List.of(pobi, rich, dolbum));
    }

    @Test
    @DisplayName("동점이더라도 딜러만 블랙잭이면 딜러가 승리한다.")
    void blackjackWinEvenIfCompetitorMadeSameScore() {
        // given
        Dealer dealer = new Dealer(List.of(ACE_SPADES, TEN_HEARTS));
        Gambler gambler = new Gambler("rich", List.of(SEVEN_CLUBS, SEVEN_CLUBS, SEVEN_CLUBS));

        // when
        boolean isDealerBlackjack = dealer.isBlackJack();
        MatchResult resultForDealer = dealer.match(gambler);
        boolean isGamblerBlackjack = gambler.isBlackJack();
        MatchResult resultForGambler = gambler.match(dealer);

        // then
        assertAll(
                () -> assertThat(dealer.getScore()).isEqualTo(gambler.getScore()),
                () -> assertThat(isDealerBlackjack).isTrue(),
                () -> assertThat(isGamblerBlackjack).isFalse(),
                () -> assertThat(resultForDealer).isEqualTo(MatchResult.WIN),
                () -> assertThat(resultForGambler).isEqualTo(MatchResult.LOSE)
        );
    }

    @Test
    @DisplayName("겜블러가 버스트이면, 딜러는 버스트여도 딜러가 승리한다.")
    void dealerWinsInAnyConditionWhenGamblerIsBust() {
        // given
        Gambler gambler = new Gambler("rich", List.of(TEN_HEARTS, TEN_HEARTS, SEVEN_CLUBS));
        Dealer dealer = new Dealer(List.of(SEVEN_CLUBS, TEN_HEARTS));

        // when
        boolean isGamblerBust = gambler.isBust();
        MatchResult gamblerResultWhenDealerIsNotBust = gambler.match(dealer);

        boolean dealerBustStatusBeforeBust = dealer.isBust();
        MatchResult dealerResultWhenDealerIsNotBust = dealer.match(gambler);

        dealer.addCard(TEN_HEARTS);
        boolean dealerBustStatusAfterBust = dealer.isBust();
        MatchResult gamblerResultWhenDealerIsBust = gambler.match(dealer);
        MatchResult dealerResultWhenDealerIsBust = dealer.match(gambler);

        // then
        assertAll(
                () -> assertThat(isGamblerBust).isTrue(),
                () -> assertThat(gamblerResultWhenDealerIsNotBust).isEqualTo(LOSE),
                () -> assertThat(dealerBustStatusBeforeBust).isFalse(),
                () -> assertThat(dealerResultWhenDealerIsNotBust).isEqualTo(WIN),

                () -> assertThat(dealerBustStatusAfterBust).isTrue(),
                () -> assertThat(gamblerResultWhenDealerIsBust).isEqualTo(LOSE),
                () -> assertThat(dealerResultWhenDealerIsBust).isEqualTo(MatchResult.WIN)
        );
    }
}
