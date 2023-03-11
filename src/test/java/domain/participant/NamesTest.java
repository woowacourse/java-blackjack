package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class NamesTest {
    @Test
    @DisplayName("참가자 이름에 중복이 있을 경우 예외가 발생한다.")
    void duplicateNames() {
        Assertions.assertThatThrownBy(() ->
                        new Names(List.of(new Name("leo"), new Name("leo"))))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
    @DisplayName("참가자가 7명을 초과할 경우 예외가 발생한다.")
    void overRangeNamesCount() {
        Assertions.assertThatThrownBy(() ->
                    new Names(
                            List.of(
                                    new Name("leo1"), new Name("leo2"),
                                    new Name("leo3"), new Name("leo4"),
                                    new Name("leo5"), new Name("leo6"),
                                    new Name("leo7"), new Name("leo8"))))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("참가자가 한 명도 없을 경우 예외가 발생한다.")
    void zeroNames() {
        Assertions.assertThatThrownBy(() -> new Names(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}