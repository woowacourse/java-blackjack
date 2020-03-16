package blackjack.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class InputViewTest {
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