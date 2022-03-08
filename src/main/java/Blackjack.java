import java.util.List;

public class Blackjack {

	private static final String ERROR_MESSAGE_PLAYER_NUMBER_EXCEED = "[ERROR] 참가자의 수는 8명을 초과할 수 없습니다.";

	public Blackjack(List<String> players) {
		if (players.size() > 8) {
			throw new IllegalArgumentException(ERROR_MESSAGE_PLAYER_NUMBER_EXCEED);
		}
	}
}
