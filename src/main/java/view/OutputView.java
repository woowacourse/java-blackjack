package view;

import java.util.List;
import java.util.stream.Collectors;

import dto.BlackjackGameDto;
import dto.DealerDto;
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
		String stringBuilder = "딜러와 "
			+ blackjackGameDto.getPlayers().stream()
			.map(PlayerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
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

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public static void printResult(BlackjackGameDto blackjackGameDto) {
		DealerDto dealerDto = blackjackGameDto.getDealer();
		List<PlayerDto> playersDto = blackjackGameDto.getPlayers();

		System.out.println(dealerDto.showCards() + " - " + dealerDto.getHands().calculateTotalScore());
		for (PlayerDto player : playersDto) {
			System.out.println(player.showCards() + " - " + player.getHands().calculateTotalScore());
		}
	}
}
