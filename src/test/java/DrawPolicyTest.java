import static org.assertj.core.api.Assertions.assertThat;

import domain.DrawPolicy;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class DrawPolicyTest {
    @Test
    void 카드의_합이_21_이하인_경우_카드를_더_받을_수_있다() {
        DrawPolicy drawPolicy = new DrawPolicy();

        assertThat(drawPolicy.isDrawable(21)).isTrue();
        assertThat(drawPolicy.isDrawable(19)).isTrue();
        assertThat(drawPolicy.isDrawable(18)).isTrue();
    }

    @Test
    void 카드의_합이_21_초과인_경우_카드를_더_받을_수_없다() {
        DrawPolicy drawPolicy = new DrawPolicy();

        assertThat(drawPolicy.isDrawable(22)).isFalse();
        assertThat(drawPolicy.isDrawable(23)).isFalse();
        assertThat(drawPolicy.isDrawable(25)).isFalse();
    }
}
