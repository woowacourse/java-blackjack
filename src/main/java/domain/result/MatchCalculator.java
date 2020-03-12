package domain.result;

import java.util.ArrayList;
import java.util.List;

import domain.user.Dealer;
import domain.user.User;

public class MatchCalculator {
	private List<User> users;
	private Dealer dealer;

	public MatchCalculator(List<User> users, Dealer dealer) {
		this.users = users;
		this.dealer = dealer;
	}

	public List<MatchResult> getMatchResults() {
		List<MatchResult> matchResults = new ArrayList<>();
		for (User user : users) {
			MatchResult matchResult = MatchResult.findMatchResult(user, dealer);
			matchResults.add(matchResult);
		}
		return matchResults;
	}
}
