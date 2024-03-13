package domain.participant;

import domain.blackjack.HitOption;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

public class Players {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 8;

    private final List<Player> value;

    public Players(final List<String> names) {
        validate(names);
        value = names.stream().map(name -> new Player(new Name(name))).toList();
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

    public void beginDealing(Dealer dealer) {
        dealer.receiveCard(dealer.draw());
        dealer.receiveCard(dealer.draw());
        for (Participant participant : value) {
            participant.receiveCard(dealer.draw());
            participant.receiveCard(dealer.draw());
        }
    }

    public void playersHit(Function<Name, String> isHitOption, Consumer<Participant> printParticipantHands, Dealer dealer) {
        for (Player player : value) {
            playerHit(isHitOption, printParticipantHands, player, dealer);
        }
    }

    private void playerHit(Function<Name, String> isHitOption, Consumer<Participant> printParticipantHands, Participant participant, Dealer dealer) {
        while (participant.canHit() && HitOption.isHit(isHitOption.apply(participant.getName()))) {
            participant.receiveCard(dealer.draw());
            printParticipantHands.accept(participant);
        }
    }

    public List<Name> getNames() {
        return value.stream()
                .map(Participant::getName)
                .toList();
    }

    public List<Player> getValue() {
        return value;
    }
}
