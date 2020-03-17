package utils;

import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import domain.player.Player;
import domain.player.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardPrintUtilsTest {
	@DisplayName("모든 카드 출력 테스트")
	@Test
	void toStringAllCardTest() {
		Player user = new User("a", 0);
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(CardPrintUtils.formatNameAndAllCard(user)).isEqualTo("a카드 : A다이아몬드, 8스페이드");
	}

	@DisplayName("한장의 카드 출력 테스트")
	@Test
	void toStringOneCardTest() {
		Player user = new User("a", 0);
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(CardPrintUtils.formatNameAndOneCard(user)).isEqualTo("a카드 : A다이아몬드");
	}
}
