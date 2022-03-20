package blackjack.domain;

import static java.util.stream.Collectors.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.result.BettingResult;
import blackjack.domain.result.CardResult;

public class BlackJackManager {

	private static final String NOT_EXIST_PLAYER_ERROR = "플레이어가 존재하지 않습니다.";

	private final Dealer dealer;
	private final List<Player> players;

	public BlackJackManager(Map<String, Integer> players) {
		this.dealer = new Dealer();
		this.players = players.entrySet().stream()
			.map(entry -> new Player(entry.getKey(), entry.getValue()))
			.collect(toList());
	}

	public void handOutFirst(Deck deck) {
		for (int i = 0; i < Cards.BLACKJACK_SIZE; i++) {
			handOutToAllGamers(deck);
		}
	}

	private void handOutToAllGamers(Deck deck) {
		dealer.addCard(deck.draw());
		for (Player player : players) {
			player.addCard(deck.draw());
		}
	}

	public void giveCardToDealer(Deck deck) {
		dealer.addCard(deck.draw());
	}

	public void giveCardToPlayer(String name, Deck deck) {
		Player player = findPlayerByName(name);
		player.addCard(deck.draw());
	}

	public boolean checkDealerDrawPossible() {
		return dealer.isDrawable();
	}

	public List<Card> findCardsOfPlayer(String name) {
		return findPlayerByName(name).getCards();
	}

	public Player findPlayerByName(String name) {
		return players.stream()
			.filter(player -> player.isSameName(name))
			.findAny()
			.orElseThrow(() -> new IllegalArgumentException(NOT_EXIST_PLAYER_ERROR));
	}

	public boolean checkPlayerBust(String name) {
		return !findPlayerByName(name).isDrawable();
	}

	public List<String> findPlayerNames() {
		return players.stream()
			.map(Player::getName)
			.collect(toList());
	}

	public List<Card> findDealerFirstCard() {
		return dealer.openCardFirst();
	}

	public int findDealerHitCount() {
		return dealer.findHitCount();
	}

	public String findDealerName() {
		return dealer.getName();
	}

	public CardResult createDealerResult() {
		return new CardResult(dealer);
	}

	public List<CardResult> createPlayerResults() {
		return players.stream()
			.map(CardResult::new)
			.collect(Collectors.toList());
	}

	public BettingResult createBettingResult() {
		return new BettingResult(players.stream()
			.collect(toMap(Player::getName, player -> player.match(dealer))));
	}

	public Dealer getDealer() {
		return dealer;
	}

	public List<Player> getPlayers() {
		return Collections.unmodifiableList(players);
	}
}
