package domain.participant;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

class NamesTest {
    @DisplayName("플레이어의 이름이 겹치면 예외가 발생한다")
    @Test
    void duplicateName() {
        final List<String> names = new ArrayList<>();

        names.add("teba");
        names.add("teba");

        assertThatCode(() -> new Names(names)).isInstanceOf(IllegalArgumentException.class);
    }
}
