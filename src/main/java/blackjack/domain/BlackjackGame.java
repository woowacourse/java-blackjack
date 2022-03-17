package blackjack.domain;

public class BlackjackGame {

    private static final int INITIAL_CARD_NUMBER = 2;
    public static final int BLACKJACK_NUMBER = 21;

    private final Participants participants;
    private final Participant dealer;
    private final Drawable drawable;

    public BlackjackGame(Participants participants, Drawable drawable) {
        this.participants = participants;
        this.dealer = new Dealer();
        this.drawable = drawable;
    }

    public void drawStartingCard() {
        for (int i = 0; i < INITIAL_CARD_NUMBER; i++) {
            participants.drawAll(drawable);
            dealer.drawCard(drawable.draw());
        }
    }

    public boolean checkDealerDrawable() {
        return dealer.isDrawable();
    }

    public void drawDealerCard() {
        dealer.drawCard(drawable.draw());
    }

    public ProfitResult calculateProfitResult() {
        return participants.compete(dealer);
    }

    public void drawPlayerCard() {
        participants.drawPlayerCard(drawable);
    }

    public void proceedTurn() {
        participants.proceedTurn();
    }

    public Participants getPlayers() {
        return participants;
    }

    public Participant getDealer() {
        return dealer;
    }

    public String getNowTurnPlayerName() {
        return getNowTurnPlayer().getName();
    }

    public Participant getNowTurnPlayer() {
        return participants.getCurrentTurnPlayer();
    }

    public boolean isBustCurrentTurn() {
        return participants.isBustCurrentTurnPlayer();
    }

    public boolean isEndAllPlayersTurn() {
        return participants.isEndPlayersTurn();
    }
}
