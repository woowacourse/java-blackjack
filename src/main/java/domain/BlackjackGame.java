package domain;

import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
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

    public Map<Participant, Integer> getBettingResult() {
        Map<Participant, Integer> betResult = new LinkedHashMap<>();
        for (Participant player : participants.getPlayers()) {
            PlayerGameResult playerGameResult = getPlayerGameResult(player);
            int reward = playerGameResult.calculateRatio(player.getBetAmount());
            betResult.put(player, reward);
        }

        return new LinkedHashMap<>(betResult);
    }

    private PlayerGameResult getPlayerGameResult(Participant player) {
        if (isPlayerBlackjack(player)) {
            return PlayerGameResult.BLACKJACK;
        }
        if (isPlayerWin(player)) {
            return PlayerGameResult.WIN;
        }
        if (isDraw(player)) {
            return PlayerGameResult.DRAW;
        }
        return PlayerGameResult.LOSE;
    }

    private boolean isPlayerBlackjack(Participant player) {
        return player.isBlackjack() && !participants.isDealerBlackjack();
    }

    private boolean isPlayerWin(Participant player) {
        return (player.calculateScore() > participants.getDealerScore() && !player.isBust())
                || participants.isDealerBust();
    }

    private boolean isDraw(Participant player) {
        return player.calculateScore() == participants.getDealerScore();
    }


    public Participant getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getPlayers() {
        return participants.getPlayers();
    }
}
