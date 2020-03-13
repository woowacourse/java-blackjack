package domains.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import domains.card.Deck;

class PlayerTest {
	private static Player player;
	private static Deck deck;

	@BeforeAll
	static void setUp() {
		deck = new Deck();
		player = new Player("또링", deck);
	}

	@DisplayName("생성했을 때, 두 장의 카드를 보유하고 있는지 확인")
	@Test
	void constructor_InitializeParticipant_HandsSizeIsTwo() {
		assertThat(player.handSize()).isEqualTo(2);
	}

	@DisplayName("카드를 더 받을지 입력받을 때 y혹은 n이 입력되지 않았을 경우 예외처리")
	@ParameterizedTest
	@ValueSource(strings = {"Y", "1", "q"})
	void needMoreCard_InvalidAnswer_ExceptionThrown(String answer) {
		assertThatThrownBy(() -> player.needMoreCard(answer, deck))
			.isInstanceOf(InvalidPlayerException.class)
			.hasMessage(InvalidPlayerException.INVALID_INPUT);
	}
}
