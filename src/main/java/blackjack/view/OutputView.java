package blackjack.view;

import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.CardResult;

public class OutputView {

	public static void printGamers(String dealer, List<String> players) {
		System.out.printf("%s와 %s에게 %d장을 나누었습니다.\n",
			dealer, String.join(", ", players), Cards.BLACKJACK_SIZE);
	}

	public static void printNameAndCards(String name, List<Card> cards) {
		System.out.println(name + "카드: " + connectCardsWithName(cards));
	}

	public static void printAdditionalDrawDealer(int count) {
		System.out.println();
		if (count != 0) {
			System.out.printf("딜러는 16이하라 %d장의 카드를 더 받았습니다.\n\n", count);
			return;
		}
		System.out.println("딜러는 17이상이라 카드를 더 받지 않았습니다.\n");
	}

	public static void printFinalCards(CardResult dealer, List<CardResult> players) {
		printDealerFinalCards(dealer);
		printPlayerFinalCards(players);
	}

	private static void printDealerFinalCards(CardResult dealer) {
		System.out.printf("%s카드: %s- 결과: %d\n",
			dealer.getName(), connectCardsWithName(dealer.getCards()), dealer.getSumOfCards());
	}

	private static void printPlayerFinalCards(List<CardResult> players) {
		for (CardResult player : players) {
			System.out.printf("%s카드: %s- 결과: %d\n",
				player.getName(), connectCardsWithName(player.getCards()), player.getSumOfCards());
		}
		System.out.println();
	}

	private static String connectCardsWithName(List<Card> cards) {
		return cards.stream()
			.map(card -> card.getName() + card.getSymbol())
			.collect(Collectors.joining(", "));
	}

	public static void printFinalResult(BettingResult result) {
		System.out.println("## 최종 수익");
		System.out.println("딜러: " + result.getDealerEarning());
		result.getPlayerEarnings()
			.forEach((name, earning) -> System.out.printf("%s: %s\n", name, earning));
	}
}
