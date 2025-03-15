package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.GameResult;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Player extends Participant {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    public Player(final ParticipantName name, final CardHand cardHand, final BettingMoney bettingMoney) {
        super(cardHand);
        this.name = name;
        this.bettingMoney = bettingMoney;
    }

    public int calculateProfit(GameResult gameResult) {
        return gameResult.calculatePayout(bettingMoney);
    }

    @Override
    public boolean canHit() {
        return getScore().isUnderGoal();
    }

    @Override
    public List<Card> showStartCards() {
        List<Card> cards = cardHand.getCards();
        return cards.subList(0,2);
    }

    @Override
    public String getName() {
        return name.getValue();
    }
}
