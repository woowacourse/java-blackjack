package controller;

import domain.PlayerIntentionType;
import domain.Score;
import domain.card.Cards;
import domain.card.Deck;
import domain.user.Dealer;
import domain.user.Player;
import domain.user.Players;
import service.BlackjackService;

import static view.InputView.*;
import static view.OutputView.*;

public class BlackjackController {
	public static void run(Deck deck, Dealer dealer, Players players) {
		BlackjackService.giveInitialCards(deck, dealer, players);
		printInitialDistribution(players);
		printInitialStatus(dealer.openCard(), players);
		if (isBlackjack(dealer)) {
			printResultStatus(dealer.openAllCards(), players);
			printResult(BlackjackService.createResultWhenDealerBlackjack(players), players);
			return;
		}

		players.forEach(player -> {
			while (canDrawMore(player) && wantDraw(player)) {
				BlackjackService.addCard(player, deck);
				printCardsStatusOf(player);
			}
		});

		while (hasToDraw(dealer)) {
			BlackjackService.addCard(dealer, deck);
			printDealerDraw();
		}

		printResultStatus(dealer.openAllCards(), players);
		printResult(BlackjackService.createResult(dealer, players), players);
	}

	private static boolean hasToDraw(Dealer dealer) {
		Cards dealerCards = dealer.openAllCards();
		return Score.of(dealerCards).canDealerDraw();
	}

	private static boolean wantDraw(Player player) {
		return PlayerIntentionType.of(inputPlayerIntention(player)).equals(PlayerIntentionType.YES);
	}

	private static boolean canDrawMore(Player player) {
		Cards playerCards = player.openAllCards();
		return Score.of(playerCards).isNotBurst();
	}

	private static boolean isBlackjack(Dealer dealer) {
		Cards dealerCards = dealer.openAllCards();
		return dealerCards.hasInitialSize() && Score.of(dealerCards).isBlackjackScore();
	}
}
