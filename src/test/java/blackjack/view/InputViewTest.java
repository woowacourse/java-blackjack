package blackjack.view;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        Names names = inputView.inputPlayNames();

        //then
        assertThat(names).isEqualTo(new Names("allen,bebop"));
    }

    @DisplayName("이름입력시 빈칸 예외처리")
    @ParameterizedTest
    @ValueSource(strings = {"\n", " \n"})
    void inputPlayNamesException(String input) {
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        InputView inputView = new InputView(scanner);

        assertThatThrownBy(inputView::inputPlayNames)
                .isInstanceOf(IllegalArgumentException.class);
    }
}