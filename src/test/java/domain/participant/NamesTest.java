package domain.participant;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class NamesTest {
    @Test
    void duplicateNames() {
        Assertions.assertThatThrownBy(() ->
                        new Names(List.of(new Name("leo"), new Name("leo"))))
                .isInstanceOf(IllegalArgumentException.class);

    }

    @Test
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
    void zeroNames() {
        Assertions.assertThatThrownBy(() -> new Names(List.of()))
                .isInstanceOf(IllegalArgumentException.class);
    }
}