package domain.participant;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class Players implements Iterable<Player> {
	private final List<Player> players;

	public Players(List<Player> players) {
		this.players = players;
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
