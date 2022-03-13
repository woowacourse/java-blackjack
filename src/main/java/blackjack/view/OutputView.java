package blackjack.view;

import blackjack.domain.Dealer;
import blackjack.domain.Gamer;
import blackjack.domain.Players;
import blackjack.domain.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.Card;
import blackjack.domain.Player;
import blackjack.domain.ResultType;

public class OutputView {
	private static final String BUST_STATUS_TO_STRING = "-1";
	private static final String ACE_NUMBER_TO_STRING = "A";
	private static final String DELIMITER = ", ";

	public void displayDealerOneCard(Dealer dealer) {
		Card card = dealer.getRandomOneCard();
		String cardInfo = convertCardToString(card);
		System.out.println(dealer.getName() + ": " + cardInfo);
	}

	public void displayFirstDistribution(Players players, Dealer dealer) {
		String joinedNamesInfo = players.getPlayers().stream()
			.map(Player::getName)
			.collect(Collectors.joining(DELIMITER));
		System.out.println(dealer.getName() + "와 " + joinedNamesInfo + "에게 2장의 카드를 나누었습니다.");
	}

	public void displayAllCard(Gamer gamer) {
		List<String> strings = generateAllCardStrings(gamer);
		System.out.println(gamer.getName() + "카드: " + String.join(DELIMITER, strings));
	}

	public void displayAllCardAndScore(Gamer gamer) {
		String joinedCardsInfo = String.join(DELIMITER, generateAllCardStrings(gamer));
		String scoreString = String.valueOf(gamer.getScore());
		System.out.println(gamer.getName() + "카드: " + joinedCardsInfo + " - 결과: " + scoreString);
	}

	private List<String> generateAllCardStrings(Gamer gamer) {
		return gamer.getCards().stream()
			.map(this::convertCardToString)
			.collect(Collectors.toList());
	}

	private String convertCardToString(Card card) {
		return card.getCardName() + card.getType();
	}

	public void displayDealerUnderSevenTeen() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public void displayResult(Result result, Players players, Dealer dealer) {
		Map<Player, ResultType> gameResults = result.getResult(players, dealer);
		final int winCount = result.calculateCount(ResultType.LOSE);
		final int loseCount = result.calculateCount(ResultType.WIN);
		final int drawCount = result.calculateCount(ResultType.DRAW);
		System.out.println("## 최종 승패");
		System.out.println(dealer.getName() + ": " + winCount + "승 " + loseCount + "패 " + drawCount + "무");
		for (Player player : gameResults.keySet()) {
			System.out.println(player.getName() + ": " + gameResults.get(player).getValue());
		}
	}

	public void displayNewLine() {
		System.out.print("\n");
	}
}
