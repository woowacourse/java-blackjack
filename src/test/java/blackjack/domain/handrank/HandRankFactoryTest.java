package blackjack.domain.handrank;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.fixture.CardsFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class HandRankFactoryTest {

    @DisplayName("손패가 블랙잭인 경우, 블랙잭 객체를 반환한다.")
    @Test
    void fromTest_whenBlackjack() {
        Hand blackjack = new Hand(CardsFixture.BLACKJACK);

        HandRank actual = HandRankFactory.from(blackjack);

        assertThat(actual).isExactlyInstanceOf(Blackjack.class);
    }

    @DisplayName("손패가 버스트가 된 경우, 버스트 객체를 반환한다.")
    @ParameterizedTest
    @MethodSource("bustCards")
    void fromTest_whenBusted(List<Card> bust) {
        Hand blackjack = new Hand(bust);

        HandRank actual = HandRankFactory.from(blackjack);

        assertThat(actual).isExactlyInstanceOf(Bust.class);
    }

    static Stream<List<Card>> bustCards() {
        return Stream.of(CardsFixture.BUSTED, CardsFixture.CARDS_SCORE_22);
    }

    @DisplayName("손패가 버스트, 블랙잭이 아닌 경우, 일반 랭크 객체를 반환한다.")
    @ParameterizedTest
    @MethodSource("normalCards")
    void fromTest_whenNotBlackjackAndNotBust(List<Card> cards) {
        Hand blackjack = new Hand(cards);

        HandRank actual = HandRankFactory.from(blackjack);

        assertThat(actual).isExactlyInstanceOf(Stand.class);
    }

    static Stream<List<Card>> normalCards() {
        return Stream.of(CardsFixture.CARDS_SCORE_4, CardsFixture.CARDS_SCORE_17, CardsFixture.CARDS_SCORE_16);
    }
}
