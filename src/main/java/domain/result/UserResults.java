package domain.result;

import static java.util.stream.Collectors.*;

import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import domain.user.User;

public class UserResults {
	private final List<UserResult> playerResults;
	private final UserResult dealerResult;

	private UserResults(List<UserResult> userResults, UserResult dealerResult) {
		this.playerResults = userResults;
		this.dealerResult = dealerResult;
	}

	public static UserResults fromPlayerResultsAndDealer(List<UserResult> userResults,
		User dealer) {
		UserResult dealerResult = initDealerResult(userResults, dealer);
		return new UserResults(userResults, dealerResult);
	}

	private static UserResult initDealerResult(List<UserResult> userResults, User dealer) {
		int dealerPrize = userResults.stream()
			.map(UserResult::getPrize)
			.mapToInt(val -> val * -1)
			.sum();

		return new UserResult(dealer, dealerPrize);
	}

	public List<UserResult> getPlayerResults() {
		return Stream.concat(Stream.of(dealerResult), playerResults.stream())
			.collect(collectingAndThen(toList(), Collections::unmodifiableList));
	}
}
