package model.user;

import model.card.Card;
import model.card.Deck;
import model.card.RandomShuffleMaker;
import model.user.Dealer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static model.card.Shape.DIAMOND;
import static model.card.Value.KING;
import static model.card.Value.SEVEN;
import static model.card.Value.SIX;
import static org.assertj.core.api.Assertions.assertThat;

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
                Arguments.of(new Card(DIAMOND, SIX), new Card(DIAMOND, KING), true),
                Arguments.of(new Card(DIAMOND, SEVEN), new Card(DIAMOND, KING), false)
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
