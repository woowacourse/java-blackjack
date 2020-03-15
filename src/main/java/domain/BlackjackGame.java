package domain;

import java.util.List;

import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Player;
import domain.gamer.Players;

/**
 *    블랙잭 게임 클래스입니다.
 *
 *    @author AnHyungJu, ParkDooWon
 */
public class BlackjackGame {
	private static final int INITIAL_DRAW_NUMBER = 2;

	private Players players;
	private Dealer dealer;
	private Deck deck;

	public BlackjackGame(String[] playersName) {
		this.players = new Players(playersName);
		this.dealer = new Dealer();
		this.deck = new Deck();
	}

	public void initialDraw() {
		for (int i = 0; i < INITIAL_DRAW_NUMBER; i++) {
			draw(dealer);
			players.draw(deck);
		}
	}

	public boolean isDealerBlackjack() {
		return dealer.isBlackjack();
	}

	public void draw(Gamer gamer) {
		gamer.draw(deck.deal());
	}

	public List<Player> getPlayers() {
		return players.getPlayers();
	}

	public Dealer getDealer() {
		return dealer;
	}
}
