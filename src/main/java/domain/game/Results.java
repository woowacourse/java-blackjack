package domain.game;

import java.util.Iterator;
import java.util.List;

public class Results implements Iterable<Result> {
    private final List<Result> results;

    public Results(final List<Result> results) {
        this.results = results;
    }

    @Override
    public Iterator<Result> iterator() {
        return results.iterator();
    }
}
