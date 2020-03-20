package domain.player;

import domain.Money;
import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class PlayerTest {
	@DisplayName("버스터시 점수가 0으로 반환되는지 확인")
	@Test
	void calculateBurstIsZeroScore() {
		Player user = new User("a", Money.valueOf(1000));
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.KING, Shape.DIAMOND));
		cards.add(new Card(Symbol.QUEEN, Shape.SPADE));
		cards.add(new Card(Symbol.FIVE, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(user.calculateBurstIsZeroScore()).isEqualTo(0);
	}
}
