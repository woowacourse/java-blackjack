package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Results implements Iterable<Result> {
	private final List<Result> results;

	public Results(List<Result> results) {
		this.results = Collections.unmodifiableList(new ArrayList<>(results));
	}

	@Override
	public Iterator<Result> iterator() {
		return results.iterator();
	}
}
