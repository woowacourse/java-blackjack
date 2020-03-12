package view;

import dto.BlackjackGameDto;
import dto.PlayerDto;

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
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("딜러와 ");
		blackjackGameDto.getPlayers().forEach(player -> stringBuilder.append(player.getName())
			.append(" "));
		stringBuilder.append("에게 2장을 나누었습니다.");
		System.out.println(stringBuilder);
	}

	public static void printInitialCards(BlackjackGameDto blackjackGameDto) {
		System.out.println(blackjackGameDto.getDealer().showDealerInitialCard());
		for (PlayerDto player : blackjackGameDto.getPlayers()) {
			System.out.println(player.showCards());
		}
	}

	public static void printDealerBlackjack() {
		System.out.println("딜러가 블랙잭입니다.");
	}

	public static void printCards(PlayerDto playerDto) {
		System.out.println(playerDto.showCards());
	}
}
