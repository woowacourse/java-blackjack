package view;

import java.util.stream.Collectors;

import domain.GameResult;
import domain.card.Card;
import domain.gamer.Gamer;
import view.dto.BlackjackGameDto;
import view.dto.DealerDto;
import view.dto.PlayerDto;

/**
 *   class outputView입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class OutputView {
	public static void printErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public static void printInitial(BlackjackGameDto blackjackGameDto) {
		printInitialDraw(blackjackGameDto);
		printInitialCards(blackjackGameDto);
	}

	public static void printInitialDraw(BlackjackGameDto blackjackGameDto) {
		String string = "딜러와 "
			+ blackjackGameDto.getPlayers().stream()
			.map(PlayerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
		System.out.println(string);
	}

	public static void printInitialCards(BlackjackGameDto blackjackGameDto) {
		System.out.println(showDealerInitialCard(blackjackGameDto.getDealer()));
		for (PlayerDto player : blackjackGameDto.getPlayers()) {
			System.out.println(showCards(player));
		}
	}

	private static String showDealerInitialCard(DealerDto dealerDto) {
		return dealerDto.getName()
			+ ": "
			+ "HIDDEN, "
			+ dealerDto.getHands().getCards()
			.get(1)
			.shape();
	}

	private static String showCards(PlayerDto player) {
		return player.getName()
			+ ": "
			+ player.getHands().getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public static void printDealerBlackjack() {
		System.out.println("딜러가 블랙잭입니다.");
	}

	public static void printCards(PlayerDto playerDto) {
		System.out.println(showCards(playerDto));
	}

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public static void printGameResult(GameResult gameResult) {
		System.out.println("## 최종 수익");
		for (Gamer gamer : gameResult.getGameResult().keySet()) {
			System.out.println(
				String.format("%s: %d원", gamer.getName(), gameResult.getGameResult().get(gamer).intValue()));
		}
	}
}
