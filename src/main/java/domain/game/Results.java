package domain.game;

import java.util.Iterator;
import java.util.List;

public class Results implements Iterable<Result> {
    private List<Result> results;

    public Results(List<Result> results) {
        this.results = results;
    }

    @Override
    public Iterator<Result> iterator() {
        return results.iterator();
    }
}
