package blackjack.domain.dto;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import blackjack.domain.BlackJack;
import blackjack.domain.Participant;
import blackjack.domain.card.Card;

public class BlackJackDto {
	private static final String NAME_DELIMITER = ": ";
	private static final String CARD_DELIMITER = ", ";
	private static final String MESSAGE_WIN = "승 ";
	private static final String MESSAGE_LOSE = "패 ";
	private static final int FIRST_CARD = 0;

	private final Participant dealer;
	private final List<Participant> players;
	private final Map<Participant, Boolean> result;

	public BlackJackDto(Participant dealer, List<Participant> players, Map<Participant, Boolean> result) {
		this.dealer = dealer;
		this.players = players;
		this.result = result;
	}

	public static BlackJackDto from(BlackJack blackJack) {
		return new BlackJackDto(blackJack.getDealer(), blackJack.getPlayers(), blackJack.getResult());
	}

	public String getDealerOpenCard() {
		return dealer.getName() + NAME_DELIMITER + dealer.getCards().get(FIRST_CARD).getName();
	}

	public String getPlayerCardStatus(Participant participant) {
		String[] playerCardStatus = participant.getCards().stream()
			.map(Card::getName)
			.toArray(String[]::new);

		return participant.getName() + NAME_DELIMITER + String.join(CARD_DELIMITER, playerCardStatus);
	}

	public String getDealerResult() {
		long loseCount = result.values().stream().filter(value -> value).count();
		long winCount = result.size() - loseCount;

		return dealer.getName() + NAME_DELIMITER + winCount + MESSAGE_WIN + loseCount + MESSAGE_LOSE;
	}

	public List<String> getPlayersResult() {
		return result.keySet().stream()
			.map(key -> key.getName() + NAME_DELIMITER + decodeResult(result.get(key)))
			.collect(Collectors.toList());
	}

	private String decodeResult(Boolean isWin) {
		if (isWin) {
			return MESSAGE_WIN;
		}
		return MESSAGE_LOSE;
	}

	public Participant getDealer() {
		return dealer;
	}

	public List<Participant> getPlayers() {
		return players;
	}
}
