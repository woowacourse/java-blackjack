package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import domain.card.Card;
import domain.card.Emblem;
import domain.card.Grade;
import domain.participant.Dealer;
import domain.participant.Hand;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest {

    @Test
    void 딜러는_처음_받은_카드만_노출한다() {
        // given
        Grade expectedGrade = Grade.TEN;
        Emblem expectedEmblem = Emblem.CLOVER;
        Hand hand = new Hand(
                List.of(new Card(expectedEmblem, expectedGrade),
                        new Card(Emblem.DIAMOND, Grade.TEN)));
        Dealer dealer = new Dealer(hand);
        // when
        List<String> result = dealer.getInitialCards();
        // then
        assertAll(
                () -> assertThat(result).hasSize(1),
                () -> assertThat(result.getFirst()).isEqualTo(expectedGrade.getName() + expectedEmblem.getName())
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
