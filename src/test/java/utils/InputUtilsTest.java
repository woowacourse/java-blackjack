package utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class InputUtilsTest {
	@Test
	@DisplayName("이름이 제대로 들어가있는지 확인하는 테스트")
	public void splitAsCommaTest() {
		List<String> names = InputUtils.splitAsDelimiter("pobi,json,java");
		assertThat(names).contains("pobi").contains("java");
	}
}