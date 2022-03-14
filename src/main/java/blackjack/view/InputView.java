package blackjack.view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern YES_OR_NO = Pattern.compile("^[ynYN]$");
	private static final String DELIMITER = ",";

	public List<String> inputPlayerNames() {
		System.out.println("게임에 참여할 사람의 이름을 입력하세요.(쉼표 기준으로 분리)");
		return Arrays.asList(scanner.nextLine().split(DELIMITER));
	}

	public boolean inputYesOrNo(String name) {
		System.out.println(name + "는 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)");
		if (!YES_OR_NO.matcher(scanner.nextLine()).matches()) {
			throw new IllegalArgumentException("입력은 Y,N(소문자 가능)만 입력해주세요.");
		}
		return true;
	}
}
