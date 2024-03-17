package model.player;

import model.Outcome;
import model.card.Card;
import model.card.Cards;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Participant extends User {
    private final ParticipantProfile profile;

    public Participant(Cards cards, ParticipantProfile profile) {
        super(cards);
        this.profile = profile;
    }

    public void offerCard(Predicate<Name> inputForMoreCard,
                          BiConsumer<Name, Cards> printParticipantsCard, Supplier<Card> selectCard) {
        while (isHit() && inputForMoreCard.test(profile.getName())) {
            addCard(selectCard.get());
            printParticipantsCard.accept(profile.getName(), getCards());
        }
    }

    public int calculateRevenue(Dealer dealer) {
        return profile.calculateRevenue(findOutcome(dealer), cards.findBlackJackState());
    }

    private Outcome findOutcome(Dealer dealer) {
        if (isNotHit()) {
            return Outcome.LOSE;
        }
        if (dealer.isNotHit()) {
            return Outcome.WIN;
        }
        return findPlayerOutcome(dealer.findPlayerDifference());
    }

    private Outcome findPlayerOutcome(int otherDifference) {
        int difference = findPlayerDifference();
        if (otherDifference > difference) {
            return Outcome.WIN;
        }
        if (otherDifference < difference) {
            return Outcome.LOSE;
        }
        return Outcome.DRAW;
    }

    public Name getName() {
        return profile.getName();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Participant that = (Participant) o;
        return Objects.equals(profile, that.profile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(profile);
    }
}
