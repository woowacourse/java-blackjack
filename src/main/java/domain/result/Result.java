package domain.result;

import java.util.ArrayList;
import java.util.List;

import domain.user.Dealer;
import domain.user.User;

public class Result {
	private List<User> users;
	private Dealer dealer;

	public Result(List<User> users, Dealer dealer) {
		this.users = users;
		this.dealer = dealer;
	}

	public List<MatchResult> getResult() {
		List<MatchResult> matchResults = new ArrayList<>();
		List<UserResult> userResults = new ArrayList<>();
		for (User user : users) {
			MatchResult matchResult = MatchResult.findMatchResult(user, dealer);
			matchResults.add(matchResult);
		}
		return matchResults;
		// TODO: 2020/03/12 딜러의 승패는 어떻게 표현해야 할까?
	}
}
