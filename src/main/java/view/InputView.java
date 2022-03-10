package view;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class InputView {
	private static final Scanner scanner = new Scanner(System.in);
	private static final Pattern YES_OR_NO = Pattern.compile("^[ynYN]$");

	public List<String> inputPlayerNames() {
		String names = scanner.nextLine();
		return Arrays.asList(names.split(","));
	}

	public String inputYesOrNo() {
		String decision = scanner.nextLine();
		if (!YES_OR_NO.matcher(decision).matches()) {
			throw new IllegalArgumentException("입력은 Y,N(소문자 가능)만 입력해주세요.");
		}
		return decision;
	}
}
