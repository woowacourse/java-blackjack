package domain.participant;

import domain.card.Card;

import domain.game.GameResult;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Participants {

    private static final int MIN_COUNT = 1;
    private static final int MAX_COUNT = 7;
    private static final int DEALER_ORDER = 0;
    private static final int PLAYER_START_INDEX = 1;

    private final List<Participant> participants;

    private Participants(final List<String> playerNames) {
        final List<String> trimPlayerNames = processTrimPlayerNames(playerNames);

        validateDuplicateNames(trimPlayerNames);
        validatePlayerCount(trimPlayerNames);

        this.participants = makeParticipants(trimPlayerNames);
    }

    public static Participants create(final List<String> playerNames) {
        return new Participants(playerNames);
    }

    public void addCard(final int participantOrder, final Card card) {
        final Participant participant = participants.get(participantOrder);

        participant.addCard(card);
    }

    public void addCard(final int participantOrder, final Card card, final ParticipantOffset offset) {
        final int participantIndex = offset.mapToIndexFromOrder(participantOrder);
        final Participant participant = participants.get(participantIndex);

        participant.addCard(card);
    }

    public void bet(final int participantOrder, final int betAmount, final ParticipantOffset offset) {
        final int participantIndex = offset.mapToIndexFromOrder(participantOrder);
        final Player player = (Player) participants.get(participantIndex);

        player.bet(betAmount);
    }

    public Map<Participant, GameResult> calculatePlayerGameResult() {
        final Dealer dealer = (Dealer) participants.get(DEALER_ORDER);
        final int totalParticipantSize = participants.size();
        final List<Participant> players = participants.subList(PLAYER_START_INDEX, totalParticipantSize);

        return players.stream()
                .collect(Collectors.toMap(Function.identity(), dealer::calculateResult,
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }

    public boolean canDrawByOrder(final int participantOrder, final ParticipantOffset offset) {
        final int participantIndex = offset.mapToIndexFromOrder(participantOrder);
        final Participant target = participants.get(participantIndex);

        return target.canDraw();
    }

    public String findParticipantNameByOrder(final int participantOrder, final ParticipantOffset offset) {
        final int participantIndex = offset.mapToIndexFromOrder(participantOrder);
        final Participant targetPlayer = participants.get(participantIndex);

        return targetPlayer.getName();
    }

    public List<Card> findPlayerCardsByOrder(final int playerOrder, final ParticipantOffset offset) {
        final int playerIndex = offset.mapToIndexFromOrder(playerOrder);
        final Participant player = participants.get(playerIndex);
        final ParticipantCard playerCard = player.participantCard;

        return playerCard.getCards();
    }

    private List<String> processTrimPlayerNames(final List<String> playerNames) {
        return playerNames.stream()
                .map(String::trim)
                .collect(Collectors.toUnmodifiableList());
    }

    private void validateDuplicateNames(final List<String> playerNames) {
        final Set<String> uniqueNames = new HashSet<>(playerNames);

        if (playerNames.size() != uniqueNames.size()) {
            throw new IllegalArgumentException("플레이어의 이름은 중복될 수 없습니다.");
        }
    }

    private void validatePlayerCount(final List<String> playerNames) {
        final int playerCount = playerNames.size();

        if (playerCount < MIN_COUNT || playerCount > MAX_COUNT) {
            throw new IllegalArgumentException("플레이어는 최소 1명, 최대 7명이어야 합니다.");
        }
    }

    private List<Player> makePlayers(final List<String> playerNames) {
        return playerNames.stream()
                .map(Player::create)
                .collect(Collectors.toUnmodifiableList());
    }

    private List<Participant> makeParticipants(final List<String> playerNames) {
        final List<Participant> participants = new ArrayList<>();
        final List<Player> players = makePlayers(playerNames);

        participants.add(Dealer.create());
        participants.addAll(players);
        return participants;
    }

    public int size() {
        return participants.size();
    }

    public int playerSize() {
        final int totalParticipantSize = participants.size();

        return totalParticipantSize - PLAYER_START_INDEX;
    }

    public List<Participant> getParticipants() {
        return List.copyOf(participants);
    }

    public List<String> getParticipantNames() {
        return participants.stream()
                .map(Participant::getName)
                .collect(Collectors.toUnmodifiableList());
    }
}
