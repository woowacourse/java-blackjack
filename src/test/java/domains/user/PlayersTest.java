package domains.user;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import domains.card.Deck;

class PlayersTest {

	@DisplayName("참가자들간 중복된 이름이 있다면 예외처리")
	@Test
	void checkDuplicationName_HasDuplication_ExceptionThrown() {
		assertThatThrownBy(() -> new Players("a,b,c, c", new Deck()))
			.isInstanceOf(InvalidPlayersException.class)
			.hasMessage(InvalidPlayersException.DUPLICATION);
	}
}
