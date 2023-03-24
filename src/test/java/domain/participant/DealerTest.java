package domain.participant;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import domain.AbstractTestFixture;

class DealerTest extends AbstractTestFixture {

    @ParameterizedTest(name = "점수가 17미만이면 더 뽑을 수 있고, 17이상이면 불가하다")
    @CsvSource({"8,9,false", "10,6,true"})
    void test_can_hit(String letter1, String letter2, boolean canHit) {
        var dealer = new Dealer(createCards(letter1, letter2));

        assertThat(dealer.canHit()).isEqualTo(canHit);
    }
}