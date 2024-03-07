package model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.Player;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;

    public BlackJack(Participants participants, Dealer dealer) {
        this.participants = participants;
        this.dealer = dealer;
    }

    public void offerCardToPlayers(int cardCount) {
        participants.offerCardToPlayers(cardCount);
        offerCardToDealer(cardCount);
    }

    public void offerCardToParticipant(Participant participant, int cardCount) {
        participants.offerCardToParticipant(participant, cardCount);
    }

    public void offerCardToDealer(int cardCount) {
        dealer.addCards(Cards.selectRandomCards(cardCount));
    }

    public Map<Participant, Outcome> matchParticipantsOutcome() {
        List<Participant> sumPlayers = participants.getParticipants();
        return sumPlayers.stream()
                .collect(toMap(
                        participant -> participant,
                        participant -> findOutcome(participant, dealer)
                ));
    }

    private Outcome findOutcome(Player participant, Player dealer) {
        if (participant.isOverMaximumSum() && dealer.isOverMaximumSum()) {
            return Outcome.DRAW;
        }
        if (participant.isOverMaximumSum()) {
            return Outcome.LOSE;
        }
        if (dealer.isOverMaximumSum()) {
            return Outcome.WIN;
        }
        return findCloseToThreshold(participant.findPlayerDifference(), dealer.findPlayerDifference());
    }

    private Outcome findCloseToThreshold(int participantDifference, int dealerDifference) {//closestToThreshold
        if (participantDifference > dealerDifference) {
            return Outcome.LOSE;
        }
        if (participantDifference < dealerDifference) {
            return Outcome.WIN;
        }
        return Outcome.DRAW;
    }

    public boolean isDealerUnderThreshold() {
        return dealer.canReceiveCard();
    }

    public Map<Outcome, Long> getDealerOutCome() {
        Map<Participant, Outcome> participantOutcome = matchParticipantsOutcome();
        return participantOutcome.values().stream()
                .collect(groupingBy(Outcome::reverse, counting()));
    }

    public List<Participant> getParticipants() {
        return Collections.unmodifiableList(participants.getParticipants());
    }

    public Dealer getDealer() {
        return dealer;
    }

}
