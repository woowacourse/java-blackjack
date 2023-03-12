package blackjack.domain;

import blackjack.domain.card.Cards;
import blackjack.domain.gameresult.ResultReader;
import blackjack.domain.gameresult.WinningResult;
import blackjack.domain.money.Revenue;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BlackjackGame {

    private static final String DEALER_NAME = "딜러";
    private static final int FIRST_HIT_COUNT = 2;

    private final Participants participants;
    private final Cards cards;
    private final ResultReader resultReader;

    public BlackjackGame(Participants participants, Cards cards, ResultReader resultReader) {
        this.participants = participants;
        this.cards = cards;
        this.resultReader = resultReader;
    }

    public Map<Player, WinningResult> generateBlackjackResult() {
        Dealer dealer = participants.extractDealer();
        Map<Player, WinningResult> playersResult = new LinkedHashMap<>();

        for (Player player : participants.extractPlayers()) {
            playersResult.put(player, resultReader.calulatePlayerResult(dealer, player));
        }
        return Collections.unmodifiableMap(playersResult);
    }

    public Map<Player, Revenue> generatePlayersRevenue(Map<Player, WinningResult> result) {
        Map<Player, Revenue> playersRevenue = new LinkedHashMap<>();
        for (Player player : result.keySet()) {
            playersRevenue.put(player, Revenue.generateRevenue(player, result.get(player)));
        }
        return Collections.unmodifiableMap(playersRevenue);
    }

    public int generateDealerRevenue(Map<Player, Revenue> playerRevenue) {
        int dealerRevenue = 0;
        for (Player player : playerRevenue.keySet()) {
            dealerRevenue += playerRevenue.get(player).getRevenue() * -1;
        }
        return dealerRevenue;
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
