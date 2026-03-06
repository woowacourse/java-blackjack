package util;

public class RandomRankNumberGenerator implements NumberGenerator{
    @Override
    public int generate() {
        return (int) (Math.random() * 13) + 1;
    }
}
