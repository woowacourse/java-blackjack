package blackjack.domain;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.strategy.CardPicker;
import java.util.List;

public class BlackJackGame {

    private static final int BURST_SCORE = 21;
    private static final int DEALER_HIT_NUMBER = 16;

    private final CardPool cardPool;
    private final Referee referee;

    public BlackJackGame() {
        cardPool = new CardPool();
        referee = new Referee();
    }

    public void initHit(Players players, Dealer dealer, CardPicker cardPicker) {
        dealer.initHit(cardPool, cardPicker);
        players.initHit(cardPool, cardPicker);
    }

    public void hit(Participant participant, CardPicker cardPicker) {
        participant.hit(cardPool.draw(cardPicker));
    }

    public int calculateScore(Participant participant) {
        return participant.calculateScore();
    }

    public boolean isBurst(int score) {
        return BURST_SCORE < score;
    }

    public boolean isValidScore(int score) {
        return BURST_SCORE > score;
    }

    public boolean isContinueToHit(int dealerScore) {
        return dealerScore <= DEALER_HIT_NUMBER;
    }

    public List<Result> getResult(Dealer dealer, Players players) {
        return referee.judgeResult(dealer, players);
    }
}
