package view;

import java.util.stream.Collectors;

import domain.GameResult;
import domain.card.Card;
import domain.card.Hands;
import domain.gamer.Gamer;
import domain.gamer.Name;
import view.dto.GamerDto;
import view.dto.PlayersDto;

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

	public static void printInputBettingMoney(Name name) {
		System.out.println(String.format("%s의 베팅 금액은?", name.getName()));
	}

	public static void printInitial(PlayersDto playersDto, GamerDto dealerDto) {
		printInitialDraw(playersDto);
		printInitialCards(playersDto, dealerDto);
	}

	public static void printInitialDraw(PlayersDto playersDto) {
		String stringBuilder = "딜러와 "
			+ playersDto.getPlayers().stream()
			.map(GamerDto::getName)
			.collect(Collectors.joining(", "))
			+ "에게 2장을 나누었습니다.";
		System.out.println(stringBuilder);
	}

	public static void printInitialCards(PlayersDto playersDto, GamerDto dealerDto) {
		System.out.println(showDealerInitialCard(dealerDto));
		for (GamerDto player : playersDto.getPlayers()) {
			System.out.println(showCards(player));
		}
	}

	private static String showDealerInitialCard(GamerDto dealer) {
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

	public static void printCards(GamerDto gamerDto) {
		System.out.println(showCards(gamerDto));
	}

	public static void printDealerDraw() {
		System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
	}

	public static void printResult(PlayersDto playersDto, GamerDto dealerDto) {
		System.out.println(NEW_LINE + "##최종 점수");
		System.out.println(showCards(dealerDto) + " - " + dealerDto.getTotalScore());
		for (GamerDto playerDto : playersDto.getPlayers()) {
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

	public static void printMatchResult(GameResult gameResult) {
		System.out.println(NEW_LINE + "## 최종 수익");
		for (Gamer gamer : gameResult.getGameResult().keySet()) {
			System.out.println(String.format("%s: %d", gamer.getName(), gameResult.getGameResult()
				.get(gamer)
				.intValue()));
		}
	}
}
