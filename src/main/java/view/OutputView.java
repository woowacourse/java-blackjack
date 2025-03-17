package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import card.Card;
import game.Blackjack;
import money.Money;
import money.WagerMoney;
import paticipant.Player;

public class OutputView {
	private static final String PLAYER_NAME_DELIMITER = ", ";

	public void printHandOut(final Blackjack blackjack) {
		printHandOutIntroduce(blackjack);
		printDealerHandOutResult(blackjack);
		printPlayersCard(blackjack);
	}

	private void printHandOutIntroduce(final Blackjack blackjack) {
		final String playerNames = blackjack.getPlayerList()
			.stream()
			.map(Player::getName)
			.collect(Collectors.joining(PLAYER_NAME_DELIMITER));
		System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), playerNames);
	}

	private void printDealerHandOutResult(final Blackjack blackjack) {
		System.out.printf("딜러카드: %s" + System.lineSeparator(), convertCardToString(blackjack.getDealerFirstCard()));
	}

	private void printPlayersCard(final Blackjack blackjack) {
		for (final Player player : blackjack.getPlayerList()) {
			printPlayerPickCard(player);
		}
	}

	public void printPlayerPickCard(final Player player) {
		System.out.printf("%s카드: %s" + System.lineSeparator(), player.getName(),
			convertCardsToString(player.getCards()));
	}

	public void printDealerPickCard(final Blackjack blackjack) {
		final int pickCardCount = blackjack.getDealerCards().size() - Blackjack.INIT_PICK_CARD_COUNT;
		for (int i = 0; i < pickCardCount; i++) {
			System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
		}
	}

	public void printDealerHandResult(final Blackjack blackjack) {
		System.out.printf("딜러카드: %s - 결과: %d" + System.lineSeparator(),
			convertCardsToString(blackjack.getDealerCards()),
			blackjack.getDealerScore().value()
		);
	}

	public void printPlayerHandResult(final Blackjack blackjack) {
		for (final Player player : blackjack.getPlayerList()) {
			System.out.printf("%s카드: %s - 결과: %d" + System.lineSeparator(), player.getName(),
				convertCardsToString(player.getCards()),
				player.calculateAllScore().value());
		}
	}

	public void printWagerResult(final WagerMoney wagerMoney) {
		final Map<Player, Money> playerWagerMoney = wagerMoney.calculateWagerResult();
		System.out.println("## 최종 수익");
		System.out.printf("딜러: %d" + System.lineSeparator(), calculateDealerWagerResult(playerWagerMoney).getValue());
		for (final Map.Entry<Player, Money> playerMoney : playerWagerMoney.entrySet()) {
			System.out.printf("%s: %d" + System.lineSeparator(), playerMoney.getKey().getName(),
				playerMoney.getValue().getValue());
		}
	}

	private String convertCardsToString(final List<Card> cards) {
		return cards.stream()
			.map(this::convertCardToString)
			.collect(Collectors.joining(PLAYER_NAME_DELIMITER));
	}

	private String convertCardToString(final Card card) {
		return RankMessage.convertRankToMessage(card.rank()) + card.suit().getName();
	}

	private Money calculateDealerWagerResult(final Map<Player, Money> playerWagerResult) {
		Money dealerMoney = new Money(0);
		for (final Money playerMoney : playerWagerResult.values()) {
			dealerMoney = dealerMoney.minus(playerMoney);
		}
		return dealerMoney;
	}
}
