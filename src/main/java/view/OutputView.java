package view;

import java.util.List;

import domain.card.Card;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Player;

public class OutputView {
	public static void printReceivedCards(List<Player> players, Dealer dealer) {
		StringBuilder sb = new StringBuilder();
		sb.append(dealer.getName())
			.append("와");
		for (Player player : players) {
			sb.append(player.getName());
		}
		sb.append("에게 2장의 카드를 나누었습니다.");
		System.out.println(sb);
		printCards(dealer);
		for (Player player : players) {
			printCards(player);
		}
		System.out.println();
	}

	public static void printCards(Participant user) {
		StringBuilder sb = new StringBuilder();
		sb.append(user.getName())
			.append(": ");
		for (Card card : user.getCards()) {
			sb.append(card.getSymbol().toString())
				.append(card.getType().toString());
		}
	}

	public static void printDealerCards(Dealer dealer) {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
		printCards(dealer);
	}
}