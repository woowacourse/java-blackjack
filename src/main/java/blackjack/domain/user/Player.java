package blackjack.domain.user;

import blackjack.domain.card.UserDeck;
import blackjack.domain.money.Money;
import blackjack.domain.state.StateFactory;

public class Player extends User {

    private final String name;

    public Player(String name, UserDeck userDeck, Money money) {
        super(StateFactory.draw(userDeck, UserDeck.BLACK_JACK_NUMBER), money);
        this.name = name;
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
