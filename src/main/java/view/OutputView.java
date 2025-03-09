package view;

import java.util.List;
import java.util.Map;

public class OutputView {
	private static final String PLAYER_NAME_DELIMITER = ", ";

	public void printHandOutIntroduce(final List<String> playerNames) {
		final String names = String.join(PLAYER_NAME_DELIMITER, playerNames);
		System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), names);
	}

	public void printDealerHandOutResult(final String dealerCard) {
		System.out.printf("딜러카드: %s" + System.lineSeparator(), dealerCard);
	}

	public void printPlayersCard(final Map<String, List<String>> cardsByPlayerName) {
		cardsByPlayerName.forEach(this::printPlayerCards);
	}

	public void printPlayerCards(final String name, final List<String> card) {
		final String cards = String.join(PLAYER_NAME_DELIMITER, card);
		System.out.printf("%s카드: %s" + System.lineSeparator(), name, cards);
	}

	public void printDealerPickCard() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public void printDealerHandResult(final List<String> cards, final int score) {
		final String joinedCards = String.join(PLAYER_NAME_DELIMITER, cards);
		System.out.printf("딜러카드: %s - 결과: %d" + System.lineSeparator(), joinedCards, score);
	}

	public void printPlayerHandResult(final String name, final List<String> cards, final int score) {
		final String joinedCards = String.join(PLAYER_NAME_DELIMITER, cards);
		System.out.printf("%s카드: %s - 결과: %d" + System.lineSeparator(), name, joinedCards, score);
	}

	public void printBlackjackDuelResultIntroduce() {
		System.out.println("## 최종 승패");
	}

	public void printBlackjackDealerDuelResult(final int winCount, final int loseCount) {
		System.out.printf("딜러: %d승, %d패" + System.lineSeparator(), winCount, loseCount);
	}

	public void printBlackjackPlayerDuelResult(final String name, final boolean isWin) {
		System.out.printf("%s: ", name);
		if (isWin) {
			System.out.println("승");
			return;
		}
		System.out.println("패");
	}
}
