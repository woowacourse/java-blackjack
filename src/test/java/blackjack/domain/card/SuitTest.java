package blackjack.domain.card;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SuitTest {

    @Test
    @DisplayName("enum suit size가 4개인지 확인")
    public void checkEnumSuitSize() {
        List<Suit> suitValues = Arrays.stream(Suit.values())
                .collect(Collectors.toList());

        assertThat(suitValues.size()).isEqualTo(4);
    }
}
