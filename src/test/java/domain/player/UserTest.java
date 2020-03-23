package domain.player;

import domain.Money;
import domain.card.cardfactory.Card;
import domain.card.cardfactory.Shape;
import domain.card.cardfactory.Symbol;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;

public class UserTest {
	private User user;
	private Gamer dealer;

	@BeforeEach
	void setUp() {
		user = new User("a", Money.valueOf(1000));
		dealer = new Dealer();
	}

	@Test
	@DisplayName("유저 이름 입력이 공란일 때 예외를 잘 뱉어내는지")
	void inputBlankName() {
		String blankName = "";
		assertThatThrownBy(() ->
				new User(blankName, Money.valueOf(1000))
		).isInstanceOf(IllegalArgumentException.class);
	}

	@Test
	@DisplayName("유저 이름 입력이 null일 때 예외를 잘 뱉어내는지")
	void inputNullName() {
		assertThatThrownBy(() ->
				new User(null, Money.valueOf(1000))
		).isInstanceOf(IllegalArgumentException.class);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 승")
	@Test
	void compareScore() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FOUR, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(1000);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 패")
	@Test
	void compareScore2() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.SIX, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(-1000);
	}

	@DisplayName("블랙잭 아닐 경우 수익계산 - 무")
	@Test
	void compareScore3() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(0);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 승")
	@Test
	void compareScoreWhenBlackJack() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.FIVE, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(1500);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 패")
	@Test
	void compareScoreWhenBlackJack2() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.EIGHT, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(-1000);
	}

	@DisplayName("블랙잭일 경우 수익계산 - 무")
	@Test
	void compareScoreWhenBlackJack3() {
		user.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));
		user.checkInitCardBlackJack();
		dealer.cardDraw(Arrays.asList(new Card(Symbol.ACE, Shape.DIAMOND),
				new Card(Symbol.KING, Shape.CLOVER)));

		assertThat(user.compareScore(dealer)).isEqualTo(0);
	}
}
