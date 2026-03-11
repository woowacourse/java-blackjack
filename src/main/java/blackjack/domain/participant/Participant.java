package blackjack.domain.participant;

import blackjack.domain.BettingResult;
import blackjack.domain.GameScore;
import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import java.util.List;
import java.util.Objects;

public abstract class Participant {

    protected final Name name;
    protected final Hand hand;
    protected BettingAmount bettingAmount;

    protected Participant(String name, Hand hand) {
        this.name = new Name(name);
        this.hand = hand;
        this.bettingAmount = BettingAmount.initial();
    }

    public abstract List<String> getInitialCards();

    public final void bet(int bettingAmount) {
        this.bettingAmount = this.bettingAmount.register(bettingAmount);
    }

    public final int calculateProfitRate(BettingResult bettingResult) {
        return bettingResult.getProfitRate(bettingAmount.getBettingAmount());
    }

    public final boolean isBust() {
        GameScore gameScore = hand.calculateTotalScore();
        return gameScore.isBust();
    }

    public final void playTurn(Deck deck) {
        Card hitCard = deck.drawCard();
        hand.receiveCard(hitCard);
    }

    public final boolean isBlackjack() {
        return hand.isBlackjack();
    }

    public List<String> getCardNames() {
        return hand.getCards().stream()
                .map(Card::getCardName)
                .toList();
    }

    public final String getName() {
        return name.getValue();
    }

    public final List<Card> getCards() {
        return hand.getCards();
    }

    public final GameScore getScore() {
        return hand.calculateTotalScore();
    }

    @Override
    public final boolean equals(Object o) {
        if (!(o instanceof Participant that)) {
            return false;
        }

        return Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName());
    }
}
