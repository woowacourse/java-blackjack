package domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import domain.ResultType;
import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.user.User;

class ResultTypeTest {

	@Test
	void from_When_User_Has_More_Score_Return_WIN() {
		User winUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		User loseUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.EIGHT, Type.CLOVER)));
		assertEquals(ResultType.WIN, ResultType.from(winUser, loseUser));
	}

	@Test
	void from_When_User_Has_Same_Score_Return_Draw() {
		User winUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		User loseUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		assertEquals(ResultType.DRAW, ResultType.from(winUser, loseUser));
	}

	@Test
	void from_When_User_Has_Lower_Score_Return_Draw() {
		User winUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.EIGHT, Type.CLOVER)));
		User loseUser = User.of(List.of(new Card(Symbol.ACE, Type.CLOVER), new Card(Symbol.NINE, Type.CLOVER)));
		assertEquals(ResultType.LOSE, ResultType.from(winUser, loseUser));
	}
}