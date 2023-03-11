package domain.participant;

import domain.card.Card;
import domain.card.Rank;
import domain.card.Suit;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class DealerTest {
    Dealer dealer = new Dealer();

    @ParameterizedTest
    @MethodSource("cardProvider")
    @DisplayName("딜러는 패 합이 16이하면 카드를 한 장 받아야한다.")
    void shouldHit(Card card, boolean shouldHit) {
        dealer.receiveCard(new Card(Suit.HEART, Rank.SIX));
        dealer.receiveCard(new Card(Suit.CLOVER, Rank.TWO));
        dealer.receiveCard(new Card(Suit.DIAMOND, Rank.SIX));

        dealer.receiveCard(card);

        Assertions.assertThat(dealer.shouldHit()).isEqualTo(shouldHit);
    }

    private static Stream<Arguments> cardProvider() {
        return Stream.of(
                Arguments.of(new Card(Suit.CLOVER, Rank.ACE), true),
                Arguments.of(new Card(Suit.HEART, Rank.NINE), false)
        );
    }
}
