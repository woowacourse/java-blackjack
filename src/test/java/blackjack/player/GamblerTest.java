package blackjack.player;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.card.CardBundle;

class GamblerTest {

	@DisplayName("겜블러는 게임 결과를 만들어낼수 없습니다.")
	@Test
	void getReport() {
		Player gambler1 = new Gambler(new CardBundle(), "bebop");
		Player gambler2 = new Gambler(new CardBundle(), "allen");

		assertThatThrownBy(() -> gambler1.getReport(gambler2))
			.isInstanceOf(UnsupportedOperationException.class);
	}
}