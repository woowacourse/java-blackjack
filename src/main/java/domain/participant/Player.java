package domain.participant;

import domain.BettingMoney;
import domain.Name;
import domain.PlayerStatus;
import domain.card.Card;
import domain.constant.Result;
import java.util.List;

public class Player extends Participant {
    private final PlayerStatus status;

    public Player(Name name, BettingMoney bettingMoney) {
        super(name);
        this.status = new PlayerStatus(bettingMoney);
    }

    @Override
    public void addCard(Card card) {
        super.addCard(card);
        updateNaturalBlackJackStatus();
    }

    private void updateNaturalBlackJackStatus() {
        if (isInitialBlackJack()) {
            status.markNaturalBlackJack();
        }
    }

    private boolean isInitialBlackJack() {
        return hasTwoCards() && isBlackJack();
    }

    public boolean isNaturalBlackJack() {
        return status.isNaturalBlackJack();
    }

    public double calculateProceeds(Result result) {
        return status.calculateProceeds(result);
    }

    @Override
    public boolean canDraw() {
        return !(isBust() || isNaturalBlackJack());
    }

    @Override
    public List<String> getInitialOpenCards(){
        return getHandToString();
    }
}