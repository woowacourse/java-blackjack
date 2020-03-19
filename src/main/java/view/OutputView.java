package view;

import java.util.List;
import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Hands;
import domain.gamer.PlayerGameResult;
import view.dto.BlackjackGameDto;
import view.dto.DealerDto;
import view.dto.GamerDto;
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
		String stringBuilder = "딜러와 "
			+ blackjackGameDto.getPlayers().stream()
			.map(PlayerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
		System.out.println(stringBuilder);
	}

	public static void printInitialCards(BlackjackGameDto blackjackGameDto) {
		System.out.println(showDealerInitialCard(blackjackGameDto.getDealer()));
		for (PlayerDto player : blackjackGameDto.getPlayers()) {
			System.out.println(showCards(player));
		}
	}

	private static String showDealerInitialCard(DealerDto dealer) {
		Hands hands = dealer.getHands();
		return dealer.getName()
			+ ": "
			+ "HIDDEN, "
			+ hands.getCards()
			.get(1)
			.shape();
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

	public static void printResult(BlackjackGameDto blackjackGameDto) {
		DealerDto dealerDto = blackjackGameDto.getDealer();
		List<PlayerDto> playersDto = blackjackGameDto.getPlayers();

		System.out.println(showCards(dealerDto) + " - " + dealerDto.getTotalScore());
		for (PlayerDto playerDto : playersDto) {
			System.out.println(showCards(playerDto) + " - " + playerDto.getTotalScore());
		}
	}

	private static String showCards(GamerDto gamerDto) {
		Hands hands = gamerDto.getHands();
		return gamerDto.getName()
			+ ": "
			+ hands.getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}

	public static void printMatchResult(BlackjackGameDto blackjackGameDto) {
		System.out.println("## 최종 승패");
		List<PlayerDto> players = blackjackGameDto.getPlayers();
		DealerDto dealerDto = blackjackGameDto.getDealer();

		System.out.println(dealerDto.getName() + " : " + dealerDto.getGameResult().getWin() + "승 "
			+ dealerDto.getGameResult().getLose() + "패 " + dealerDto.getGameResult().getDraw() + "무");
		for (PlayerDto playerDto : players) {
			PlayerGameResult playerGameResult = playerDto.getPlayerGameResult();
			System.out.println(playerDto.getName() + ": " + playerGameResult.getResult());
		}
	}
}
