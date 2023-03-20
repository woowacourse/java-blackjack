package domain.player;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

class NameTest {
    @Test
    @DisplayName("한글, 영어와 숫자로 이루어진 이름을 등록할 수 있다.")
    void givenValidName_thenSuccess() {
        String expected = "한글HANguel09";
        Name name = Name.of(expected);

        assertThat(name.getName())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("한글, 영어 또는 숫자가 아닌 이름을 등록할 수 없다.")
    void givenInvalidName_thenFail() {
        String expected = "日本語";

        assertThatThrownBy(() -> Name.of(expected))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("공백을 이름으로 입력할 수 없다.")
    void givenEmptyValue_thenFail() {
        assertAll(
                () -> assertThatThrownBy(() -> Name.of(""))
                        .isInstanceOf(IllegalArgumentException.class),
                () -> assertThatThrownBy(() -> Name.of(" "))
                        .isInstanceOf(IllegalArgumentException.class)
        );
    }

    @Test
    @DisplayName("딜러를 나타내는 값을 이름으로 입력할 수 없다.")
    void givenDealerName_thenFail() {
        assertThatThrownBy(() -> Name.of(Name.DEALER_NAME))
                .isInstanceOf(IllegalArgumentException.class);
    }
}