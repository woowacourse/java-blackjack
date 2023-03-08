package blackjack.domain;

import static blackjack.domain.participant.Result.LOSE;

import blackjack.domain.betting.Betting;
import blackjack.domain.betting.Profit;
import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Result;
import java.util.List;

public class BlackJackGame {

    private static final int TRUMP_COUNT = 1;
    private static final int INITIAL_DRAW_COUNT = 2;

    private final Participants participants;
    private final Deck deck;
    private final Betting betting;

    public BlackJackGame(final Participants participants, final Betting betting) {
        this.participants = participants;
        this.deck = Deck.createUsingTrump(TRUMP_COUNT);
        this.betting = betting;
    }

    public void initialDraw() {
        participants.drawCard(deck, INITIAL_DRAW_COUNT);
        participants.receiveBlackJackBonus(betting);
    }

    public void dealCard(final Participant participant) {
        participant.drawCard(deck.draw());
    }

    public void applyPlayersProfit() {
        final Dealer dealer = getDealer();
        final List<Player> players = getPlayers();

        for (final Player player : players) {
            final Result result = dealer.showResult(player.getScore());
            applyLose(player, result);
        }
    }

    private void applyLose(final Player player, final Result result) {
        if (result == LOSE) {
            betting.fail(player);
        }
    }

    public Profit returnPlayerProfit(final Player player) {
        return betting.getPlayerProfit(player);
    }

    public Profit returnDealerProfit() {
        return betting.getDealerProfit();
    }

    public List<Player> getPlayers() {
        return participants.getPlayers();
    }

    public Dealer getDealer() {
        return participants.getDealer();
    }

    public List<Participant> getParticipants() {
        return participants.getParticipants();
    }

    public int getInitialDrawCount() {
        return INITIAL_DRAW_COUNT;
    }
}
