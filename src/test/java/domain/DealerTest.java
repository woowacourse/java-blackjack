package domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DealerTest extends AbstractTestFixture {

    @ParameterizedTest(name = "Hit 가 가능한지 확인한다")
    @CsvSource({"8,9,false", "10,6,true"})
    void test_over_16(String letter1, String letter2, boolean canHit) {
        var dealer = new Dealer(createCards(letter1, letter2));

        assertThat(dealer.canHit()).isEqualTo(canHit);
    }
}