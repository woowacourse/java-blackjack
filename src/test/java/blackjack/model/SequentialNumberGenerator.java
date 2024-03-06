package blackjack.model;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class SequentialNumberGenerator implements NumberGenerator {
    private final Iterator<Integer> numbers;

    public SequentialNumberGenerator(final List<Integer> numbers) {
        this.numbers = numbers.iterator();
    }

    @Override
    public int pick() {
        if (numbers.hasNext()) {
            return numbers.next();
        }
        throw new NoSuchElementException("이미 모든 numbers가 반환되었습니다.");
    }
}
