package blackjack.domain.entry;

import blackjack.domain.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

public class Player extends Participant {
    private final String name;
    private int bettingMoney;

    public Player(String name, HoldCards holdCards) {
        super(holdCards);
        this.name = name;
    }

    public void initBettingMoney(int money) {
        this.bettingMoney = money;
    }

    public PlayerOutcome match(Dealer dealer) {
        return PlayerOutcome.match(dealer.countCards(), countCards());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List<Card> openCard() {
        HoldCards holdCards = getHoldCards();
        return List.copyOf(holdCards.getCards());
    }

    @Override
    public boolean canHit() {
        return countCards() < BLACKJACK_NUMBER;
    }

    public int getBettingMoney() {
        return this.bettingMoney;
    }
}
