package blackjack.domain.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ParticipantCards {
	private static final int MAXIMUM_TO_ACE_IS_ELEVEN = 11;
	private static final int MAKING_ACE_ELEVEN = 10;

	private final List<Card> participantCards;

	public ParticipantCards() {
		this.participantCards = new ArrayList<>();
	}

	public void addCard(Card card) {
		participantCards.add(card);
	}

	public int calculateJudgingPoint() {
		return participantCards.stream()
			.map(Card::getNumber)
			.reduce(0, Integer::sum);
	}

	public int calculateIncludeAce() {
		int point = calculateJudgingPoint();
		if (point <= MAXIMUM_TO_ACE_IS_ELEVEN && havingAce()) {
			point += MAKING_ACE_ELEVEN;
		}
		return point;
	}

	private boolean havingAce() {
		return participantCards.stream()
			.anyMatch(Card::isAce);
	}

	public Stream<Card> getCardStream() {
		return participantCards.stream();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		ParticipantCards participantCards1 = (ParticipantCards)o;
		return Objects.equals(participantCards, participantCards1.participantCards);
	}

	@Override
	public int hashCode() {
		return Objects.hash(participantCards);
	}
}
