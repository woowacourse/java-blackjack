package domain.player;

import static domain.player.CardFixtures.A_SPADES;
import static domain.player.CardFixtures.SEVEN_CLUBS;
import static domain.player.CardFixtures.TEN_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.MatchResult;
import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GamblerTest {
    @Test
    @DisplayName("겜블러 생성 테스트")
    void gambler_create() {
        // given
        String expected = "pobi";
        Gambler gambler = new Gambler(expected);

        // when
        String actual = gambler.getName();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    @DisplayName("버스트 여부를 확인할 수 있다.")
    void is_bust() {
        // given
        Gambler gambler = new Gambler("dolbum");
        gambler.addCard(PlayingCard.of(Suit.HEARTS, Denomination.KING));
        gambler.addCard(PlayingCard.of(Suit.SPADES, Denomination.KING));
        gambler.addCard(PlayingCard.of(Suit.CLUBS, Denomination.KING));

        // when
        boolean isBust = gambler.isBust();

        // then
        assertThat(isBust).isTrue();
    }

    @ParameterizedTest(name = "15 vs {0}+{1} = {2}")
    @CsvSource(value = {"SIX,TEN,WIN", "NINE,SIX,DRAW", "SIX,TWO,LOSE"})
    @DisplayName("겜블러는 딜러를 받아 자신의 승패를 반환할 수 있다")
    void getGamblerResultByDealer(Denomination denomination,
                                  Denomination denomination2,
                                  MatchResult expected) {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.ACE));
        dealer.addCard(PlayingCard.of(Suit.SPADES, Denomination.FOUR));

        Gambler gambler = new Gambler("pobi");
        gambler.addCard(PlayingCard.of(Suit.HEARTS, denomination));
        gambler.addCard(PlayingCard.of(Suit.HEARTS, denomination2));

        // when
        MatchResult match = gambler.match(dealer);

        // then
        assertThat(match).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 블랙잭이면, 무승부(push)처리 되어야 한다")
    void matchResultShouldBeDrawWhenBothHaveBlackjack() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(A_SPADES);
        dealer.addCard(TEN_HEARTS);

        Gambler gambler = new Gambler("rich");
        gambler.addCard(A_SPADES);
        gambler.addCard(TEN_HEARTS);

        // when
        boolean isDealerBlackjack = dealer.isBlackJack();
        MatchResult resultForDealer = dealer.match(gambler);
        boolean isGamblerBlackjack = gambler.isBlackJack();
        MatchResult resultForGambler = gambler.match(dealer);

        // then
        assertAll(
                () -> assertThat(isDealerBlackjack).isTrue(),
                () -> assertThat(isGamblerBlackjack).isTrue(),
                () -> assertThat(resultForDealer).isEqualTo(MatchResult.DRAW),
                () -> assertThat(resultForGambler).isEqualTo(MatchResult.DRAW)
        );
    }

    @Test
    @DisplayName("동점이더라도 겜블러만 블랙잭이면 겜블러가 승리한다.")
    void blackjackWinEvenIfCompetitorMadeSameScore() {
        // given
        Dealer dealer = new Dealer();
        dealer.addCard(SEVEN_CLUBS);
        dealer.addCard(SEVEN_CLUBS);
        dealer.addCard(SEVEN_CLUBS);

        Gambler gambler = new Gambler("rich");
        gambler.addCard(A_SPADES);
        gambler.addCard(TEN_HEARTS);

        // when
        boolean isDealerBlackjack = dealer.isBlackJack();
        MatchResult resultForDealer = dealer.match(gambler);
        boolean isGamblerBlackjack = gambler.isBlackJack();
        MatchResult resultForGambler = gambler.match(dealer);

        // then
        assertAll(
                () -> assertThat(dealer.getScore()).isEqualTo(gambler.getScore()),
                () -> assertThat(isDealerBlackjack).isFalse(),
                () -> assertThat(isGamblerBlackjack).isTrue(),
                () -> assertThat(resultForDealer).isEqualTo(MatchResult.LOSE),
                () -> assertThat(resultForGambler).isEqualTo(MatchResult.WIN)
        );
    }
}
