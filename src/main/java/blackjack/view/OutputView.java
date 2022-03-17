package blackjack.view;

import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Gamer;
import blackjack.domain.gamer.Players;
import blackjack.domain.process.Result;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.gamer.Player;

public class OutputView {
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
		return card.getCardName() + card.getType().getName();
	}

	public void displayDealerUnderSevenTeen() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public void displayResult(Dealer dealer, Result result) {
		System.out.println("## 최종 승패");
		System.out.println(dealer.getName() + ": " + result.getDealerMoney());
		Map<String, Integer> playersMoney = result.getPlayersMoney();
		for (String playerName : playersMoney.keySet()) {
			System.out.println(playerName + ": " + playersMoney.get(playerName));
		}
	}

	public void displayNewLine() {
		System.out.print("\n");
	}
}
