package domain.result;

import static org.assertj.core.api.AssertionsForInterfaceTypes.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

public class DealerResultTest {

	@Test
	void makeProfitTest() {
		List<UserResult> userResults = Arrays.asList(
			new UserResult("둔덩1", 1000),
			new UserResult("둔덩2", 1000),
			new UserResult("둔덩3", 1000)
			);
		DealerResult dealerResult = new DealerResult(userResults);

		assertThat(dealerResult.getProfit()).isEqualTo(-3000);
	}
}
