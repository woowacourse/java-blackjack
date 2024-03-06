package view;

import java.util.List;
import java.util.StringJoiner;

import domain.Card;
import domain.Dealer;
import domain.Player;
import domain.Players;

public class OutputView {

	public void printInitCardStatus(Dealer dealer, Players players) {
		System.out.println();
		List<Player> playerInfos = players.getPlayers();
		System.out.println(String.format("딜러와 %s에게 2장을 나누었습니다.", createPlayerNamesText(playerInfos)));

		Card dealerInitCard = dealer.getCardHand().get(0);
		System.out.println(String.format("딜러: %s", createCardInfoText(dealerInitCard)));

		for (Player playerInfo : playerInfos) {
			printCardStatus(playerInfo);
		}
		System.out.println();
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

	public void printCardStatus(Player player) {
		System.out.println(String.format("%s카드: %s", player.getName(), createCardsInfoText(player.getCardHand())));
	}

	private String createCardsInfoText(List<Card> cards) {
		StringJoiner cardInfoJoiner = new StringJoiner(", ");
		for (Card card : cards) {
			cardInfoJoiner.add(createCardInfoText(card));
		}

		return cardInfoJoiner.toString();
	}

	public void printDealerHitMessage() {
		System.out.println(System.lineSeparator() + "딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public void printTotalCardStatus(Dealer dealer, Players players) {
		System.out.println();
		System.out.println(String.format("딜러 카드: %s - 결과: %d",
			createCardsInfoText(dealer.getCardHand()), dealer.getScore()));

		for (Player player : players.getPlayers()) {
			System.out.println(String.format("%s카드: %s - 결과: %d",
				player.getName(), createCardsInfoText(player.getCardHand()), player.getScore()));
		}
	}
}
