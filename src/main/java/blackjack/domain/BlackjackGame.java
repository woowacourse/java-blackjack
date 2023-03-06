package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.gameResult.BlackjackResult;
import blackjack.domain.gameResult.ResultReader;
import blackjack.domain.gameResult.WinningResult;
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
        Map<Player, WinningResult> playersResult = new LinkedHashMap<>();
        List<WinningResult> dealerResults = new ArrayList<>();
        ResultReader resultReader = new ResultReader();

        for (Player player : participants.extractPlayers()) {
            dealerResults.add(resultReader.calulateDealerResult(dealer, player));
            playersResult.put(player, resultReader.calulatePlayerResult(dealer, player));
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

    private void firstHitRule(final Participant participant) {
        for (int count = 0; count < FIRST_HIT_COUNT; count++) {
            participant.hit(cards.pick());
        }
    }
}
