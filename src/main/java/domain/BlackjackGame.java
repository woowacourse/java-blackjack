package domain;

import static domain.result.GameResult.DRAW;
import static domain.result.GameResult.LOSE;
import static domain.result.GameResult.WIN;

import domain.card.Deck;
import domain.card.Shuffler;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.result.DealerResult;
import domain.result.GameResult;
import domain.result.GameResults;
import domain.result.PlayerResult;
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


    public Participants getParticipants() {
        return participants;
    }

    // FIXME: 10줄 넘는다
    public GameResults getGameResults() {

        final Participant dealer = participants.getDealer();
        final List<PlayerResult> playerResults = new ArrayList<>();

        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        for (final Participant player : participants.getPlayers()) {

            final GameResult result = judge(dealer, player);

            playerResults.add(new PlayerResult(player.getName(), result));

            if (result == WIN) {
                dealerLoseCount++;
            }
            if (result == DRAW) {
                dealerDrawCount++;
            }
            if (result == LOSE) {
                dealerWinCount++;
            }
        }
        final DealerResult dealerResult =
                new DealerResult(dealer.getName(), dealerWinCount, dealerDrawCount, dealerLoseCount);

        return new GameResults(dealerResult, playerResults);
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
        if (dealer.isBust()) {
            return WIN;
        }

        if (player.isBlackjack() && !dealer.isBlackjack()) {
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

