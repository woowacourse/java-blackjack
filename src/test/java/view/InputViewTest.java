package view;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputViewTest {

    @DisplayName("이름에 공백만 있으면 예외 처리.")
    @Test
    void Trim이후_이름이_없을경우() {

        String names = "아나키, , 모아";
        List<String> playerName = Arrays.stream(names.split(","))
                .map(String::trim)
                .toList();


        assertThatThrownBy(() -> new InputView().isBlank(playerName))
                .isInstanceOf(IllegalArgumentException.class);
    }

}
