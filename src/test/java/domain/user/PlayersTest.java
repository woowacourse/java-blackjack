package domain.user;

import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static domain.user.Players.OVER_MAX_PLAYERS_COUNT;
import static domain.user.Players.UNDER_MIN_PLAYERS_COUNT;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {

	@DisplayName("Players 생성시 최대인원 초과하면 예외처리")
	@Test
	void create_Over_Max_Count_Of_Players() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				Players.of("1,2,3,4,5,6,7,8"))
				.withMessage(OVER_MAX_PLAYERS_COUNT);
	}

	@DisplayName("Players 생성시 최소인원 미만이면 예외처리")
	@Test
	void create_Under_Min_Count_Of_Players() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				Players.of(new ArrayList<>()))
				.withMessage(UNDER_MIN_PLAYERS_COUNT);
	}

	@DisplayName("getNames가 원하는 문자열을 반환하는지 확인")
	@Test
	void getNames_From_Players() {
		String expected = "kouz, toney";
		String actual = Players.of("kouz,toney").getNames();
		assertEquals(expected, actual);
	}
}
