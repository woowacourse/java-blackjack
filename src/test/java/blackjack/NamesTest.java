package blackjack;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class NamesTest {
    @Test
    @DisplayName("이름 목록을 포함한 일급 컬렉션을 생성 한다.")
    public void Names_Instance_create_with_StringList() {
        List<String> values = List.of("초롱", "조이썬");

        Assertions.assertThatCode(() -> {
            var sut = Names.from(values);
        }).doesNotThrowAnyException();
    }
}
