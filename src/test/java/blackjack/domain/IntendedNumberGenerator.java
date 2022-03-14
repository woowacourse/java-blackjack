package blackjack.domain;

import java.util.List;
import java.util.Objects;

import blackjack.domain.strategy.NumberGenerator;

public class IntendedNumberGenerator implements NumberGenerator {

    private static final String INTENDED_NUMBER_GENERATOR_OUT_OF_BOUND_EXCEPTION = "[ERROR] 숫자 생성기 인덱스 초과";
    private int index = 0;
    private List<Integer> numbers;

    public IntendedNumberGenerator(List<Integer> numbers) {
        Objects.nonNull(numbers);
        this.numbers = numbers;
    }

    @Override
    public int generateNumber() {
        try {
            return numbers.get(index++);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new IllegalArgumentException(INTENDED_NUMBER_GENERATOR_OUT_OF_BOUND_EXCEPTION);
        }
    }
}
