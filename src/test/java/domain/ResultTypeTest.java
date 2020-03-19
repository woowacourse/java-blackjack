package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.Player;

class ResultTypeTest {

	@Test
	void from_When_User_Has_More_Score_Return_WIN() {

		Player winUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		Player loseUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.EIGHT, Type.CLOVER)));
		assertEquals(ResultType.WIN, ResultType.from(winUser, loseUser));
	}

	@Test
	void from_When_User_Has_Same_Score_Return_Draw() {
		Player winUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		Player loseUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		assertEquals(ResultType.DRAW, ResultType.from(winUser, loseUser));
	}

	@Test
	void from_When_User_Has_Lower_Score_Return_Lose() {
		Player winUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.EIGHT, Type.CLOVER)));
		Player loseUser = new Player("a", List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		assertEquals(ResultType.LOSE, ResultType.from(winUser, loseUser));
	}

	@Test
	void getProfit_Return_150_Percent_Of_Money_When_Blackjack() {
		assertEquals(100 * 1.5, ResultType.BLACKJACK_WIN.getProfit(100));
	}

	@Test
	void getProfit_Return_Zero_Percent_Of_Money_When_Draw() {
		assertEquals(0, ResultType.DRAW.getProfit(100));
	}

	@Test
	void getProfit_Return_Losted_Money_When_Lose() {
		assertEquals(100 * -1, ResultType.LOSE.getProfit(100));
	}
}