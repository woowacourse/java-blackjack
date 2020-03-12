package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import domain.user.Dealer;
import domain.user.User;

public class Result {
	private List<User> users;
	private Dealer dealer;

	public Result(List<User> users, Dealer dealer) {
		this.users = users;
		this.dealer = dealer;
	}

	public List<String> getResult() {
		List<String> results = new ArrayList<>();
		int dealerScore = dealer.calculateScore();
		for (User user : users) {
			if (dealer.isBust() && user.isBust() == false) {
				results.add(user.getName() + ": 승");
			} else if (user.calculateScore() > dealerScore) {
				results.add(user.getName() + ": 승");
			} else if (user.isBlackjack() && dealer.isBlackjack()) {
				results.add(user.getName() + ": 무");
			} else {
				results.add(user.getName() + ": 패");
			}
		}
		int win = 0;
		int draw = 0;
		int lose = 0;

		for (String result : results) {
			if (result.indexOf("승") > 0) {
				lose++;
			} else if (result.indexOf("패") > 0) {
				win++;
			} else {
				draw++;
			}
		}

		results.add(0, dealer.getName() + ": ㅅ123123123");
		return results;
	}
}
