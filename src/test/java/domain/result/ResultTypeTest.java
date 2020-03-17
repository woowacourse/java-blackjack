package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domain.card.Card;
import domain.card.Symbol;
import domain.card.Type;
import domain.gamer.Name;
import domain.gamer.Player;

class ResultTypeTest {
	@Test
	@DisplayName("플레이어의 승패와 대칭되는 딜러의 승패가 반환되는지")
	void reverseTest() {
		assertThat(ResultType.reverse(ResultType.WIN)).isEqualTo(ResultType.LOSE);
		assertThat(ResultType.reverse(ResultType.LOSE)).isEqualTo(ResultType.WIN);
		assertThat(ResultType.reverse(ResultType.DRAW)).isEqualTo(ResultType.DRAW);
	}

	@Test
	@DisplayName("점수비교를 통해 올바른 결과(Enum)을 생성하는지 테스트")
	void ofTest() {
		Player pobi = new Player(new Name("pobi"));
		Player brown = new Player(new Name("brown"));
		pobi.hit(new Card(Symbol.SEVEN, Type.CLUB));
		brown.hit(new Card(Symbol.SIX, Type.CLUB));
		assertThat(ResultType.of(brown, pobi)).isEqualTo(ResultType.LOSE);
		assertThat(ResultType.of(pobi, brown)).isEqualTo(ResultType.WIN);
		assertThat(ResultType.of(brown, brown)).isEqualTo(ResultType.DRAW);
	}
}