package domain;

import domain.card.Cards;

import java.util.Arrays;
import java.util.function.Predicate;

/**
 * 클래스 이름 : .java
 *
 * @author
 * @version 1.0
 * <p>
 * 날짜 : 2020/03/11
 */
public enum ScoreType {
	BLACKJACK(ScoreType::isBlackjack),
	BURST(ScoreType::isBurst),
	NORMAL(cards -> !isBlackjack(cards) && !isBurst(cards));

	private static final int BLACKJACK_POINT = 21;
	private static final int ACE_GAP = 10;
	private static final int INITIAL_CARDS_COUNT = 2;

	private final Predicate<Cards> scoreTypeJudge;

	ScoreType(Predicate<Cards> scoreTypeJudge) {
		this.scoreTypeJudge = scoreTypeJudge;
	}

	private static boolean isBlackjack(Cards cards) {
		return cards.size() == INITIAL_CARDS_COUNT && cards.getPoint() == BLACKJACK_POINT;
	}

	private static boolean isBurst(Cards cards) {
		int point = cards.getPoint();
		int aceCount = cards.getAceCount();
		if (isOverBurst(point) && hasAce(aceCount)) {
			point -= ACE_GAP * aceCount;
		}
		return point > BLACKJACK_POINT;
	}

	private static boolean isOverBurst(int point) {
		return point > BLACKJACK_POINT;
	}

	private static boolean hasAce(int aceCount) {
		return aceCount > 0;
	}

	public static ScoreType of(Cards cards) {
		return Arrays.stream(ScoreType.values())
				.filter(scoreType -> scoreType.scoreTypeJudge.test(cards))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);
	}
}
