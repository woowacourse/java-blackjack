package blackjack.domain.participant;

import static blackjack.controller.BlackJackController.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerNameTest {
	@Test
	@DisplayName("이름 생성")
	void create() {
		PlayerName playerName = new PlayerName("1");
		assertThat(playerName).isInstanceOf(PlayerName.class);
	}

	@Test
	@DisplayName("이름에 공백 포함 시 에러 처리")
	void exception_space() {
		assertThatThrownBy(() -> new PlayerName("1 ")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage("이름에 공백이 포함됩니다.");
	}

	@Test
	@DisplayName("이름이 비었을 시 에러 처리")
	void exception_empty() {
		assertThatThrownBy(() -> new PlayerName("")).isInstanceOf(IllegalArgumentException.class)
			.hasMessage(ERROR_MESSAGE_INPUT);
	}
}