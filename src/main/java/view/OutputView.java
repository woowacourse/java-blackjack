package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domains.result.Profits;
import domains.user.Dealer;
import domains.user.Player;
import domains.user.Players;
import domains.user.money.ProfitMoney;

public class OutputView {
	public static void printInputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}

	public static void printInputBettingMoney(Player player) {
		System.out.println(player.getName() + "의 베팅금액을 입력하세요.");
	}

	public static void printInitialHands(Players players, Dealer dealer) {
		List<String> names = new ArrayList<>();
		for (Player player : players) {
			names.add(player.getName());
		}
		System.out.println("딜러와 " + String.join(",", names) + "에게 2장의 카드를 나누었습니다.");

		System.out.println("딜러 카드: " + dealer.openFirstCard());
		for (Player player : players) {
			printHands(player);
		}
	}

	public static void printHands(Player player) {
		System.out.println(player.getName() + "카드 : " + player.getHandsWords());
	}

	public static void printNeedMoreCard(Player player) {
		System.out.println(player.getName() + "은 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
	}

	public static void printBurst(Player player) {
		System.out.println(player.getName() + "은 버스트입니다.");
	}

	public static void printDealerHitCard() {
		System.out.println("딜러는 16이하라 한장의 카드를 더 받았습니다.");
	}

	public static void printAllHands(Players players, Dealer dealer) {
		System.out.println("딜러 카드: " + dealer.getHandsWords() + " - 결과 : " + dealer.score());
		for (Player player : players) {
			System.out.println(player.getName() + "카드 : " + player.getHandsWords() + " - 결과 : " + player.score());
		}
	}

	public static void printGameResult(Profits profits) {
		System.out.println("딜러: " + profits.createDealerProfit());

		Map<Player, ProfitMoney> playerProfits = profits.getPlayerProfits();
		for (Player player : playerProfits.keySet()) {
			System.out.println(player.getName() + " : " + playerProfits.get(player));
		}
	}
}
