package domain.player;

import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static domain.player.CardFixtures.A_SPADES;
import static domain.player.CardFixtures.SEVEN_CLUBS;
import static domain.player.CardFixtures.TEN_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.MatchResult;
import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;
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
        Dealer rich = new Dealer("rich");
        rich.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        rich.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));
        int expected = 10;

        // when
        int actual = rich.getScore();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러는 현재 합산 16이하인지 확인할 수 있다")
    void checkIfSameOrLessThanSixteen() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.FIVE));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.FIVE));

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isTrue();
    }

    @Test
    @DisplayName("딜러는 현재 합산 17이상인지 확인할 수 있다")
    void checkIfSameOrGreaterThanSeventeen() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));

        // when
        boolean actual = dealer.isHittable();

        // then
        assertThat(actual).isFalse();
    }

    @Test
    @DisplayName("버스트 여부를 확인할 수 있다.")
    void isBust() {
        // given
        Dealer dealer = new Dealer("rich");
        dealer.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));
        dealer.addCard(PlayingCard.of(Suit.CLUBS, Denomination.KING));

        // when
        boolean isBust = dealer.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @Test
    @DisplayName("딜러는 겜블러 목록을 받아 자신의 승패를 확인할 수 있다")
    void getMatchResults() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.ACE));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.FOUR));
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
        Gambler pobi = new Gambler("pobi");
        pobi.addCard(PlayingCard.of(Suit.HEARTS, Denomination.SIX));
        pobi.addCard(PlayingCard.of(Suit.HEARTS, Denomination.TEN));

        Gambler rich = new Gambler("rich");
        rich.addCard(PlayingCard.of(Suit.CLUBS, Denomination.ACE));
        rich.addCard(PlayingCard.of(Suit.CLUBS, Denomination.TEN));

        Gambler dolbum = new Gambler("dolbum");
        dolbum.addCard(PlayingCard.of(Suit.CLUBS, Denomination.ACE));
        dolbum.addCard(PlayingCard.of(Suit.CLUBS, Denomination.TWO));

        return new Gamblers(List.of(pobi, rich, dolbum));
    }

    @Test
    @DisplayName("동점이더라도 딜러만 블랙잭이면 딜러가 승리한다.")
    void blackjackWinEvenIfCompetitorMadeSameScore() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(A_SPADES);
        dealer.addCard(TEN_HEARTS);

        Gambler gambler = new Gambler("rich");
        gambler.addCard(SEVEN_CLUBS);
        gambler.addCard(SEVEN_CLUBS);
        gambler.addCard(SEVEN_CLUBS);

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
        Gambler gambler = new Gambler("rich");
        gambler.addCard(TEN_HEARTS);
        gambler.addCard(TEN_HEARTS);
        gambler.addCard(SEVEN_CLUBS);

        Dealer dealer = new Dealer();
        dealer.addCard(SEVEN_CLUBS);
        dealer.addCard(TEN_HEARTS);

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
