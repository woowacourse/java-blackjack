package domain.gamer;

import domain.card.Deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Gamers {
	private static final int FIRST_GIVE_CARD_COUNT = 2;

	private final List<Gamer> gamers;

	public Gamers(List<Player> players, Dealer dealer) {
		this.gamers = of(players, dealer);
	}

	private static List<Gamer> of(List<Player> players, Dealer dealer) {
		List<Gamer> gamers = new ArrayList<>();
		gamers.add(dealer);
		gamers.addAll(players);

		return gamers;
	}

	public void giveTwoCardToAll(Deck deck) {
		giveCardToAll(deck, FIRST_GIVE_CARD_COUNT);
	}

	public void giveCardToAll(Deck deck, int count) {
		for (int i = 0; i < count; i++) {
			giveCardToAll(deck);
		}
	}

	private void giveCardToAll(Deck deck) {
		for (Gamer gamer : gamers) {
			gamer.hit(deck.drawCard());
		}
	}

	public List<Player> getPlayers() {
		return findPlayers(gamers);
	}

	public static List<Player> findPlayers(List<Gamer> gamers) {
		return gamers.stream()
				.filter(gamer -> gamer.getClass() == Player.class)
				.map(player -> (Player) player)
				.collect(Collectors.toUnmodifiableList());
	}

	public Dealer getDealer() {
		return findDealer(gamers);
	}

	public static Dealer findDealer(List<Gamer> gamers) {
		return gamers.stream()
				.filter(gamer -> gamer.getClass() == Dealer.class)
				.map(dealer -> (Dealer) dealer)
				.findFirst()
				.orElseThrow(AssertionError::new);
	}

	public List<Gamer> getGamers() {
		return Collections.unmodifiableList(gamers);
	}
}
