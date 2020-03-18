package domain.result;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlayerScoreBoardTest {
	@DisplayName("스코어 보드에 생성시 User 에 null이 들어가면 예외 발생")
	@Test
	void construct_score_board_with_null_user_exception_test() {
		assertThatNullPointerException().isThrownBy(() -> PlayerScoreBoard.of(null));
	}
}