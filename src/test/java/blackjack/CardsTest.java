package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드 한장 뽑기 테스트")
    @Test
    void duplicate() {
        Cards cards = new Cards();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(2);
        assertThat(cards.pickCard(numberGenerator).getName()).isEqualTo("3다이아몬드");
    }
}
