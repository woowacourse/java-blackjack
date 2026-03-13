package blackjack.domain.participant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import blackjack.domain.card.Card;
import blackjack.domain.card.Emblem;
import blackjack.domain.card.Grade;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @Test
    void 딜러는_처음_받은_카드만_노출한다() {
        // given
        Card expectedCard = new Card(Emblem.CLOVER, Grade.TEN);
        Hand hand = new Hand(
                List.of(expectedCard,
                        new Card(Emblem.DIAMOND, Grade.TEN)));
        Dealer dealer = new Dealer(hand);
        // when
        List<Card> result = dealer.getInitialCards();
        // then
        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.getFirst()).isEqualTo(expectedCard)
        );
    }

    @ParameterizedTest
    @CsvSource(value = {"SEVEN, true", "SIX, false"})
    void 카드의_합이_스탠드_상태인지_확인한다(Grade grade, boolean expectedResult) {
        // given
        Hand hand = createHand(grade);
        Dealer dealer = new Dealer(hand);
        // when
        boolean result = dealer.isStand();
        // then
        assertThat(result).isEqualTo(expectedResult);
    }

    private Hand createHand(Grade grade) {
        return new Hand(
                List.of(new Card(Emblem.CLOVER, grade),
                        new Card(Emblem.CLOVER, Grade.TEN)));
    }
}
