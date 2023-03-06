package blackjack.domain;

import blackjack.util.WinningResult;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Cards cards;

    public BlackjackGame(Participants participants, Cards cards) {
        this.participants = participants;
        this.cards = cards;
    }

    public BlackjackResult generateBlackjackResult() {
        Dealer dealer = participants.extractDealer();
        List<Player> players = participants.extractPlayers();
        Map<Player, WinningResult> playersResult = new LinkedHashMap<>();
        List<WinningResult> dealerResults = new ArrayList<>();

        for (Player player : players) {
            WinningResult dealerResult = dealer.judgeWinOrLose(player);
            dealerResults.add(dealerResult);
            playerResultSave(playersResult, player, dealerResult);
        }
        return new BlackjackResult(playersResult, dealerResults);
    }

    public void settingGame() {
        for (Participant participant : participants.getParticipants()) {
            firstHitRule(participant);
        }
    }

    public Participants getParticipants() {
        return participants;
    }

    public Cards getCards() {
        return cards;
    }

    private void playerResultSave(final Map<Player, WinningResult> playersResult, final Player player, final WinningResult dealerResult) {
        if (dealerResult == WinningResult.WIN) {
            playersResult.put(player, WinningResult.LOSE);
        }
        if (dealerResult == WinningResult.LOSE) {
            playersResult.put(player, WinningResult.WIN);
        }
        if (dealerResult == WinningResult.PUSH) {
            playersResult.put(player, WinningResult.PUSH);
        }
    }

    private void firstHitRule(final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(cards.pick());
        }
    }
}
