package blackjackgame.domain;

public class Judge {
    /*private static final int BLACKJACK_MAX_SCORE = 21;

    private final Dealer dealer;
    private final Players guests;

    public Judge(Dealer dealer, Players guests) {
        this.dealer = dealer;
        this.guests = guests;
    }

    public Map<Guest, GameOutcome> guestsResult() {
        Map<Guest, GameOutcome> guestsResult = new LinkedHashMap<>();
        int dealerScore = dealer.getScore();
        for (final Guest guest : guests.getPlayers()) {
            guestsResult.put(guest, judgeFromScore(dealerScore, guest.getScore()));
        }
        return guestsResult;
    }

    private GameOutcome judgeFromScore(final int dealerScore, final int guestScore) {
        if (guestScore > BLACKJACK_MAX_SCORE ||
            (dealerScore <= BLACKJACK_MAX_SCORE && dealerScore > guestScore)) {
            return LOSE;
        }
        if (dealerScore > BLACKJACK_MAX_SCORE || guestScore > dealerScore) {
            return WIN;
        }
        return DRAW;
    }*/

}
