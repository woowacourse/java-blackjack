package domain;

import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackGame {

    private final Deck deck;
    private final Participants participants;

    private BlackjackGame(Participants participants) {
        this.deck = Deck.create();
        this.participants = participants;
    }

    public static BlackjackGame of(List<String> playersName) {
        return new BlackjackGame(Participants.of(playersName));
    }

    public void start() {
        participants.initHand(deck);
    }

    public void playDealerTurn() {
        participants.playDealerTurn(deck);
    }

    public boolean canDealerHit() {
        return participants.canDealerHit();
    }

    public void giveCardToParticipant(Participant player) {
        player.addCard(deck.pollAvailableCard());
    }

    public List<String> getPlayersName() {
        return participants.getPlayers().stream()
                .map(Participant::getName)
                .collect(Collectors.toList());
    }

    public Map<String, PlayerGameResult> getResult() {
        Map<String, PlayerGameResult> result = new LinkedHashMap<>();
        participants.getPlayers().forEach(player -> result.put(player.getName(), getPlayerGameResult(player)));

        return new LinkedHashMap<>(result);
    }

    private PlayerGameResult getPlayerGameResult(Participant player) {
        int dealerScore = participants.getDealerScore();
        int playerScore = player.calculateScore();

        if (isPlayerLose(player, dealerScore, playerScore)) {
            return PlayerGameResult.LOSE;
        }
        if (playerScore == dealerScore) {
            return PlayerGameResult.DRAW;
        }

        return PlayerGameResult.WIN;
    }

    private boolean isPlayerLose(Participant player, int dealerScore, int playerScore) {
        return (playerScore < dealerScore && !participants.isDealerBust()) || player.isBust();
    }

    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }
}
