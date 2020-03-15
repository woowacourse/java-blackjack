package domain.user;

import java.util.Arrays;

public enum PlayerIntentionType {
	WANT_DRAW("y"),
	STAND("n");

	private final String value;

	PlayerIntentionType(String value) {
		this.value = value;
	}

	public static PlayerIntentionType of(String intention) {
		return Arrays.stream(PlayerIntentionType.values())
			.filter(type -> type.value.equals(intention))
			.findFirst()
			.orElseThrow(NullPointerException::new);
	}

	public boolean isWantDraw() {
		return WANT_DRAW.equals(this);
	}
}
