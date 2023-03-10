package domain.result;

import domain.Player;
import domain.Score;

public class PlayerResult {

	private final String name;
	private final ResultState resultState;

	private PlayerResult(final String name, final ResultState resultState) {
		this.name = name;
		this.resultState = resultState;
	}

	public static PlayerResult decide(final Player targetPlayer, final Player comparedPlayer) {
		if (isVictory(targetPlayer.getScore(), comparedPlayer.getScore())) {
			return new PlayerResult(targetPlayer.getName(), ResultState.WIN);
		}
		if (isDraw(targetPlayer.getScore(), comparedPlayer.getScore())) {
			return new PlayerResult(targetPlayer.getName(), ResultState.DRAW);
		}
		return new PlayerResult(targetPlayer.getName(), ResultState.LOSS);
	}

	private static boolean isVictory(final Score score, final Score comparedScore) {
		return !score.isBust()
			&& (comparedScore.isBust() || score.getValue() > comparedScore.getValue());
	}

	private static boolean isDraw(final Score score, final Score comparedScore) {
		return score.isBust() && comparedScore.isBust()
			|| score.getValue() == comparedScore.getValue();
	}

	public String getName() {
		return name;
	}

	public ResultState getResultState() {
		return resultState;
	}
}
