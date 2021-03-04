package blackjack.domain.user;

import blackjack.domain.card.UserDeck;

public class Player extends User {

    private final String name;

    public Player(String name, UserDeck userDeck) {
        super(userDeck);
        this.name = name;
    }

    @Override
    public boolean isAvailableDraw() {
        return super.checkDrawRule(UserDeck.BLACK_JACK_NUMBER);
    }

    public OneGameResult betResult(Dealer dealer) {
        if (isLose(dealer)) {
            return OneGameResult.LOSE;
        }
        if (super.getPoint() > dealer.getPoint()) {
            return OneGameResult.WIN;
        }
        return OneGameResult.TIE;
    }

    private boolean isLose(Dealer dealer) {
        return (super.isBurstCondition() && dealer.isBurstCondition()) ||
            (super.getPoint() < dealer.getPoint());
    }

    public String getName() {
        return name;
    }
}
