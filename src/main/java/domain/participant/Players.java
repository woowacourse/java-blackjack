package domain.participant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import domain.result.EarningRate;

public class Players implements Iterable<Player> {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
	}

	public List<String> showNames() {
		return new ArrayList<>(players.stream().map(Player::showName).collect(Collectors.toList()));
	}

	public LinkedHashMap<Participant, EarningRate> getResult(Dealer other) {
		LinkedHashMap<Participant, EarningRate> map = new LinkedHashMap<>();
		players.forEach(player -> map.put(player, player.getResult(other)));
		return map;
	}

	private class PlayerIterator implements Iterator<Player> {
		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < players.size();
		}

		@Override
		public Player next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return players.get(current++);
		}
	}

	@Override
	public Iterator<Player> iterator() {
		return new PlayerIterator();
	}

	@Override
	public void forEach(Consumer<? super Player> action) {
		Iterable.super.forEach(action);
	}
}
