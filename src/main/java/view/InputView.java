package view;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

import game.Blackjack;
import money.Money;
import paticipant.Player;

public class InputView {
	private static final String NAME_DELIMITER = ",";

	private final Scanner sc;

	public InputView() {
		this.sc = new Scanner(System.in);
	}

	public List<String> readPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		final String input = sc.nextLine();
		return Arrays.asList(input.split(NAME_DELIMITER));
	}

	public boolean readPlayerAnswer(final String playerName) {
		System.out.printf("%s는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)" + System.lineSeparator(), playerName);
		final String input = sc.nextLine();
		return Objects.equals(input, "y");
	}

	public Map<Player, Money> readPlayersMoney(final Blackjack blackjack) {
		final Map<Player, Money> playersMoney = new LinkedHashMap<>();
		for (final Player player : blackjack.getPlayers().getPlayers()) {
			System.out.printf("%s의 배팅 금액은?" + System.lineSeparator(), player.getName());
			final int input = readInt();
			playersMoney.put(player, new Money(input));
		}
		return playersMoney;
	}

	private int readInt() {
		try {
			return Integer.parseInt(sc.nextLine());
		} catch (final NumberFormatException e) {
			return 0;
		}
	}
}
