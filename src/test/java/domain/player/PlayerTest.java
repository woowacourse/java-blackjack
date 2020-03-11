package domain.player;

import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
	@DisplayName("모든 카드 출력 테스트")
	@Test
	void toStringAllCardTest() {
		Player user = new User("a");
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(user.toStringAllCard()).isEqualTo("a카드 : A다이아몬드, 8스페이드");
	}

	@DisplayName("한장의 카드 출력 테스트")
	@Test
	void toStringOneCardTest() {
		Player user = new User("a");
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(user.toStringOneCard()).isEqualTo("a카드 : A다이아몬드");
	}
}
