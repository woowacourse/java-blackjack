package domain.result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import org.junit.jupiter.api.Test;

public class MatchResultTest {
	@Test
	void reverseWinAndLose() {
		assertThat(MatchResult.WIN.reverseWinAndLose()).isEqualTo(MatchResult.LOSE);
		assertThat(MatchResult.LOSE.reverseWinAndLose()).isEqualTo(MatchResult.WIN);
		assertThat(MatchResult.DRAW.reverseWinAndLose()).isEqualTo(MatchResult.DRAW);
	}
}
