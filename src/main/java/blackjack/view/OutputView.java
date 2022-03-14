package blackjack.view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.user.Player;
import blackjack.domain.result.ResultType;

public class OutputView {
	public void displayOneCard(final Card card) {
		String cardNumber = convertCardNumberToString(card);
		System.out.println("딜러: " + cardNumber + card.getSuit());
	}

	public void displayFirstDistribution(final List<Player> players) {
		List<String> collect = players.stream()
			.map(Player::getName)
			.collect(Collectors.toList());
		System.out.println("딜러와 " + String.join(", ", collect) + "에게 2장의 카드를 나누었습니다.");
	}

	public void displayAllCard(final String name, final List<Card> cards) {
		List<String> strings = generateAllCardStrings(cards);
		System.out.println(name + "카드: " + String.join(", ", strings));
	}

	public void displayAllCardAndScore(final String name, final int score, final List<Card> cards) {
		List<String> strings = generateAllCardStrings(cards);
		String scoreString = String.valueOf(score);
		if ("-1".equals(scoreString)) {
			scoreString = "버스트";
		}
		System.out.println(name + "카드: " + String.join(", ", strings) + " - 결과: " + scoreString);
	}

	private List<String> generateAllCardStrings(final List<Card> cards) {
		List<String> strings = cards.stream()
			.map(card -> convertCardNumberToString(card) + card.getSuit())
			.collect(Collectors.toList());
		return strings;
	}

	private String convertCardNumberToString(Card card) {
		String cardNumber = String.valueOf(card.getScore());
		if ("1".equals(cardNumber)) {
			cardNumber = "A";
		}
		return cardNumber;
	}

	public void displayDealerUnderSevenTeen() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public void displayResult(final Map<Player, ResultType> result) {
		final int winCount = (int) result.values().stream()
			.filter(resultType -> resultType == ResultType.LOSE)
			.count();
		final int loseCount = (int)result.values().stream()
			.filter(resultType -> resultType == ResultType.WIN)
			.count();
		final int drawCount = result.size() - (winCount + loseCount);
		System.out.println("## 최종 승패");
		System.out.println("딜러: " + winCount + "승 " + loseCount + "패 " + drawCount + "무");
		for (Player player : result.keySet()) {
			System.out.println(player.getName() + ": " + result.get(player).getValue());
		}
	}

	public void printException(final String message) {
		System.out.println("[ERROR] " + message);
	}
}
