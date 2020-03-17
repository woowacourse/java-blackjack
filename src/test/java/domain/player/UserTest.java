package domain.player;

import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class UserTest {
	@Test
	@DisplayName("유저 이름 입력이 공란일 때 예외를 잘 뱉어내는지")
	void inputBlankName() {
		String blankName = "";
		assertThatThrownBy(() ->
				new User(blankName, 0)
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("유저 이름 입력이 null일 때 예외를 잘 뱉어내는지")
	void inputNullName() {
		assertThatThrownBy(() ->
				new User(null, 0)
		).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 승")
	@Test
	void compareScore() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FOUR, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, false)).isEqualTo(1000);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 패")
	@Test
	void compareScore2() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.SIX, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, false)).isEqualTo(-1000);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 무")
	@Test
	void compareScore3() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, false)).isEqualTo(0);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 승")
	@Test
	void compareScoreWhenBlackJack() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, true)).isEqualTo(1500);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 패")
	@Test
	void compareScoreWhenBlackJack2() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.EIGHT, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, true)).isEqualTo(-1000);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 무")
	@Test
	void compareScoreWhenBlackJack3() {
		User user = new User("a", 1000);
		Gamer dealer = new Dealer();

		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));

		assertThat(user.compareScore(dealer, true)).isEqualTo(0);
	}
}
