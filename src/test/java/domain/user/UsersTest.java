package domain.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.*;

public class UsersTest {
	@Test
	void create_Over_Max_Count_Of_Players() {
		User user = new Dealer();
		assertThatIllegalArgumentException().isThrownBy(() ->
			Users.of("1,2,3,4,5,6,7,8", user))
			.withMessage("블랙잭의 최대 인원은 8명입니다.");
	}

	@Test
	void create_Under_Min_Count_Of_Players() {
		User user = new Dealer();
		assertThatIllegalArgumentException().isThrownBy(() ->
			Users.of(new ArrayList<>(), user))
			.withMessage("블랙잭의 최소 인원은 2명입니다.");
	}
}
