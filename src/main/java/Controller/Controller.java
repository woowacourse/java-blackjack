package Controller;

import static view.InputView.*;
import static view.OutputView.*;

import java.util.List;

import domain.BlackJack;
import domain.card.Deck;
import domain.Player;
import domain.strategy.RandomShuffleStrategy;
import domain.Users;

public class Controller {

	public void run() {
		BlackJack blackJack = ready();
		play(blackJack);
		end(blackJack);
	}

	private BlackJack ready() {
		List<String> playerNames = askPlayerNames();
		Deck deck = new Deck(new RandomShuffleStrategy());
		Users players = Users.from(playerNames, deck);
		bet(players.getPlayers());
		BlackJack blackJack = new BlackJack(players, deck);
		printInitMessage(players);
		printDealerCardHidden(blackJack.getDealerCardHidden());
		printPlayerCards(blackJack.getPlayerToCard());
		return blackJack;
	}

	private void bet(List<Player> players) {
		for(Player player : players){
			player.setBet(askPlayerBet(player.getName()));
		}
	}

	private void play(final BlackJack blackJack) {
		blank();
		for (Player player : blackJack.getPlayers()) {
			askPlayerHit(player, blackJack);
		}
		giveCardToDealer(blackJack);
	}

	private void askPlayerHit(final Player player, final BlackJack blackJack) {
		while (player.isHittable() && askIfHit(player)) {
			blackJack.giveCard(player);
			printEachPlayerCards(player.getName(), player.getCardNames());
		}
	}

	private void giveCardToDealer(final BlackJack blackJack) {
		while (blackJack.isDealerHittable()) {
			blackJack.giveCardToDealer();
			printDealerHitMessage();
		}
	}

	private void end(final BlackJack blackJack) {
		printDealerCards(blackJack.getDealerCards(), blackJack.getDealerScore());
		printPlayerCards(blackJack.getPlayerToCard(), blackJack.getPlayerToScore());
		printGameResult(blackJack.calculateDealerResult(), blackJack.calculatePlayerResults());
	}
}
