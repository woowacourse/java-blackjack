package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;

class InputViewTest {
    @DisplayName("이름 입력 테스트")
    @Test
    void inputPlayNames() {
        //given
        String input = "allen,bebop";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        InputView inputView = new InputView(scanner);

        //when
        List<String> names = inputView.inputPlayNames();

        //then
        assertThat(names).containsExactly("allen", "bebop");
    }

}