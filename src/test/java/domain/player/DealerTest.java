package domain.player;

import static domain.MatchResult.LOSE;
import static domain.MatchResult.WIN;
import static org.assertj.core.api.Assertions.assertThat;

import domain.MatchResult;
import domain.card.Card;
import domain.card.Denomination;
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
        rich.addCard(Card.of(Suit.HEARTS, Denomination.FIVE));
        rich.addCard(Card.of(Suit.SPADES, Denomination.FIVE));
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
        dealer.addCard(Card.of(Suit.HEARTS, Denomination.FIVE));
        dealer.addCard(Card.of(Suit.SPADES, Denomination.FIVE));

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
        dealer.addCard(Card.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(Card.of(Suit.SPADES, Denomination.KING));

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
        dealer.addCard(Card.of(Suit.HEARTS, Denomination.KING));
        dealer.addCard(Card.of(Suit.SPADES, Denomination.KING));
        dealer.addCard(Card.of(Suit.CLUBS, Denomination.KING));

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
        dealer.addCard(Card.of(Suit.SPADES, Denomination.ACE));
        dealer.addCard(Card.of(Suit.SPADES, Denomination.FOUR));
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
        pobi.addCard(Card.of(Suit.HEARTS, Denomination.SIX));
        pobi.addCard(Card.of(Suit.HEARTS, Denomination.TEN));

        Gambler rich = new Gambler("rich");
        rich.addCard(Card.of(Suit.CLUBS, Denomination.ACE));
        rich.addCard(Card.of(Suit.CLUBS, Denomination.TEN));

        Gambler dolbum = new Gambler("dolbum");
        dolbum.addCard(Card.of(Suit.CLUBS, Denomination.ACE));
        dolbum.addCard(Card.of(Suit.CLUBS, Denomination.TWO));

        return new Gamblers(List.of(pobi, rich, dolbum));
    }
}
