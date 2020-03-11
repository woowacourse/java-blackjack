package domain.user;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class PlayersTest {

	@Test
	void create_With_Names() {
		List<Player> values = Arrays.asList(
				new Player("kouz"),
				new Player("toney")
		);

		assertEquals(Players.of(values), Players.of("kouz,toney"));
	}
}
