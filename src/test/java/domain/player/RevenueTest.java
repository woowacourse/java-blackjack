package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@DisplayName("Revenue 은")
public class RevenueTest {

    @Test
    void Revenue_의_총_합을_구할_수_있다() {
        // given
        final Revenue revenue1 = Revenue.of(1000);
        final Revenue revenue2 = Revenue.of(3000);
        final Revenue revenue3 = Revenue.of(5000);

        // when
        final Revenue result = Revenue.total(List.of(revenue1, revenue2, revenue3));

        // then
        assertThat(result.amount()).isEqualTo(9000);
    }

    @Test
    void Revenue_의_부호를_반전시킬_수_있다() {
        // given
        final Revenue revenue = Revenue.of(1000);

        // when
        final Revenue reverse = revenue.reverse();

        // then
        assertThat(reverse.amount()).isEqualTo(-1000);
    }
}
