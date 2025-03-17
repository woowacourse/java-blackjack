package view;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import card.Card;
import game.Blackjack;
import game.GameScore;
import money.Money;
import money.WagerMoney;
import paticipant.Dealer;
import paticipant.Player;

public class OutputView {
	private static final String PLAYER_NAME_DELIMITER = ", ";

	public void printHandOut(final List<Player> players, final Dealer dealer) {
		printHandOutIntroduce(players);
		printDealerHandOutResult(dealer);
		printPlayersCard(players);
	}

	private void printHandOutIntroduce(final List<Player> players) {
		final String playerNames = players
			.stream()
			.map(Player::getName)
			.collect(Collectors.joining(PLAYER_NAME_DELIMITER));
		System.out.printf("딜러와 %s에게 2장을 나누었습니다." + System.lineSeparator(), playerNames);
	}

	private void printDealerHandOutResult(final Dealer dealer) {
		System.out.printf("딜러카드: %s" + System.lineSeparator(), convertCardToString(getDealerFirstCard(dealer)));
	}

	private Card getDealerFirstCard(final Dealer dealer) {
		return dealer.getParticipant().getCardHand().getCards().getFirst();
	}

	private void printPlayersCard(final List<Player> players) {
		for (final Player player : players) {
			printPlayerPickCard(player);
		}
	}

	public void printPlayerPickCard(final Player player) {
		System.out.printf("%s카드: %s" + System.lineSeparator(), player.getName(),
			convertCardsToString(getPlayerCards(player)));
	}

	private List<Card> getPlayerCards(final Player player) {
		return player.getParticipant().getCardHand().getCards();
	}

	public void printDealerPickCard(final Dealer dealer) {
		final int pickCardCount = getDealerCards(dealer).size() - Blackjack.INIT_PICK_CARD_COUNT;
		for (int i = 0; i < pickCardCount; i++) {
			System.out.println("딜러는 16이하라 한 장의 카드를 더 받았습니다.");
		}
	}

	public void printDealerHandResult(final Dealer dealer) {
		System.out.printf("딜러카드: %s - 결과: %d" + System.lineSeparator(),
			convertCardsToString(getDealerCards(dealer)),
			getDealerScore(dealer).value()
		);
	}

	private List<Card> getDealerCards(final Dealer dealer) {
		return dealer.getParticipant().getCardHand().getCards();
	}

	private GameScore getDealerScore(final Dealer dealer) {
		return dealer.getParticipant().calculateAllScore();
	}

	public void printPlayerHandResult(final List<Player> players) {
		for (final Player player : players) {
			System.out.printf("%s카드: %s - 결과: %d" + System.lineSeparator(), player.getName(),
				convertCardsToString(getPlayerCards(player)),
				getPlayerScore(player).value());
		}
	}

	private GameScore getPlayerScore(final Player player) {
		return player.getParticipant().calculateAllScore();
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
