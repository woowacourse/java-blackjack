package domain.game;

import domain.card.Card;
import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.ParticipantOffset;
import domain.participant.Participants;
import domain.participant.Player;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameManager {

    private static final int START_CARD_COUNT = 2;
    private static final int DEALER_ORDER = 0;

    private final Deck deck;
    private final Participants participants;

    private GameManager(final Deck deck, final Participants participants) {
        this.deck = deck;
        this.participants = participants;
    }

    public static GameManager create(final Deck deck, final Participants participants) {
        return new GameManager(deck, participants);
    }

    public void bet(final int participantOrder, final int betAmount) {
        participants.bet(participantOrder, betAmount, ParticipantOffset.PLAYER);
    }

    public void giveStartCards() {
        final int totalParticipantSize = participants.size();

        for (int participantOrder = 0; participantOrder < totalParticipantSize; participantOrder++) {
            giveTwoCard(participantOrder);
        }
    }

    public void givePlayerCard(final int playerOrder) {
        final Card drawCard = deck.draw();

        participants.addCard(playerOrder, drawCard, ParticipantOffset.PLAYER);
    }

    public int drawCardsForDealer() {
        int drawCardCount = 0;

        while (canDrawByOrder(DEALER_ORDER, ParticipantOffset.DEALER)) {
            final Card drawCard = deck.draw();

            participants.addCard(DEALER_ORDER, drawCard, ParticipantOffset.DEALER);
            drawCardCount++;
        }
        return drawCardCount;
    }

    public boolean canPlayerDrawByOrder(final int playerOrder) {
        return canDrawByOrder(playerOrder, ParticipantOffset.PLAYER);
    }

    public String findPlayerNameByOrder(final int playerOrder) {
        return findNameByOrder(playerOrder, ParticipantOffset.PLAYER);
    }

    public String findDealerName() {
        return findNameByOrder(DEALER_ORDER, ParticipantOffset.DEALER);
    }

    public List<Card> findPlayerCardsByOrder(final int playerOrder) {
        return participants.findPlayerCardsByOrder(playerOrder, ParticipantOffset.PLAYER);
    }

    private BigDecimal calculateBenefit(final Participant participant, final GameResult gameResult) {
        final Player player = (Player) participant;

        return player.calculateBenefit(gameResult);
    }

    private boolean canDrawByOrder(final int participantOrder, final ParticipantOffset offset) {
        return participants.canDrawByOrder(participantOrder, offset);
    }

    private String findNameByOrder(final int participantOrder, final ParticipantOffset offset) {
        return participants.findParticipantNameByOrder(participantOrder, offset);
    }

    public int playerSize() {
        return participants.playerSize();
    }

    private void giveTwoCard(final int participantOrder) {
        int cardCount = 0;

        while (cardCount++ < START_CARD_COUNT) {
            final Card drawCard = deck.draw();

            participants.addCard(participantOrder, drawCard);
        }
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public List<String> getParticipantsName() {
        return participants.getParticipantNames();
    }

    public Map<String, BigDecimal> getTotalPlayerGameResult() {
        final Map<Participant, GameResult> gameResults = participants.calculatePlayerGameResult();

        return gameResults.keySet().stream()
                .collect(Collectors.toMap(Participant::getName,
                        participant -> calculateBenefit(participant, gameResults.get(participant)),
                        (newValue, oldValue) -> oldValue, LinkedHashMap::new));
    }
}
