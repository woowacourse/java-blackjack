package domain.participant;

import domain.blackjack.Deck;
import domain.blackjack.HitOption;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Participants {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;

    private final List<Participant> value;

    public Participants(final List<String> names) {
        validate(names);
        value = names.stream().map(name -> new Participant(new Name(name))).collect(Collectors.toList());
        value.add(new Dealer());
    }

    private static void validate(List<String> names) {
        if (names.size() < MIN_PARTICIPANT_COUNT || names.size() > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException(String.format("최소 %d명 최대 %d명까지 입력받을 수 있습니다.", MIN_PARTICIPANT_COUNT, MAX_PARTICIPANT_COUNT));
        }
        Set<String> distinctNames = Set.copyOf(names);

        if (distinctNames.size() != names.size()) {
            throw new IllegalArgumentException("이름은 중복될 수 없습니다.");
        }
    }

    public void beginDealing(Deck deck) {
        for (Participant participant : value) {
            participant.receiveCard(deck.draw());
            participant.receiveCard(deck.draw());
        }
    }

    public void participantsHit(Function<Name, String> function, Consumer<Participant> printParticipantHands) {
        for (Participant participant : getNotDealerParticipant()) {
            participantHit(function, printParticipantHands, participant);
        }
    }

    private void participantHit(Function<Name, String> function, Consumer<Participant> printParticipantHands, Participant participant) {
        Dealer dealer = getDealer();
        while (participant.canHit() && HitOption.isHit(function.apply(participant.getName()))) {
            participant.receiveCard(dealer.draw());
            printParticipantHands.accept(participant);
        }
    }

    public Dealer getDealer() {
        return (Dealer) value.stream()
                .filter(Participant::isDealer)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("딜러가 포함되어 있지 않습니다."));
    }

    public List<Participant> getNotDealerParticipant() {
        return value.stream().filter(participant -> !participant.isDealer()).toList();
    }

    public List<Name> getNames() {
        return value.stream()
                .filter(participant -> !participant.isDealer())
                .map(Participant::getName)
                .toList();
    }

    public List<Participant> getValue() {
        return Collections.unmodifiableList(value);
    }
}
