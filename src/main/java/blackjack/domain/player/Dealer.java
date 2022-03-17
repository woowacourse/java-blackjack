package blackjack.domain.player;

import blackjack.domain.BetMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.ArrayList;
import java.util.List;

public class Dealer extends Player {

    public static final String DEALER_NAME = "딜러";
    private static final int DEALER_RECEIVE_STANDARD = 16;
    private static final int DEALER_OPEN_CARD_SIZE = 1;

    private final String name = DEALER_NAME;
    private final BetMoney betMoney;

    public Dealer() {
        super(new Cards());
        this.betMoney = new BetMoney(0);
    }

    @Override
    public List<Card> openCards() {
        return new ArrayList<>(cards.getCards().subList(0, DEALER_OPEN_CARD_SIZE));
    }

    @Override
    public boolean isReceivable() {
        return calculateResult() <= DEALER_RECEIVE_STANDARD;
    }

    public int betMoney() {
        return betMoney.getBetMoney();
    }
}
