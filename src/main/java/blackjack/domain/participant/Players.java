package blackjack.domain.participant;

import static java.util.stream.Collectors.toMap;

import blackjack.domain.result.Score;
import blackjack.domain.result.WinStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.IntStream;

public class Players {
    private final List<Participant> players;

    public Players(final List<Participant> players) {
        validateDuplicate(players);
        this.players = players;
    }

    public static Players from(final List<String> playerNames) {
        final List<Participant> participants = playerNames.stream()
                .map(Participant::from)
                .toList();

        return new Players(participants);
    }

    private void validateDuplicate(final List<Participant> participants) {
        if (Set.copyOf(participants).size() != participants.size()) {
            throw new IllegalArgumentException("중복된 이름의 참여자는 참여할 수 없습니다.");
        }
    }

    public void divideCard(final List<Card> cards) {
        validateCardSize(cards);
        final Iterator<Card> cardIterator = cards.iterator();

        players.forEach(player -> player.addStartCard(cardIterator.next(), cardIterator.next()));
    }

    private void validateCardSize(final List<Card> cards) {
        if (cards.size() != players.size() * 2) {
            throw new IllegalArgumentException("카드의 수량이 맞지 않습니다.");
        }
    }

    public void addCardTo(final String name, final Card card) {
        final Participant findedParticipant = findParticipant(name);
        findedParticipant.addCard(card);
    }

    public Map<ParticipantName, WinStatus> determineWinStatus(final Score dealerScore) {
        final Map<ParticipantName, WinStatus> playersWinStatus = new LinkedHashMap<>();

        for (Participant participant : players) {
            playersWinStatus.put(participant.getName(), WinStatus.of(dealerScore, participant.calculate()));
        }

        return playersWinStatus;
    }

    public boolean isNotDead(final String name) {
        final Participant participant = findParticipant(name);
        return participant.isNotDead();
    }

    private Participant findParticipant(final String name) {
        return players.stream()
                .filter(participant -> participant.isName(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("없는 참가자 입니다."));
    }

    public Hands getHandsOf(final String name) {
        final Participant findedParticipant = findParticipant(name);
        return findedParticipant.getHands();
    }

    public Map<ParticipantName, Hands> getPlayersHands() {
        return players.stream()
                .collect(toMap(Participant::getName,
                        Participant::getHands,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public Map<ParticipantName, Score> getPlayersScore() {
        return players.stream()
                .collect(toMap(Participant::getName,
                        Participant::calculate,
                        (v1, v2) -> v1,
                        LinkedHashMap::new));
    }

    public List<ParticipantName> getNames() {
        return players.stream()
                .map(Participant::getName)
                .toList();
    }

    public int count() {
        return players.size();
    }
}
