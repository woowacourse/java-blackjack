package blackjack.domain.user;

public class Player extends User {

    public Player(String name) {
        super(name);
    }

    @Override
    public String showInitialCardInfo() {
        return super.showCardInfo();
    }
}
