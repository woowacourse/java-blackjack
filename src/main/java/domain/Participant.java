package domain;

public class Participant extends Player {
    public Participant(String name) {
        super(name);
    }

    @Override
    public void openInitialCards() {
        openCards(2);
    }

    @Override
    public boolean isDealer() {
        return false;
    }
}
