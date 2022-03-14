package blackJack.domain;

import blackJack.domain.card.Deck;
import blackJack.domain.participant.Dealer;
import blackJack.domain.participant.Participant;
import blackJack.domain.participant.Participants;
import blackJack.domain.participant.Player;
import blackJack.domain.result.WinDrawLose;

import java.util.EnumMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int INITIAL_CARD_COUNT = 2;

    private final Participants participants;
    private final Deck deck;

    public BlackJackGame(Participants participants, Deck deck) {
        this.participants = participants;
        this.deck = deck;
    }

    public void firstCardDispensing() {
        distributeCard(getDealer());
        getPlayers().forEach(this::distributeCard);
    }

    public void distributeCard(Participant participant) {
        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            participant.receiveCard(deck.getCard());
        }
    }

    public Dealer doDealerGame() {
        final Dealer dealer = participants.getDealer();
        while (dealer.hasNextTurn()) {
            dealer.receiveCard(deck.getCard());
        }
        return dealer;
    }

    public Map<WinDrawLose, Integer> calculateDealerResult() {
        final Map<WinDrawLose, Integer> gameResult = initializeDealerResult();
        for (Player player : getPlayers()) {
            final WinDrawLose winDrawLose = getDealer().isWin(player);
            gameResult.computeIfPresent(winDrawLose, (k, v) -> v + 1);
        }
        return gameResult;
    }

    private Map<WinDrawLose, Integer> initializeDealerResult() {
        final Map<WinDrawLose, Integer> gameResult = new EnumMap<>(WinDrawLose.class);
        for (WinDrawLose value : WinDrawLose.values()) {
            gameResult.put(value, 0);
        }
        return gameResult;
    }

    public Map<Player, WinDrawLose> calculatePlayersResult() {
        final Map<Player, WinDrawLose> gameResult = new LinkedHashMap<>();
        for (Player player : getPlayers()) {
            final WinDrawLose winOrLose = player.isWin(getDealer());
            gameResult.put(player, winOrLose);
        }
        return gameResult;
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }
}
