package dto;

import java.util.List;
import java.util.stream.Collectors;

import domain.BlackjackGame;
import domain.card.Card;
import domain.card.Deck;
import domain.gamer.Dealer;
import domain.gamer.Player;

/**
 *   class 블랙잭 게임 DTO입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class BlackjackGameDto {
	private List<Player> players;
	private Dealer dealer;
	private Deck deck;

	private BlackjackGameDto(List<Player> players, Dealer dealer, Deck deck) {
		this.players = players;
		this.dealer = dealer;
		this.deck = deck;
	}

	public static BlackjackGameDto from(BlackjackGame blackjackGame) {
		return new BlackjackGameDto(blackjackGame.getPlayers(), blackjackGame.getDealer(), blackjackGame.getDeck());
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Dealer getDealer() {
		return dealer;
	}

	public Deck getDeck() {
		return deck;
	}

	public String showDealerInitialCard() {
		return "딜러: "
			+ "HIDDEN, "
			+ dealer.getHands()
			.getCards()
			.get(1)
			.shape();
	}

	public String showCards(Player player) {
		return player.getName()
			+ ": "
			+ player.getHands().getCards().stream()
			.map(Card::shape)
			.collect(Collectors.joining(", "));
	}
}
