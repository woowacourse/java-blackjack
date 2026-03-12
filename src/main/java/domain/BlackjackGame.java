package domain;

import domain.card.Deck;
import domain.participant.Participant;
import domain.participant.Participants;
import java.util.ArrayList;
import java.util.List;

public class BlackjackGame {

    public static final int INIT_DRAW_COUNT = 2;
    public static final int HIT_DRAW_COUNT = 1;
    public static final int BUST_BOUND = 21;
    public static final int DEALER_DRAW_BOUND = 16;

    private final Deck deck;
    private final Participants participants;

    public BlackjackGame(final Participants participants) {
        this.deck = new Deck();
        this.participants = participants;
    }

    public void initDraw() {
        for (final Participant participant : participants.getPlayers()) {
            drawCards(participant, INIT_DRAW_COUNT);
        }
    }

    // FIXME: Player랑 Dealer 구분 필요한가 - hit 하나에서 공통으로 처리할 수 있게?
    public void hitPlayer(final Participant player) {
        drawCards(player, HIT_DRAW_COUNT);
    }

    public void hitDealer() {
        drawCards(participants.getDealer(), HIT_DRAW_COUNT);
    }


    public boolean canPlayerDraw(final Participant player) {
        return !player.isBust() && player.getScore() != BUST_BOUND;
    }

    public boolean canDealerDraw() {
        return participants.getDealer().getScore() <= DEALER_DRAW_BOUND;
    }


    public Participants getParticipants() {
        return participants;
    }

    // FIXME: 10줄 넘는다
    public List<FinalResult> getGameResults() {

        final Participant dealer = participants.getDealer();

        final List<FinalResult> results = new ArrayList<>();

        int dealerWinCount = 0;
        int dealerDrawCount = 0;
        int dealerLoseCount = 0;
        for (final Participant player : participants.getPlayers()) {

            final GameResult result = judge(dealer, player);

            if (GameResult.WIN.equals(result)) {
                dealerLoseCount++;
                results.add(new FinalResult(player.getName(), 1, 0, 0, false));
            }
            if (GameResult.DRAW.equals(result)) {
                dealerDrawCount++;
                results.add(new FinalResult(player.getName(), 0, 1, 0, false));
            }
            if (GameResult.LOSE.equals(result)) {
                dealerWinCount++;
                results.add(new FinalResult(player.getName(), 0, 0, 1, false));
            }
        }
        results.add(new FinalResult(dealer.getName(), dealerWinCount, dealerDrawCount, dealerLoseCount, true));

        return results;
    }


    private void drawCards(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            participant.draw(deck.draw());
        }
    }

    // FIXME: 10줄 넘는다
    private GameResult judge(final Participant dealer, final Participant player) {
        if (player.isBust()) {
            return GameResult.LOSE;
        }

        if (dealer.isBust()) {
            return GameResult.WIN;
        }

        if (dealer.isBlackjack() && !player.isBlackjack()) {
            return GameResult.LOSE;
        }

        if (!dealer.isBlackjack() && player.isBlackjack()) {
            return GameResult.WIN;
        }

        final int dealerScore = dealer.getScore();
        final int playerScore = player.getScore();

        if (dealerScore > playerScore) {
            return GameResult.LOSE;
        }

        if (dealerScore < playerScore) {
            return GameResult.WIN;
        }

        return GameResult.DRAW;
    }
}
