package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    void Null로_생성할_경우_예외가_발생한다() {
        String emptyString = null;
        Assertions.assertThatThrownBy(() -> Name.valueOf(emptyString)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 공백으로_생성할_경우_예외가_발생한다() {
        String emptyString = " ";
        Assertions.assertThatThrownBy(() -> Name.valueOf(emptyString)).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 길이가_범위를_초과하는_경우_예외가_발생한다() {
        String length10String = "01234567890";
        Assertions.assertThatThrownBy(() -> Name.valueOf(length10String)).isInstanceOf(IllegalArgumentException.class);
    }
}
