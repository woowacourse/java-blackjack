package domain.result;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Player;
import domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ResultTypeTest {

	private Card card1;
	private Card card2;
	private User player1;
	private User player2;

	@BeforeEach
	void setUp() {
		card1 = new Card(Symbol.QUEEN, Type.HEART);
		card2 = new Card(Symbol.SEVEN, Type.HEART);
		player1 = new Player("toney");
		player2 = new Player("kouz");
		player1.addCard(card1);
		player2.addCard(card1);
	}

	@DisplayName("WIN에 대해 opposite() 동작시 LOSE 반환 확인")
	@Test
	void opposite_When_Win_Return_Lose() {
		assertEquals(ResultType.LOSE, ResultType.opposite(ResultType.WIN));
	}

	@DisplayName("from()으로 ResultType 중 WIN이 생성되는지 확인")
	@Test
	void from_When_Player1_Win_Return_WIN() {
		Card card3 = new Card(Symbol.SIX, Type.CLOVER);

		player1.addCard(card2);
		player2.addCard(card3);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.WIN);
	}

	@DisplayName("from()으로 ResultType 중 DRAW가 생성되는지 확인")
	@Test
	void from_When_Player1_draw_Return_DRAW() {
		player1.addCard(card2);
		player2.addCard(card2);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.DRAW);
	}

	@DisplayName("from()으로 ResultType 중 LOSE가 생성되는지 확인")
	@Test
	void from_When_Player1_Lose_Return_LOSE() {
		Card card3 = new Card(Symbol.SIX, Type.CLOVER);

		player1.addCard(card3);
		player2.addCard(card2);

		assertThat(ResultType.from(player1, player2)).isEqualTo(ResultType.LOSE);
	}
}