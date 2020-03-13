package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Player;
import domain.user.User;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTypeTest {
	@Test
	void opposite_When_Win_Return_Lose() {
		assertEquals(ResultType.LOSE, ResultType.opposite(ResultType.WIN));
	}

	@Test
	void from_When_Player1_Win_Return_WIN() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);
		Card card3 = new Card(Symbol.SIX, Type.CLOVER);
		User player1 = new Player("toney");
		User player2 = new Player("kouz");

		player1.addCard(card1);
		player1.addCard(card2);
		player2.addCard(card1);
		player2.addCard(card3);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.WIN);
	}

	@Test
	void from_When_Player1_draw_Return_DRAW() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);
		User player1 = new Player("toney");
		User player2 = new Player("kouz");

		player1.addCard(card1);
		player1.addCard(card2);
		player2.addCard(card1);
		player2.addCard(card2);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.DRAW);
	}

	@Test
	void from_When_Player1_Lose_Return_LOSE() {
		Card card1 = new Card(Symbol.QUEEN, Type.HEART);
		Card card2 = new Card(Symbol.SEVEN, Type.HEART);
		Card card3 = new Card(Symbol.SIX, Type.CLOVER);
		User player1 = new Player("toney");
		User player2 = new Player("kouz");

		player1.addCard(card1);
		player1.addCard(card3);
		player2.addCard(card1);
		player2.addCard(card2);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.LOSE);
	}
}