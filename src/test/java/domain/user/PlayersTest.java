package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static domain.user.Players.OVER_MAX_PLAYERS_COUNT;
import static domain.user.Players.UNDER_MIN_PLAYERS_COUNT;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class PlayersTest {

	@Test
	void create_With_Names() {
		List<Player> values = Arrays.asList(
				new Player("kouz"),
				new Player("toney")
		);

		assertEquals(Players.of(values), Players.of("kouz,toney"));
	}

	@Test
	void create_Over_Max_Count_Of_Players() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				Players.of("1,2,3,4,5,6,7,8"))
				.withMessage(OVER_MAX_PLAYERS_COUNT);
	}

	@Test
	void create_Under_Min_Count_Of_Players() {
		assertThatIllegalArgumentException().isThrownBy(() ->
				Players.of(new ArrayList<>()))
				.withMessage(UNDER_MIN_PLAYERS_COUNT);
	}

	@Test
	void getNames_From_Players() {
		String expected = "kouz, toney";
		String actual = Players.of("kouz,toney").getNames();
		assertEquals(expected, actual);
	}
}
