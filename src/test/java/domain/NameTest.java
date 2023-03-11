package domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import domain.name.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NameTest {

    private Name name;

    @BeforeEach
    void setName() {
        Name name = new Name("kiara");
    }

    @DisplayName("이름은 5글자 이하이다")
    @ParameterizedTest
    @ValueSource(strings = {"a", "ash", "kiara", "woowa"})
    void validateNameLength(String name) {
        assertThatNoException().isThrownBy(() -> new Name(name));
    }

    @DisplayName("이름이 5글자 초과면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"hijava", "helloworld", "woowacourse"})
    void nameNotOver5(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("플레이어의 이름은 ")
                .hasMessageContaining("보다 길 수 없습니다.");
    }

    @DisplayName("이름이 공백이면 예외가 발생한다")
    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    void nameNotEmpty(String name) {
        assertThatThrownBy(() -> new Name(name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("플레이어의 이름은 공백으로만 이루어질 수 없습니다.");
    }

    @DisplayName("이름이 딜러이면 예외가 발생한다")
    @Test
    void nameNot딜러() {
        assertThatThrownBy(() -> new Name("딜러"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("딜러라는 이름은 사용할 수 없습니다.");
    }
}
