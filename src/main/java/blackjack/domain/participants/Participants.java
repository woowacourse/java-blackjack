package blackjack.domain.participants;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import blackjack.domain.card.Deck;
import blackjack.exceptions.InvalidParticipantsException;

public class Participants implements Iterable<Participant> {
    public static final int FIRST_CARDS_COUNT = 2;
    public static final String SPLIT_DELIMITER = ",";
    public static final String JOIN_DELIMITER = ", ";

    private final List<Participant> participants;

    public Participants(final Dealer dealer, final String names) {
        this.participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(Arrays.stream(names
            .split(SPLIT_DELIMITER))
            .map(Player::new)
            .collect(Collectors.toList()));
    }

    // 테스트용
    public Participants(final Dealer dealer, final Player... players) {
        this.participants = new ArrayList<>();
        participants.add(dealer);
        participants.addAll(Arrays.asList(players));
    }

    public void initialDraw(Deck deck) {
        for (int i = 0; i < FIRST_CARDS_COUNT; i++) {
            participants.forEach(participant -> participant.draw(deck));
        }
    }

    public Participant getDealer() {
        return participants.stream()
            .filter(Participant::isDealer)
            .findFirst()
            .orElseThrow(() ->
                new InvalidParticipantsException("딜러가 없는 게임은 무효입니다.")
            );
    }

    public List<Participant> getPlayers() {
        return participants.stream()
            .filter(participant -> !participant.isDealer())
            .collect(Collectors.toList());
    }

    public String getNames() {
        return participants.stream()
            .map(Participant::getName)
            .collect(Collectors.joining(JOIN_DELIMITER));
    }

    @Override
    public Iterator<Participant> iterator() {
        return participants.iterator();
    }
}
