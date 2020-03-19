package view;

import java.util.stream.Collectors;

import domain.card.Card;
import domain.card.Hands;
import view.dto.DealerDto;
import view.dto.GamerDto;
import view.dto.PlayerDto;
import view.dto.PlayersDto;

/**
 *   class outputView입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class OutputView {
	public static void printErrorMessage(IllegalArgumentException e) {
		System.out.println(e.getMessage());
	}

	public static void printInitial(PlayersDto playersDto, DealerDto dealerDto) {
		printInitialDraw(playersDto);
		printInitialCards(playersDto, dealerDto);
	}

	public static void printInitialDraw(PlayersDto playersDto) {
		String stringBuilder = "딜러와 "
			+ playersDto.getPlayers().stream()
			.map(PlayerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
		System.out.println(stringBuilder);
	}

	public static void printInitialCards(PlayersDto playersDto, DealerDto dealerDto) {
		System.out.println(showDealerInitialCard(dealerDto));
		for (PlayerDto player : playersDto.getPlayers()) {
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

	public static void printResult(PlayersDto playersDto, DealerDto dealerDto) {
		System.out.println(showCards(dealerDto) + " - " + dealerDto.getTotalScore());
		for (PlayerDto playerDto : playersDto.getPlayers()) {
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

	public static void printMatchResult(PlayersDto playersDto, DealerDto dealerDto) {
		System.out.println("## 최종 승패");
	}
}
