package utils;

import domain.Money;
import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import domain.player.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class CardPrintUtilsTest {
	private User user;

	@BeforeEach
	void setUp() {
		user = new User("a", Money.valueOf(1000));
	}

	@DisplayName("모든 카드 출력 테스트")
	@Test
	void toStringAllCardTest() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(CardPrintUtils.formatNameAndAllCard(user)).isEqualTo("a카드 : A다이아몬드, 8스페이드");
	}

	@DisplayName("한장의 카드 출력 테스트")
	@Test
	void toStringOneCardTest() {
		List<Card> cards = new ArrayList<>();
		cards.add(new Card(Symbol.ACE, Shape.DIAMOND));
		cards.add(new Card(Symbol.EIGHT, Shape.SPADE));
		user.cardDraw(cards);

		assertThat(CardPrintUtils.formatNameAndOneCard(user)).isEqualTo("a카드 : A다이아몬드");
	}
}
