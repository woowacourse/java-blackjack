package view;

import domain.gamer.Player;
import dto.BlackjackGameDto;

/**
 *   class outputView입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class OutputView {
	public static void printErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
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
		System.out.println(blackjackGameDto.showDealerInitialCard());
		for (Player player : blackjackGameDto.getPlayers()) {
			System.out.println(blackjackGameDto.showCards(player));
		}
	}
}
