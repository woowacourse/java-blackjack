package blackjack.domain.dto;

import java.util.List;
import java.util.Map;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;

public class BlackJackDto {
	private static final String NAME_DELIMITER = ": ";
	private static final String CARD_DELIMITER = ", ";
	private static final String MESSAGE_WIN = "승 ";
	private static final String MESSAGE_LOSE = "패 ";
	private static final int FIRST_CARD = 0;

	private final Participant dealer;
	private final List<Participant> players;
	private final Map<Participant, Boolean> result;

	private BlackJackDto(Participant dealer, List<Participant> players, Map<Participant, Boolean> result) {
		this.dealer = dealer;
		this.players = players;
		this.result = result;
	}

	public static BlackJackDto from(BlackJack blackJack) {
		return new BlackJackDto(blackJack.getDealer(), blackJack.getPlayers(), blackJack.getResult());
	}

	public Participant getDealer() {
		return dealer;
	}

	public List<Participant> getPlayers() {
		return players;
	}

	public Map<Participant, Boolean> getResult() {
		return result;
	}
}
