package domain.result;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PlayerResults {
	private final List<PlayerResult> playerResults;

	public PlayerResults(List<PlayerResult> playerResults) {
		this.playerResults = Collections.unmodifiableList(new ArrayList<>(Objects.requireNonNull(playerResults)));
	}

	public List<PlayerResult> getPlayerResults() {
		return playerResults;
	}
}
