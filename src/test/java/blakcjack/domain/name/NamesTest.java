package blakcjack.domain.name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static blakcjack.domain.name.DuplicatePlayerNamesException.DUPLICATE_NAME_ERROR;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NamesTest {
	@DisplayName("생성자 테스트")
	@Test
	void create() {
		final Names names = new Names(Arrays.asList("sakjung", "mediumBear"));
		assertThat(names).isEqualTo(new Names(Arrays.asList("sakjung", "mediumBear")));
	}

	@DisplayName("중복된 이름을 받았을 떄 에러 반환하는지")
	@Test
	void validateDuplication_duplicatePlayerNames_throwError() {
		assertThatThrownBy(() -> new Names(Arrays.asList("sakjung", "mediumBear", "sakjung")))
				.isInstanceOf(DuplicatePlayerNamesException.class)
				.hasMessage(DUPLICATE_NAME_ERROR);
	}
}