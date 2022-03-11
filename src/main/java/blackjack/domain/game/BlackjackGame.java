package blackjack.domain.game;

import java.util.HashMap;
import java.util.Map;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;

public class BlackjackGame {

    private final Deck deck = new Deck();
    private final Participants participants;
    private Map<Player, WinningResult> playerResult;

    public BlackjackGame(Participants participants) {
        this.participants = participants;
    }

    public void initCards() {
        participants.dealInitialCards(deck);
    }

    public void hitCard(Participant participant) {
        participant.addCard(deck.pickCard());
    }

    public boolean isHitAndDealMoreCard(boolean isHit, Participant participant) {
        if (isHit && participant.canHit()) {
            hitCard(participant);
            return true;
        }
        return false;
    }

    public void calculatePlayerResult() {
        playerResult = new HashMap<>();
        Dealer dealer = participants.getDealer();
        for (Player player : participants.getPlayers()) {
            playerResult.put(player, WinningResult.of(player, dealer));
        }
    }

    public Map<Player, WinningResult> getPlayerResult() {
        return playerResult;
    }

    public Map<WinningResult, Integer> getDealerResult() {
        Map<WinningResult, Integer> dealerResult = new HashMap<>();
        for (WinningResult winningResult : playerResult.values()) {
            WinningResult convertedResult = convertToDealerWinningResult(winningResult);
            dealerResult.put(convertedResult, dealerResult.getOrDefault(convertedResult, 0) + 1);
        }
        return dealerResult;
    }

    private WinningResult convertToDealerWinningResult(WinningResult winningResult) {
        if (winningResult == WinningResult.DRAW) {
            return winningResult;
        }
        if (winningResult == WinningResult.WIN) {
            return WinningResult.LOSE;
        }
        return WinningResult.WIN;
    }

    public Participants getParticipants() {
        return participants;
    }
}
