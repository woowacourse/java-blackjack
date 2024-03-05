package model;

public class Dealer extends Player implements NoticeStatus {

    private static final int NUMBER_THRESHOLD = 16;

    @Override
    public boolean notice() {
        return sumCardNumbers() <= NUMBER_THRESHOLD;
    }
}
