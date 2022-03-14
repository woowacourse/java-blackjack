package domain.player;

import static org.assertj.core.api.Assertions.assertThat;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Denomination;
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
        gambler.addCard(Card.of(Suit.HEARTS, Denomination.KING));
        gambler.addCard(Card.of(Suit.SPADES, Denomination.KING));
        gambler.addCard(Card.of(Suit.CLUBS, Denomination.KING));

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
        dealer.addCard(Card.of(Suit.SPADES, Denomination.ACE));
        dealer.addCard(Card.of(Suit.SPADES, Denomination.FOUR));

        Gambler gambler = new Gambler("pobi");
        gambler.addCard(Card.of(Suit.HEARTS, denomination));
        gambler.addCard(Card.of(Suit.HEARTS, denomination2));

        // when
        MatchResult match = gambler.match(dealer);

        // then
        assertThat(match).isEqualTo(expected);
    }
}
