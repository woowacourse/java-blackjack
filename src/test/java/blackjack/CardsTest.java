package blackjack;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CardsTest {

    @DisplayName("카드 한장 뽑기 테스트")
    @Test
    void pickCard() {
        Cards cards = new Cards();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
        assertThat(cards.pickCard(numberGenerator).getName()).isEqualTo("3다이아몬드");
    }

    @DisplayName("카드 중복 테스트")
    @Test
    void duplicate() {
        Cards cards = new Cards();
        NumberGenerator numberGenerator = new IntendedNumberGenerator(List.of(2));
        cards.pickCard(numberGenerator);

        assertThatThrownBy(() -> cards.pickCard(numberGenerator))
                .isInstanceOf(IndexOutOfBoundsException.class);
    }
}
