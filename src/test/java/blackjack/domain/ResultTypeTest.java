package blackjack.domain;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.cards.Card;
import blackjack.domain.cards.CardValue;
import blackjack.domain.cards.Deck;
import blackjack.domain.cards.Shape;
import blackjack.domain.names.Name;
import blackjack.domain.participants.Betting;
import blackjack.domain.participants.Player;
import java.util.Arrays;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ResultTypeTest {

    @Test
    @DisplayName("값 차이에 따른 게임 결과 테스트")
    void getResultType() {
        Deck deck = new Deck(Arrays.asList(
            Card.valueOf(Shape.DIAMOND, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT),
            Card.valueOf(Shape.DIAMOND, CardValue.ACE),
            Card.valueOf(Shape.HEART, CardValue.EIGHT),
            Card.valueOf(Shape.SPADE, CardValue.TEN),
            Card.valueOf(Shape.CLOVER, CardValue.SEVEN),
            Card.valueOf(Shape.CLOVER, CardValue.TEN),
            Card.valueOf(Shape.SPADE, CardValue.EIGHT)));
        CardDistributorForTest cardDistributorForTest = CardDistributorForTest
            .valueOf(new CardDistributor(deck));

        Player eighteen = new Player(new Name("eighteen"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(eighteen, 2);
        Player nineteen = new Player(new Name("nineteen"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(nineteen, 2);
        Player seventeen = new Player(new Name("seventeen"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(seventeen, 2);
        Player anotherEighteen = new Player(new Name("anotherEighteen"), Betting.valueOf("1"));
        cardDistributorForTest.distributeCardsTo(anotherEighteen, 2);

        assertThat(ResultType.getResultTypeByScore(nineteen, eighteen)).isEqualTo(ResultType.WIN);
        assertThat(ResultType.getResultTypeByScore(eighteen, anotherEighteen))
            .isEqualTo(ResultType.TIE);
        assertThat(ResultType.getResultTypeByScore(seventeen, eighteen)).isEqualTo(ResultType.LOSE);
    }

    @Test
    @DisplayName("승패 전환 기능")
    void opposite() {
        assertThat(ResultType.WIN.opposite()).isEqualTo(ResultType.LOSE);
        assertThat(ResultType.LOSE.opposite()).isEqualTo(ResultType.WIN);
        assertThat(ResultType.TIE.opposite()).isEqualTo(ResultType.TIE);
    }
}
