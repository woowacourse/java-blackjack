package blackjack.domain.participant;

import static java.util.Map.entry;

import blackjack.domain.GameOutcome;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.dto.OutComeResult;
import blackjack.dto.ParticipantCards;
import blackjack.dto.ParticipantScoreResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

public class Players {

    private final List<Player> players;
    private int currentTurnIndex;

    public Players(final List<Player> players) {
        Objects.requireNonNull(players, "players는 null로 생성할 수 없습니다.");
        this.players = new ArrayList<>(players);
        validatePlayersSize(players);
        validateDuplicationPlayers(this.players);
    }

    private void validatePlayersSize(final List<Player> players) {
        if (players.isEmpty()) {
            throw new IllegalArgumentException("플레이어는 0명이 될 수 없습니다.");
        }
    }

    private void validateDuplicationPlayers(final List<Player> players) {
        if (calculateDistinctCount(players) != players.size()) {
            throw new IllegalArgumentException("이름 간에 중복이 있으면 안됩니다.");
        }
    }

    private int calculateDistinctCount(final List<Player> players) {
        return (int) players.stream()
                .map(Player::getName)
                .distinct()
                .count();
    }

    public static Players createByPlayerNames(final List<String> playerNames, final CardDeck cardDeck) {
        final List<Player> players = createPlayers(playerNames, cardDeck);
        return new Players(players);
    }

    private static List<Player> createPlayers(final List<String> playerNames, final CardDeck cardDeck) {
        return playerNames.stream()
                .map(name -> createPlayer(name, cardDeck))
                .collect(Collectors.toList());
    }

    private static Player createPlayer(final String name, final CardDeck cardDeck) {
        return Player.createNewPlayer(name, Cards.createByCardDeck(cardDeck));
    }

    public List<ParticipantCards> getFirstCards() {
        return players.stream()
                .map(ParticipantCards::toParticipantFirstCards)
                .collect(Collectors.toUnmodifiableList());
    }

    public void turnToNextParticipant() {
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
        return players.size() <= currentTurnIndex;
    }

    public ParticipantCards hitCurrentParticipant(final Card card) {
        final Player currentTurnPlayer = currentTurnPlayer();
        currentTurnPlayer.hit(card);
        checkCanTurnNext(currentTurnPlayer);
        return ParticipantCards.toParticipantCards(currentTurnPlayer);
    }

    private void checkCanTurnNext(final Player currentPlayer) {
        if (!currentPlayer.canHit()) {
            currentTurnIndex++;
        }
    }

    private Player currentTurnPlayer() {
        validateAllTurnEnd();
        return players.get(currentTurnIndex);
    }

    public String getCurrentParticipantName() {
        validateAllTurnEnd();
        return players.get(currentTurnIndex).getName();
    }

    public ParticipantCards getCurrentParticipantCards() {
        return ParticipantCards.toParticipantCards(currentTurnPlayer());
    }

    public List<ParticipantScoreResult> getParticipantScoreResults() {
        return players.stream()
                .map(ParticipantScoreResult::from)
                .collect(Collectors.toUnmodifiableList());
    }

    public OutComeResult outcomeResult(Dealer dealer) {
        return OutComeResult.from(calculateOutcomeResultWithDealer(dealer));
    }

    private Map<String, GameOutcome> calculateOutcomeResultWithDealer(final Dealer dealer) {
        return players.stream()
                .map(player -> entry(player.getName(), player.fight(dealer)))
                .collect(Collectors.toMap(Entry::getKey, Entry::getValue, (v1, v2) -> v1, LinkedHashMap::new));
    }
}
