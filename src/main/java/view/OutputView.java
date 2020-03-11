package view;

import java.util.Map;
import java.util.stream.Collectors;

import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import domain.gamer.WinOrLose;

public class OutputView {
	private static final String NEWLINE = System.getProperty("line.separator");

	public static void printPlayerNamesGuide() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
	}

	public static void printInitCardGuide(Gamers gamers) {
		StringBuffer initCardGuide = new StringBuffer();
		initCardGuide.append(NEWLINE);
		initCardGuide.append("딜러와 ");
		initCardGuide.append(gamers.stream()
			.map(Gamer::getName)
			.collect(Collectors.joining(",")));
		initCardGuide.append(" 에게 카드 2장을 나누었습니다.");

		System.out.println(initCardGuide.toString());
	}

	public static void printGamersCard(Gamers gamers) {
		printGamerCard(gamers.getDealer());
		gamers.stream().forEach(OutputView::printGamerCard);
		System.out.println();
	}

	public static void printGamerCard(Gamer gamer) {
		System.out.println(gamer);
	}

	public static void printAddCardAtDealer() {
		System.out.println("딜러는 16이하라 카드 한장 더 받았습니다.");
	}

	public static void printAddCardGuide(Player player) {
		System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)", player.getName());
	}

	public static void printCardsResultAndScore(Gamers gamers) {
		System.out.println();
		System.out.printf("%s - 결과 : %s" + NEWLINE, gamers.getDealer(), gamers.getDealer().calculateWithAce());
		gamers.stream()
			.forEach(player -> System.out.printf("%s - 결과 : %s" + NEWLINE, player, player.calculateWithAce()));
	}

	public static void printPlayersWinOrLose(Map<String, WinOrLose> playersWinOrLose) {
        System.out.println();
        System.out.println("## 최종 승패");
        System.out.println("딜러 : " + playersWinOrLose.values().stream().filter(x -> x == WinOrLose.LOSE).count() + WinOrLose.WIN.getInitial()
            + playersWinOrLose.values().stream().filter(x -> x == WinOrLose.WIN).count() + WinOrLose.LOSE.getInitial()
        );
		playersWinOrLose.forEach((a, b) -> System.out.println(a + " : " + b.getInitial()));
	}
}
