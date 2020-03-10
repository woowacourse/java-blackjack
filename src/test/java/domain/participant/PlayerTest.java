package domain.participant;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;

class PlayerTest {
	@Test
	@DisplayName("입력받은 문자열이 null인지 검증")
	void validateNull() {
		assertThatThrownBy(() -> {
			new Player(null);
		}).isInstanceOf(NullPointerException.class);
	}

	@ParameterizedTest
	@ValueSource(strings = {"", " ", "  "})
	@DisplayName("입력받은 문자열이 공백인지 검증")
	void validateSpace(String name) {
		assertThatThrownBy(() -> {
			new Player(name);
		}).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("플레이어가 지급받은 카드를 갖고 있는지 확인")
	void receiveCard() {
		Player player = new Player("pobi");
		player.receiveCard(new Card(Symbol.ACE, Type.CLUB));

		assertThat(player.getCards()).contains(new Card(Symbol.ACE, Type.CLUB));
	}
}