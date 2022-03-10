package blackjack.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import blackjack.domain.user.Players;

public class PlayersTest {
	@Test
	@DisplayName("빈 리스트를 받았을 때 에러 발생")
	void check_empty_list() {
		String lines = ",,,,";
		List<String> strings = Arrays.asList(lines.split(","));
		assertThatThrownBy(() -> new Players(strings))
			.isInstanceOf(IllegalArgumentException.class);
	}
}
