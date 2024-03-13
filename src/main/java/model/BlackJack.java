package model;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.toMap;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;
import model.card.CardSize;
import model.card.Cards;
import model.player.Dealer;
import model.player.Participant;
import model.player.Participants;
import model.player.User;

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
        if (participants == null) {
            throw new IllegalArgumentException("참가자 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void validateDealerIsNotNull(Dealer dealer) {
        if (dealer == null) {
            throw new IllegalArgumentException("딜러가 없으면 게임이 진행되지 않습니다.");
        }
    }

    public void decideParticipantsPlay(Function<String, Boolean> isMoreCard, BiConsumer<String, Cards> callback) {
        for (Participant participant : participants.getParticipants()) {
            decideParticipantPlay(participant, isMoreCard, callback);
        }
    }

    private void decideParticipantPlay(Participant participant,
                                       Function<String, Boolean> isMoreCard, BiConsumer<String, Cards> callback) {
        while (participant.isHit() && isMoreCard.apply(participant.getName())) {
            offerCardToParticipant(participant, CardSize.ONE);
            callback.accept(participant.getName(), participant.getCards());
        }
    }

    public void decideDealerPlay(Runnable runnable) {
        while (dealer.isHit()) {
            runnable.run();
            offerCardToDealer(CardSize.ONE);
        }
    }

    private void offerCardToParticipant(Participant participant, CardSize size) {
        if (participants.isExistParticipant(participant)) {
            dealer.offerCardToParticipant(participant, size);
        }
        throw new IllegalArgumentException("참가자(" + participant.getName() + ")가 존재하지 않습니다.");
    }

    public void offerCardToDealer(CardSize size) {
        dealer.addCards(dealer.takeCardFromDeck(size));
    }

    public Map<Participant, Outcome> matchParticipantsOutcome() {
        List<Participant> sumPlayers = participants.getParticipants();
        return sumPlayers.stream()
                .collect(toMap(
                        participant -> participant,
                        participant -> participant.findOutcome(dealer)
                ));
    }

    public Map<Outcome, Long> getDealerOutCome() {
        Map<Participant, Outcome> participantOutcome = matchParticipantsOutcome();
        return participantOutcome.values().stream()
                .collect(groupingBy(Outcome::reverse, counting()));
    }

    public Map<String, Cards> mapToUsersNameAndCards() {
        return participants.getParticipants().stream()
                .collect(toMap(
                        User::getName,
                        User::getCards
                ));
    }

    public List<String> findParticipantsName() {
        return participants.findParticipantsName();
    }

    public Map<String, Cards> mapToDealerNameAndCards() {
        return Map.of(dealer.getName(), dealer.getCards());
    }
}
