package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.game.BlackJackGame;
import blackjack.domain.card.Card;
import blackjack.dto.GameResultDto;
import blackjack.dto.GamerDto;

public class OutputView {

	public static void printGamers(GamerDto dealer, List<GamerDto> players) {
		String names = players.stream()
			.map(GamerDto::getName)
			.collect(Collectors.joining(", "));
		System.out.printf("%s와 %s에게 %d장을 나누었습니다.\n",
			dealer.getName(), names, BlackJackGame.INIT_DISTRIBUTION_COUNT);
	}

	public static void checkHoldingCards(String name, List<Card> cards) {
		System.out.println(name + "카드: " + getCardNames(cards));
	}

	public static void printAdditionalDrawDealer(int count) {
		System.out.println();
		if (count != 0) {
			System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", count);
			return;
		}
		System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n");
	}

	public static void printFinalCards(GamerDto dealer, List<GamerDto> players) {
		printDealerFinalCards(dealer);
		printPlayerFinalCards(players);
	}

	private static void printDealerFinalCards(GamerDto dealer) {
		System.out.printf("%s카드: %s- 결과: %d\n",
			dealer.getName(), getCardNames(dealer.getCards()), dealer.getCardNumberSum());
	}

	private static void printPlayerFinalCards(List<GamerDto> players) {
		for (GamerDto player : players) {
			System.out.printf("%s카드: %s- 결과: %d\n",
				player.getName(), getCardNames(player.getCards()), player.getCardNumberSum());
		}
		System.out.println();
	}

	private static String getCardNames(List<Card> cards) {
		return cards.stream()
			.map(card -> card.getName() + card.getSymbol())
			.collect(Collectors.joining(", "));
	}

	public static void printFinalResult(GameResultDto gameResultDto) {
		System.out.println("## 최종 수익");
		System.out.println("딜러: " + gameResultDto.getDealerEarning());
		gameResultDto.getPlayerEarnings()
			.forEach((name, earning) -> System.out.printf("%s: %s\n", name, earning));
	}
}
