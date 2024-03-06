package view;

import java.util.List;
import java.util.StringJoiner;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;

public class OutputView {

	/**
	 * 딜러와 pobi, jason에게 2장을 나누었습니다.
	 * 딜러: 3다이아몬드
	 * pobi카드: 2하트, 8스페이드
	 * jason카드: 7클로버, K스페이드
	 */
	public void printInitCardStatus(Dealer dealer, Players players) {
		System.out.println();
		List<Player> playerInfos = players.getPlayers();
		System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", createPlayerNamesText(playerInfos)));

		Card dealerInitCard = dealer.getCardHand().get(0);
		System.out.println(String.format("딜러: %s", createCardInfoText(dealerInitCard)));

		for (Player playerInfo : playerInfos) {
			StringJoiner cardInfoJoiner = new StringJoiner(", ");
			for (Card card : playerInfo.getCardHand()) {
				cardInfoJoiner.add(createCardInfoText(card));
			}
			System.out.println(String.format("%s카드: %s", playerInfo.getName(), cardInfoJoiner));
		}
	}

	private String createPlayerNamesText(List<Player> playerInfos) {
		StringJoiner playerNameJoiner = new StringJoiner(", ");

		for (Player playerInfo : playerInfos) {
			playerNameJoiner.add(playerInfo.getName());
		}
		return playerNameJoiner.toString();
	}

	private String createCardInfoText(Card card) {
		return String.format("%s%s", card.getRankName(), card.getSuitName());
	}
}
