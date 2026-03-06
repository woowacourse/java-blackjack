package domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class NameTest {
    @Test
    void 이름이_공백인_경우_예외가_발생한다(){
        Assertions.assertThatThrownBy(() -> new Name(" ")).isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void 이름이_10자를_초과하는_경우_예외가_발생한다(){
        Assertions.assertThatThrownBy(() -> new Name("0123456789a")).isInstanceOf(IllegalArgumentException.class);
    }
}
