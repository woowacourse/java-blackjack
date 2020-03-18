package domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NamesTest {

	@DisplayName("문자열로 Names생성 확인")
	@Test
	void of_Create_Names_Using_String() {
		assertThat(Names.of("toney, kouz, pobi")).isInstanceOf(Names.class);
	}
}
