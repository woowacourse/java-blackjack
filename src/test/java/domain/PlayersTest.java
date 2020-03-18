package domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

public class PlayersTest {
	@Test
	void getNames() {
		assertThat(PlayersFactory.create("a,b,c").getNames()).isEqualTo(Arrays.asList("a", "b", "c"));
	}
}
