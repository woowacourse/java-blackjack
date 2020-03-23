package view;

import java.util.stream.Collectors;

import domain.GameResult;
import domain.card.Card;
import domain.gamer.Gamer;
import view.dto.BlackjackGameDto;
import view.dto.GamerDto;

/**
 *   class outputView입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class OutputView {
	private static final String NEW_LINE = System.lineSeparator();

	public static void printErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public static void printInitial(BlackjackGameDto blackjackGame) {
		printInitialDraw(blackjackGame);
		printInitialCards(blackjackGame);
	}

	public static void printInitialDraw(BlackjackGameDto blackjackGame) {
		String string = "딜러와 "
			+ blackjackGame.getPlayers().stream()
			.map(GamerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
		System.out.println(string);
	}

	public static void printInitialCards(BlackjackGameDto blackjackGame) {
		System.out.println(showDealerInitialCard(blackjackGame.getDealer()));
		for (GamerDto player : blackjackGame.getPlayers()) {
			System.out.println(showCards(player));
		}
	}

	private static String showDealerInitialCard(GamerDto dealer) {
		return dealer.getName()
			+ ": "
			+ "HIDDEN, "
			+ dealer.getHands().getCards()
			.get(1)
			.shape();
	}

	private static String showCards(GamerDto gamer) {
		return gamer.getName()
			+ ": "
			+ gamer.getHands().getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public static void printDealerBlackjack() {
		System.out.println("딜러가 블랙잭입니다.");
	}

	public static void printCards(GamerDto gamer) {
		System.out.println(showCards(gamer));
	}

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public static void printGameResult(GameResult gameResult) {
		System.out.println(NEW_LINE + "## 최종 수익");
		for (Gamer gamer : gameResult.getGameResult().keySet()) {
			System.out.println(
				String.format("%s: %d원", gamer.getName(), gameResult.getGameResult().get(gamer).intValue()));
		}
	}

	public static void printCardsResult(BlackjackGameDto blackjackGame) {
		GamerDto dealer = blackjackGame.getDealer();

		System.out.println(String.format(NEW_LINE + "%s - 결과: %d", showCards(dealer), dealer.getScore()));
		for (GamerDto playerDto : blackjackGame.getPlayers()) {
			System.out.println(String.format("%s - 결과: %d", showCards(playerDto), playerDto.getScore()));
		}
	}
}
