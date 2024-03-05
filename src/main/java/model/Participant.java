package model;

public class Participant extends Player implements NoticeStatus {

    private final String name;

    public Participant(String name) {
        this.name = name;
    }

    @Override
    public boolean notice() {
        if(sumCardNumbers() <= MAXIMUM_SUM) {
            return true;
        }
        return false;
    }
}
