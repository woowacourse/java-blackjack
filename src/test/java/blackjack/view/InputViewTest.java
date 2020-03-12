package blackjack.view;

import static org.assertj.core.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Scanner;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import blackjack.view.dto.NamesDTO;

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
		NamesDTO namesDTO = inputView.inputPlayNames();

		//then
		assertThat(namesDTO).isEqualTo(new NamesDTO("allen,bebop"));
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