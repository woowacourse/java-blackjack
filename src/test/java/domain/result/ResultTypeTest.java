package domain.result;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResultTypeTest {
	@Test
	void opposite_When_Win_Return_Lose() {
		assertEquals(ResultType.LOSE, ResultType.opposite(ResultType.WIN));
	}
}