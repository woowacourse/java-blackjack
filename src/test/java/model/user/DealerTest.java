package model.user;

import static model.card.CardFixture.DIAMOND_KING;
import static model.card.CardFixture.DIAMOND_SEVEN;
import static model.card.CardFixture.DIAMOND_SIX;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import model.card.Card;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class DealerTest {

    private Dealer dealer;

    @BeforeEach
    void init() {
        dealer = new Dealer();
    }

    @ParameterizedTest
    @MethodSource("receiveCardTestValues")
    @DisplayName("딜러의 카드 총 합이 16 이하인지 판단한다.")
    void canReceiveCardTest(final Card firstCard, final Card secondCard, final boolean result) {
        // given
        dealer.receiveCard(firstCard);
        dealer.receiveCard(secondCard);

        // when, then
        assertThat(dealer.canReceiveCard()).isEqualTo(result);
    }

    private static Stream<Arguments> receiveCardTestValues() {
        return Stream.of(
                Arguments.of(DIAMOND_SIX, DIAMOND_KING, true),
                Arguments.of(DIAMOND_SEVEN, DIAMOND_KING, false)
        );
    }

    @DisplayName("receiveInitialCards가 두 장의 카드를 주는지 확인한다.")
    @Test
    void receiveInitialCards() {
        // given
        dealer.receiveInitialCards(Deck.create(new RandomShuffleMaker()));

        // when, then
        assertThat(dealer.getHand().getCards()).hasSize(2);
    }
}
