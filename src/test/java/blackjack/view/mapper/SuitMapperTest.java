package blackjack.view.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import blackjack.domain.card.Suit;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SuitMapperTest {

    @DisplayName("SuitTranslator는 Suit의 멤버를 모두 알고 있다.")
    @Test
    void knowAllValuesOfSuit() {
        final List<Suit> expected = List.of(Suit.values());
        final List<Suit> actual = Arrays.stream(SuitMapper.values())
                .map(SuitMapper::getSuit)
                .toList();

        assertThat(actual).containsExactlyInAnyOrderElementsOf(expected);
    }

    @DisplayName("주어진 Suit에 매칭되는 viewName을 찾는다.")
    @Test
    void findViewName() {
        final String expected = "하트";

        final String actual = SuitMapper.mapToViewName(Suit.HEART);

        assertThat(actual).isEqualTo(expected);
    }
}
