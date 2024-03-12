package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {

    private final Participants participants;
    private final Deck deck;

    public BlackJack(final Participants participants) {
        this.participants = participants;
        this.deck = new Deck();
    }

    public void beginDealing(Consumer<Participants> beginBlackJack) {
        participants.beginDealing(deck);

        beginBlackJack.accept(participants);
    }

    public void play(Function<Name, String> isHitOption, Consumer<Participant> printParticipantHands) {
        participants.participantsHit(isHitOption, printParticipantHands, deck);
    }

    public int dealerHit() {
        int count = 0;
        Dealer dealer = participants.getDealer();
        while (dealer.shouldHit()) {
            dealer.receiveCard(deck.draw());
            count++;
        }
        return count;
    }
}
