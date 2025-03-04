import domain.NumberGenerator;

public class TestNumberGenerator implements NumberGenerator {
    @Override
    public int pickRangeOfNumber(int min, int max) {
        return max;
    }
}
