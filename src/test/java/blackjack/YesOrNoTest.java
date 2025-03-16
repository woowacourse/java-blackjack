package blackjack;

import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class YesOrNoTest {

    @Test
    void y를_입력하면_YES가_반환된다() {
        final String input = "y";

        Assertions.assertThat(YesOrNo.from(input)).isEqualTo(YesOrNo.YES);
    }

    @Test
    void n을_입력하면_NO가_반환된다() {
        final String input = "n";

        Assertions.assertThat(YesOrNo.from(input)).isEqualTo(YesOrNo.NO);
    }

    @Test
    void y나n이_아닌_값을_입력하면_예외가_발생한다() {
        final String input = "jj";

        Assertions.assertThatThrownBy(() -> YesOrNo.from(input))
                .isInstanceOf(IllegalArgumentException.class);
    }
}