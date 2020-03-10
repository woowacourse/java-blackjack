package utils;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

public class InputUtilsTest {
	@Test
	public void splitAsCommaTest() {
		List<String> names = InputUtils.splitAsDelimiter("pobi,json,java");

		assertThat(names).contains("pobi").contains("java");
	}
}