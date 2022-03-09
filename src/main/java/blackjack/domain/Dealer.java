package blackjack.domain;

public class Dealer extends Participant {

    private Dealer() {
    }

    public static Dealer startWithTwoCards(final Deck deck) {
        final Dealer dealer = new Dealer();
        dealer.drawCard(deck);
        dealer.drawCard(deck);
        return dealer;
    }

    public void continueDraw(Deck deck) {
        while (isPossibleToDrawCard()) {
            drawCard(deck);
        }
    }

    private boolean isPossibleToDrawCard() {
        return calculateScore() < 17;
    }

    public WinningResult judgeWinner(final Player player) {
        if (player.isBurst()) {
            return WinningResult.LOSS;
        }
        if (this.isBurst()) {
            return WinningResult.WIN;
        }

        return WinningResult.valueOf(player.isHigherThan(this));
    }
}
