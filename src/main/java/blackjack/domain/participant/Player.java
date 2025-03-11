package blackjack.domain.participant;

import blackjack.domain.BettingMoney;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardHand;
import java.util.List;

public class Player extends Participant {
    private final ParticipantName name;
    private final BettingMoney bettingMoney;

    public Player(ParticipantName name, CardHand cardHand, BettingMoney bettingMoney) {
        super(cardHand);
        this.name = name;
        this.bettingMoney = bettingMoney;
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
