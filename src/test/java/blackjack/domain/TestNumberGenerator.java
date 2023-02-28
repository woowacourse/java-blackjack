package blackjack.domain;

import java.util.List;

public class TestNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;

    TestNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public int generate(int size) {
        return numbers.remove(0);
    }

}
