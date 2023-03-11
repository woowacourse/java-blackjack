package blackjack.domain;

import blackjack.domain.betting.BettingTable;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.PlayerName;
import java.util.List;

public class BlackJackGame {

    private static final int TRUMP_COUNT = 1;
    private static final int INITIAL_DRAW_COUNT = 2;

    private final Participants participants;
    private final Deck deck;
    private final BettingTable bettingTable;

    public BlackJackGame(final Participants participants, final BettingTable bettingTable) {
        this.participants = participants;
        this.deck = Deck.createUsingTrump(TRUMP_COUNT);
        this.bettingTable = bettingTable;
    }

    public void initialDraw() {
        participants.drawCard(deck, INITIAL_DRAW_COUNT);
    }

    public void dealCard(final PlayerName playerName) {
        participants.drawCardForPlayer(playerName, deck);
    }

    public int showPlayerScore(final PlayerName playerName) {
        return participants.getPlayerScore(playerName);
    }

    public Hand showPlayerHand(final PlayerName playerName) {
        return participants.getPlayerHand(playerName);
    }

    public boolean isDrawablePlayer(final PlayerName playerName) {
        return participants.isDrawablePlayer(playerName);
    }

    public String getDealerName() {
        return participants.getDealerName();
    }

    public List<PlayerName> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public Hand getDealerHiddenHand() {
        return participants.getDealerHiddenHand();
    }

    public int getInitialDrawCount() {
        return INITIAL_DRAW_COUNT;
    }
}
