package view;

import domain.user.Players;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class OutputView {
	public static void printInitialDistribution(Players players) {
		System.out.println("딜러와" + players.getNames() + "에게 2장의 카드를 나누었습니다.");
	}
}
