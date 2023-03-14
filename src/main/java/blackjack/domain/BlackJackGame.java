package blackjack.domain;

import blackjack.domain.betting.BettingTable;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.PlayerName;
import blackjack.domain.participant.Result;
import java.util.List;

public class BlackJackGame {

    private static final int TRUMP_COUNT = 1;
    private static final int INITIAL_DRAW_COUNT = 2;
    private static final int BLACK_JACK_SCORE = 21;

    private final Participants participants;
    private final Deck deck;
    private final BettingTable bettingTable;

    public BlackJackGame(final Participants participants, final BettingTable bettingTable) {
        this.participants = participants;
        this.deck = Deck.createUsingTrump(TRUMP_COUNT);
        this.bettingTable = bettingTable;
    }

    public void initialDraw() {
        final List<PlayerName> playerNames = participants.getPlayerNames();
        for (int i = 0; i < INITIAL_DRAW_COUNT; i++) {
            participants.drawCardForDealer(deck);
            playerNames.forEach(playerName -> participants.drawCardForPlayer(playerName, deck));
        }
    }

    public void dealCardForPlayer(final PlayerName playerName) {
        participants.drawCardForPlayer(playerName, deck);
    }

    public void dealCardForDealer() {
        if (participants.isDrawableDealer()) {
            participants.drawCardForDealer(deck);
        }
    }

    public int getPlayerProfit(final PlayerName playerName) {
        final int dealerScore = participants.getDealerScore();
        final int playerScore = participants.getPlayerScore(playerName);
        final Result result = Result.show(playerScore, dealerScore, BLACK_JACK_SCORE);
        return result.calculateProfit(bettingTable.getPlayerBetting(playerName));
    }

    public int getDealerProfit() {
        final List<PlayerName> playerNames = participants.getPlayerNames();
        final int playersProfit = playerNames.stream()
                .mapToInt(this::getPlayerProfit)
                .sum();
        return -playersProfit;
    }

    public List<PlayerName> getPlayerNames() {
        return participants.getPlayerNames();
    }

    public int getPlayerScore(final PlayerName playerName) {
        return participants.getPlayerScore(playerName);
    }

    public Hand getPlayerHand(final PlayerName playerName) {
        return participants.getPlayerHand(playerName);
    }

    public boolean isDrawablePlayer(final PlayerName playerName) {
        return participants.isDrawablePlayer(playerName);
    }

    public String getDealerName() {
        return participants.getDealerName();
    }

    public int getDealerScore() {
        return participants.getDealerScore();
    }

    public Hand getDealerHand() {
        return participants.getDealerHand();
    }

    public Hand getDealerHiddenHand() {
        return participants.getDealerHiddenHand();
    }

    public boolean isDealerAdditionalDrawn() {
        return participants.isDealerAdditionalDrawn();
    }

    public int getDealerAdditionalDrawScore() {
        return participants.getDealerAdditionalDrawScore();
    }

    public int getInitialDrawCount() {
        return INITIAL_DRAW_COUNT;
    }
}
