package domain.blackjack;

import domain.participant.Dealer;
import domain.participant.Name;
import domain.participant.Participant;
import domain.participant.Participants;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

public class BlackJack {

    private final Dealer dealer;
    private final Participants participants;

    public BlackJack(final Dealer dealer, final Participants participants) {
        this.dealer = dealer;
        this.participants = participants;
    }

    public void beginDealing(BiConsumer<Participants, Dealer> beginBlackJack) {
        dealer.receiveCard();
        dealer.receiveCard();
        participants.beginDealing(dealer);

        beginBlackJack.accept(participants, dealer);
    }

/*    public void play(BiConsumer<Participant, Dealer> participantConsumer) {
        participants.participantHit(participantConsumer, dealer);
    }*/

    public int dealerHit() {
        int count = 0;
        while (dealer.shouldHit()) {
            dealer.receiveCard();
            count++;
        }
        return count;
    }

    public void play2(Function<Name, String> function, Consumer<Participant> printParticipantHands) {
        participants.participantsHit(function, printParticipantHands, dealer);
    }
}
