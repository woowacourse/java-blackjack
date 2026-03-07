package util;

public class RandomShapeNumberGenerator implements NumberGenerator {
    @Override
    public int generate() {
        return (int) (Math.random() * 4) + 1;
    }
}
