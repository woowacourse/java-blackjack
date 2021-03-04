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
        return !super.isBurstCondition() && super.getPoint() < UserDeck.BLACK_JACK_NUMBER;
    }

    public OneGameResult betResult(Dealer dealer) {
        if (super.isBurstCondition() && dealer.isBurstCondition()) {
            return OneGameResult.LOSE;
        }
        if (super.getPoint() < dealer.getPoint()) {
            return OneGameResult.LOSE;
        }
        if (super.getPoint() > dealer.getPoint()) {
            return OneGameResult.WIN;
        }
        return OneGameResult.TIE;
    }

    public String getName() {
        return name;
    }
}
