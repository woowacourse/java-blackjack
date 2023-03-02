import domain.PlayerName;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNameTest {

    @DisplayName("이름은 null을 허용하지 않는다.")
    @Test
    void createNameFailTestByNull(){
        Assertions.assertThatThrownBy(() -> new PlayerName(null))
                .isInstanceOf(NullPointerException.class);
    }

    @ParameterizedTest(name = "이름의 길이는 1글자 이상 5글자 이하여야 합니다.")
    @ValueSource(strings = {"", "123456"})
    void createNameFailTestByLength(String name) {
        Assertions.assertThatThrownBy(() -> new PlayerName(name))
                .isInstanceOf(IllegalArgumentException.class);
    }


}
