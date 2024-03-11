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
    private final Cards cards;

    public BlackJack(Participants participants, Dealer dealer, Cards cards) {
        validateParticipantIsNotNull(participants);
        validateDealerIsNotNull(dealer);
        this.participants = participants;
        this.dealer = dealer;
        this.cards = cards;
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
        participants.offerCardToPlayers(cards, size);
        offerCardToDealer(size);
    }

    public void offerCardToParticipant(Participant participant, CardSize size) {
        participants.offerCardToParticipant(cards, participant, size);
    }

    public void offerCardToDealer(CardSize size) {
        dealer.addCards(cards.selectRandomCards(size));
    }

    public Map<Participant, Outcome> matchParticipantsOutcome() {
        List<Participant> sumPlayers = participants.getParticipants();
        return sumPlayers.stream()
                .collect(toMap(
                        participant -> participant,
                        participant -> participant.findOutcome(dealer)
                ));
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
