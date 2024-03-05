package model.player;

public class Participant extends Player implements NoticeStatus {

    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    @Override
    public boolean notice() {
        return sumCardNumbers() <= MAXIMUM_SUM;
    }
}
