package blackjack.domain.entry;

import blackjack.domain.PlayerName;
import blackjack.domain.result.PlayerOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.HoldCards;

import java.util.List;

import static blackjack.domain.BlackjackGame.BLACKJACK_NUMBER;

public class Player extends Participant {
    private final PlayerName name;
    private int bettingMoney;

    public Player(PlayerName name, int bettingMoney, HoldCards holdCards) {
        super(holdCards);
        this.bettingMoney = bettingMoney;
        this.name = name;
    }

    public PlayerOutcome match(Dealer dealer) {
        return PlayerOutcome.match(
                dealer.calculateCardsSum(),
                calculateCardsSum(),
                dealer.countCardSize(),
                countCardSize()
        );
    }

    public int calculateBettingMoney(PlayerOutcome playerOutcome) {
        return (int) (bettingMoney * playerOutcome.getDividendRate());
    }

    @Override
    public String getName() {
        return name.getValue();
    }

    @Override
    public List<Card> openCard() {
        HoldCards holdCards = getHoldCards();
        return List.copyOf(holdCards.getCards());
    }

    @Override
    public boolean canHit() {
        return calculateCardsSum() < BLACKJACK_NUMBER;
    }
}
