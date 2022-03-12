package blackjack.domain.participant;

import static java.util.Map.entry;

import blackjack.domain.CardDeck;
import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.dto.OutComeResult;
import blackjack.dto.PlayerCards;
import blackjack.dto.PlayerScoreResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Participants {

    private final List<Participant> participants;
    private int currentTurnIndex;

    public Participants(final List<Participant> participants) {
        Objects.requireNonNull(participants, "players는 null로 생성할 수 없습니다.");
        this.participants = new ArrayList<>(participants);
        validateDuplicationPlayers(this.participants);
    }

    private void validateDuplicationPlayers(final List<Participant> participants) {
        if (calculateDistinctCount(participants) != participants.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private int calculateDistinctCount(final List<Participant> participants) {
        return (int) participants.stream()
                .map(Participant::getName)
                .distinct()
                .count();
    }

    public static Participants createByPlayerNames(final List<String> playerNames, final CardDeck cardDeck) {
        final List<Participant> participants = createPlayers(playerNames, cardDeck);
        return new Participants(participants);
    }

    private static List<Participant> createPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> createPlayer(name, cardDeck))
                .collect(Collectors.toList());
    }

    private static Player createPlayer(final String name, final CardDeck cardDeck) {
        return Player.createNewPlayer(name, cardDeck.provideFirstHitCards());
    }

    public List<PlayerCards> getPlayerFirstCards() {
        return participants.stream()
                .map(PlayerCards::toPlayerFirstCards)
                .collect(Collectors.toUnmodifiableList());
    }

    public void turnToNextPlayer() {
        validateAllTurnEnd();
        currentTurnPlayer().changeFinishStatus();
        currentTurnIndex++;
    }

    private void validateAllTurnEnd() {
        if (isAllTurnEnd()) {
            throw new IllegalStateException("모든 턴이 종료되었습니다.");
        }
    }

    public boolean isAllTurnEnd() {
        return participants.size() <= currentTurnIndex;
    }

    public PlayerCards hitCurrentPlayer(final Card card) {
        final Participant currentParticipant = currentTurnPlayer();
        currentParticipant.hit(card);
        checkCanTurnNext(currentParticipant);
        return PlayerCards.toPlayerCards(currentParticipant);
    }

    private void checkCanTurnNext(final Participant currentParticipant) {
        if (!currentParticipant.canHit()) {
            currentTurnIndex++;
        }
    }

    private Participant currentTurnPlayer() {
        validateAllTurnEnd();
        return participants.get(currentTurnIndex);
    }

    public String getCurrentTurnPlayerName() {
        validateAllTurnEnd();
        return participants.get(currentTurnIndex).getName();
    }

    public PlayerCards getCurrentTurnPlayerCards() {
        return PlayerCards.toPlayerCards(currentTurnPlayer());
    }

    public List<PlayerScoreResult> getPlayerScoreResults() {
        return participants.stream()
                .map(PlayerScoreResult::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult outcomeResult(Participant dealer) {
        return OutComeResult.from(calculateOutcomeResultWithDealer(dealer));
    }

    private Map<String, GameOutcome> calculateOutcomeResultWithDealer(final Participant dealer) {
        return participants.stream()
                .map(player -> entry(player.getName(), player.fight(dealer)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
