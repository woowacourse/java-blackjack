package model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.Player;

public class BlackJack {

    private final Participants participants;
    private final Dealer dealer;

    public BlackJack(Participants participants, Dealer dealer) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);
        this.participants = participants;
        this.dealer = dealer;
    }

    public void validateParticipantIsNotNull(Participants participants) {
        if(participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void validateDealerIsNotNull(Dealer dealer) {
        if(dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void offerCardToPlayers(CardSize size) {
        participants.offerCardToPlayers(size);
        offerCardToDealer(size);
    }

    public void offerCardToParticipant(Participant participant, CardSize size) {
        participants.offerCardToParticipant(participant, size);
    }

    public void offerCardToDealer(CardSize size) {
        dealer.addCards(Cards.selectRandomCards(size));
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
