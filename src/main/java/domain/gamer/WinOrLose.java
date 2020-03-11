package domain.gamer;

import java.util.Arrays;

public enum WinOrLose {
	WIN(true, "승"),
	LOSE(false, "패");

	private boolean value;
	private String initial;

	WinOrLose(boolean value, String initial) {
		this.value = value;
		this.initial = initial;
	}

	public static WinOrLose of(boolean value) {
		return Arrays.stream(WinOrLose.values())
			.filter(x -> x.value == value)
			.findFirst()
			.orElseThrow(IllegalArgumentException::new);
	}

	public boolean isValue() {
		return value;
	}

	public String getInitial() {
		return initial;
	}
}
