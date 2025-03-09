package domain;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }

    @Override
    public void openInitialCards() {
        openCards(2);
    }

    public boolean isDealer() {
        return false;
    }
}
