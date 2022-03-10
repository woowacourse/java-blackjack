package blackjack;

import static blackjack.Rank.*;
import static blackjack.Suit.*;
import static org.assertj.core.api.Assertions.assertThat;

import blackjack.cards.Cards;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class CardsTest {

    @DisplayName("카드 합 계산")
    @Test
    void sumCard() {
        Cards cards = Cards.mixHandCards(new Card(THREE, DIAMOND), new Card(TWO, DIAMOND));
        assertThat(cards.score()).isEqualTo(new Score(5));
    }

    @Test
    void J_Q_K_카드_점수_계산() {
        Cards cards = Cards.mixHandCards(new Card(QUEEN, CLOVER), new Card(JACK, HEART), new Card(KING, DIAMOND));
        assertThat(cards.score()).isEqualTo(new Score(30));
    }

    @ParameterizedTest
    @MethodSource("provideAceData")
    void ACE_카드_점수_계산(Cards cards) {
        assertThat(cards.score()).isEqualTo(new Score(21));
    }

    protected static Stream<Arguments> provideAceData() {
        return Stream.of(
                Arguments.of(Cards.mixHandCards(new Card(ACE, SPADE), new Card(JACK, HEART))),
                Arguments.of(Cards.mixHandCards(new Card(ACE, DIAMOND), new Card(JACK, DIAMOND),
                    new Card(KING, CLOVER))),
                Arguments.of(Cards.mixHandCards(new Card(ACE, DIAMOND), new Card(ACE, SPADE),
                    new Card(NINE, CLOVER)))
        );
    }
}
