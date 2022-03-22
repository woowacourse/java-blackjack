package domain.player;

import static domain.CardFixtures.ACE_SPADES;
import static domain.CardFixtures.FOUR_SPADES;
import static domain.CardFixtures.KING_HEARTS;
import static domain.CardFixtures.SEVEN_CLUBS;
import static domain.CardFixtures.TEN_HEARTS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.MatchResult;
import domain.card.Denomination;
import domain.card.PlayingCard;
import domain.card.Suit;
import java.util.List;
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
        Gambler gambler = new Gambler("dolbum", List.of(KING_HEARTS, KING_HEARTS, KING_HEARTS));

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
        Dealer dealer = new Dealer(List.of(ACE_SPADES, FOUR_SPADES));
        Gambler gambler = new Gambler("pobi", List.of(
                PlayingCard.of(Suit.HEARTS, denomination), PlayingCard.of(Suit.HEARTS, denomination2)));

        // when
        MatchResult match = gambler.match(dealer);

        // then
        assertThat(match).isEqualTo(expected);
    }

    @Test
    @DisplayName("딜러와 겜블러 모두 블랙잭이면, 무승부(push)처리 되어야 한다")
    void matchResultShouldBeDrawWhenBothHaveBlackjack() {
        // given
        Dealer dealer = new Dealer(List.of(ACE_SPADES, TEN_HEARTS));
        Gambler gambler = new Gambler("rich", List.of(ACE_SPADES, TEN_HEARTS));

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
        Dealer dealer = new Dealer(List.of(SEVEN_CLUBS, SEVEN_CLUBS, SEVEN_CLUBS));
        Gambler gambler = new Gambler("rich", List.of(ACE_SPADES, TEN_HEARTS));

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
