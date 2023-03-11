package domain.result;

import domain.Player;

public class PlayerResult {

	private final String name;
	private final ResultState resultState;

	private PlayerResult(final String name, final ResultState resultState) {
		this.name = name;
		this.resultState = resultState;
	}

	public static PlayerResult decide(final Player targetPlayer, final Player comparedPlayer) {
		if (isVictory(targetPlayer, comparedPlayer)) {
			return new PlayerResult(targetPlayer.getName(), ResultState.WIN);
		}
		if (isDraw(targetPlayer, comparedPlayer)) {
			return new PlayerResult(targetPlayer.getName(), ResultState.DRAW);
		}
		return new PlayerResult(targetPlayer.getName(), ResultState.LOSS);
	}

	private static boolean isVictory(final Player targetPlayer, final Player comparedPlayer) {
		return !targetPlayer.isBust()
			&& (comparedPlayer.isBust() || targetPlayer.getScore() > comparedPlayer.getScore());
	}

	private static boolean isDraw(final Player targetPlayer, final Player comparedPlayer) {
		return targetPlayer.isBust() && comparedPlayer.isBust()
			|| targetPlayer.getScore() == comparedPlayer.getScore();
	}

	public String getName() {
		return name;
	}

	public ResultState getResultState() {
		return resultState;
	}
}
