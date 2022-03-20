package domain.participant;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Players implements Iterable<Participant> {
	private final List<Participant> players;

	public Players(List<Participant> players) {
		this.players = players;
	}

	public List<String> showNames() {
		return new ArrayList<>(players.stream().map(Participant::getName).collect(Collectors.toList()));
	}

	private class PlayerIterator implements Iterator<Participant> {
		private int current = 0;

		@Override
		public boolean hasNext() {
			return current < players.size();
		}

		@Override
		public Participant next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			return players.get(current++);
		}
	}

	@Override
	public Iterator<Participant> iterator() {
		return new PlayerIterator();
	}

	@Override
	public void forEach(Consumer<? super Participant> action) {
		Iterable.super.forEach(action);
	}
}
