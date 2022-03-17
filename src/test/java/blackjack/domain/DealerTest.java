package blackjack.domain;

import blackjack.domain.card.Card;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Suit;
import blackjack.domain.player.Dealer;
import java.util.List;
import java.util.stream.Stream;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class DealerTest {

    @ParameterizedTest(name = "[{index}] cards {0}, canAddCard {1}")
    @MethodSource("parameters1")
    @DisplayName("16점 이하일 경우 카드를 추가할 수 있다.")
    void canAddCard(List<Card> cards) {
        Dealer dealer = new Dealer();
        cards.forEach(dealer::addCard);

        Assertions.assertThat(dealer.canAddCard()).isTrue();
    }

    static Stream<Arguments> parameters1() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Denomination.THREE, Suit.CLOVER), new Card(Denomination.EIGHT, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.ACE, Suit.CLOVER), new Card(Denomination.FIVE, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.KING, Suit.CLOVER), new Card(Denomination.TWO, Suit.HEART)))
        );
    }

    @ParameterizedTest(name = "[{index}] cards {0}, canAddCard {1}")
    @MethodSource("parameters2")
    @DisplayName("16점을 초과할 경우 카드를 추가하지 못한다.")
    void cantAddCard(List<Card> cards) {
        Dealer dealer = new Dealer();
        cards.forEach(dealer::addCard);

        Assertions.assertThat(dealer.canAddCard()).isFalse();
    }

    static Stream<Arguments> parameters2() {
        return Stream.of(
                Arguments.of(
                        List.of(new Card(Denomination.EIGHT, Suit.CLOVER), new Card(Denomination.JACK, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.EIGHT, Suit.CLOVER), new Card(Denomination.NINE, Suit.HEART))),
                Arguments.of(
                        List.of(new Card(Denomination.KING, Suit.CLOVER), new Card(Denomination.TEN, Suit.HEART)))
        );
    }
}
