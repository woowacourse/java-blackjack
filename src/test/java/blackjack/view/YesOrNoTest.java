package blackjack.view;

import blackjack.view.exceptions.YesOrNoException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class YesOrNoTest {
	private YesOrNo yes;
	private YesOrNo no;

	@BeforeEach
	void setUp() {
		yes = YesOrNo.of("Y");
		no = YesOrNo.of("N");
	}

	@Test
	void of() {
		assertThat(yes).isNotNull();
		assertThat(no).isNotNull();
	}

	@Test
	void of_InvalidArgument_ShouldThrowException() {
		assertThatThrownBy(() ->
				YesOrNo.of("z"))
				.isInstanceOf(YesOrNoException.class);
	}

	@Test
	void isYes() {
		assertThat(yes.isYes()).isTrue();
		assertThat(no.isYes()).isFalse();
	}
}