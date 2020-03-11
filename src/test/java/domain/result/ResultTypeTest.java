package domain.result;

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
class ResultTypeTest {
	@Test
	void opposite_When_Win_Return_Lose() {
		assertEquals(ResultType.LOSE, ResultType.opposite(ResultType.WIN));
	}
}