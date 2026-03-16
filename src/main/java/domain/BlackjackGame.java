package domain;

import static domain.result.GameResult.BLACKJACK;
import static domain.result.GameResult.DRAW;
import static domain.result.GameResult.LOSE;
import static domain.result.GameResult.WIN;

import domain.card.Deck;
import domain.card.Shuffler;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BetResult;
import domain.result.BetResults;
import domain.result.GameResult;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    public static final int INIT_DRAW_COUNT = 2;
    public static final int HIT_DRAW_COUNT = 1;

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final Participants participants) {
        this.deck = new Deck();
        this.participants = participants;
    }

    public BlackjackGame(final Participants participants, final Shuffler shuffler) {
        this.deck = new Deck(shuffler);
        this.participants = participants;
    }


    public void initDraw() {
        for (final Participant participant : participants.getPlayers()) {
            drawCards(participant, INIT_DRAW_COUNT);
        }
        drawCards(participants.getDealer(), INIT_DRAW_COUNT);
    }

    public void hit(final Participant participant) {
        drawCards(participant, HIT_DRAW_COUNT);
    }


    public BetResults getBetResults() {
        final Dealer dealer = participants.getDealer();
        final List<BetResult> playerResults = new ArrayList<>();

        int dealerProfit = 0;
        for (final Player player : participants.getPlayers()) {
            final GameResult result = judge(dealer, player);

            final int playerProfit = result.calculateBetProfit(player.getBetAmount());

            playerResults.add(new BetResult(player.getName(), playerProfit));

            dealerProfit -= playerProfit;
        }
        final BetResult dealerResult = new BetResult(dealer.getName(), dealerProfit);

        return new BetResults(dealerResult, playerResults);
    }

    public Participants getParticipants() {
        return participants;
    }


    private void drawCards(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            participant.draw(deck.draw());
        }
    }

    private GameResult judge(final Participant dealer, final Participant player) {
        if (player.isBust()) {
            return LOSE;
        }

        if (player.isBlackjack() && !dealer.isBlackjack()) {
            return BLACKJACK;
        }

        if (dealer.isBust()) {
            return WIN;
        }

        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return LOSE;
        }

        return compareScore(dealer, player);
    }

    private GameResult compareScore(final Participant dealer, final Participant player) {

        final int dealerScore = dealer.getScore();
        final int playerScore = player.getScore();

        if (dealerScore < playerScore) {
            return WIN;
        }
        if (dealerScore > playerScore) {
            return LOSE;
        }

        return DRAW;
    }
}

