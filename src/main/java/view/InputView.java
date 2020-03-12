package view;

import domain.user.Player;

import java.util.Scanner;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public class InputView {
	private static final Scanner SCANNER = new Scanner(System.in);

	public static String inputPlayerIntention(Player player) {
		System.out.println(player.toString() + "는 한장의 카드를 더 받겠습니까? (예는 y, 아니오는 n)");
		return SCANNER.nextLine();
	}
}
