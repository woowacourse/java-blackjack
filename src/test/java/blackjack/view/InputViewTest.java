package blackjack.view;

import blackjack.domain.card.CardBundle;
import blackjack.domain.generic.BettingMoney;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.PlayerInfo;
import blackjack.view.dto.DrawRequestDTO;
import blackjack.view.dto.NameMoneyDTO;
import blackjack.view.dto.NamesDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
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

		//when
		InputView inputView = new InputView(scanner);

		//then
		assertThat(inputView.inputPlayNames()).isInstanceOf(NamesDTO.class);
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

	@DisplayName("카드를 추가로 받을지 말지에 대한 요청 객체 생성")
	@Test
	void inputDrawRequest() {
		//given
		String input = "y";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(inputStream);

		//when
		InputView inputView = new InputView(scanner);

		//then
		assertThat(inputView.inputDrawRequest(new Gambler(CardBundle.emptyBundle(), new PlayerInfo("bebop", BettingMoney.of(0))))).isInstanceOf(DrawRequestDTO.class);
	}

	@DisplayName("사용자에게 베팅금액을 입력하여 NameMoneyDTO 객체 생성")
	@Test
	void inputBettingMoney() {
		//given
		List<String> names = Arrays.asList("bebop");
		String input = "1000";
		InputStream inputStream = new ByteArrayInputStream(input.getBytes());
		Scanner scanner = new Scanner(inputStream);

		InputView inputView = new InputView(scanner);

		//when
		List<NameMoneyDTO> nameMoneyDTOS = inputView.inputBettingMoney(names);

		//then
		assertThat(nameMoneyDTOS).hasSize(1);
	}
}