package domain.card;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CardTest {

	Card card;

	@BeforeEach
	void initCard() {
		card = new Card(Denomination.JACK, Suits.CLOVER);
	}

	@DisplayName("카드 점수 구하는 테스트")
	@Test
	void getScoreTest() {
		int score = card.getScore();

		assertThat(score).isEqualTo(10);
	}

	@DisplayName("카드 끗수 구하는 테스트")
	@Test
	void getPointTest() {
		String point = card.getPoint();

		assertThat(point).isEqualTo("J");
	}

	@DisplayName("카드 슈트 구하는 테스트")
	@Test
	void getSuitTest() {
		String suit = card.getSuit();

		assertThat(suit).isEqualTo("클로버");
	}

	@DisplayName("카드 구하는 테스트")
	@Test
	void getCardNameTest() {
		List<String> name = List.of(card.getPoint(), card.getSuit());

		assertThat(String.join("", name)).isEqualTo("J클로버");
	}
}
