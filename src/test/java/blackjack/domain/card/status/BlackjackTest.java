package blackjack.domain.card.status;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import blackjack.domain.card.Shape;
import blackjack.domain.card.Value;
import blackjack.domain.fixture.CardsFixture;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

public class BlackjackTest {

    @DisplayName("카드가 블랙잭이 아닌 경우, 예외를 던진다.")
    @ParameterizedTest
    @MethodSource("notBlackjack")
    void validateTest_whenNotBlackjack_throwException(List<Card> cards) {
        Hand hand = new Hand(cards);

        assertThatThrownBy(() -> new Blackjack(hand))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 카드는 블랙잭이 아닙니다.");
    }

    static Stream<List<Card>> notBlackjack() {
        return Stream.of(
                CardsFixture.CARDS_SCORE_17,
                CardsFixture.CARDS_SCORE_21,
                CardsFixture.CARDS_SCORE_22
        );
    }

    @DisplayName("블랙잭에서 카드를 추가할 경우, 버스트 된다.")
    @Test
    void addTest() {
        Hand hand = new Hand(CardsFixture.BLACKJACK);
        HandStatus handStatus = new Blackjack(hand);

        HandStatus actual = handStatus.add(new Card(Value.TWO, Shape.HEART));

        assertThat(actual).isExactlyInstanceOf(Busted.class);
    }
}
