import static org.assertj.core.api.Assertions.assertThatThrownBy;

import model.PlayerName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class PlayerNameTest {

    @ParameterizedTest
    @ValueSource(strings = {"딜러"})
    void createInvalidTest(String value) {
        assertThatThrownBy(() ->new PlayerName(value))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
