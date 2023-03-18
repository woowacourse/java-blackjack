package blackjack.domain.game;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.strategy.CardShuffle;

import java.util.List;
import java.util.Map;

public class BlackJackGame {

    private static final int BURST_SCORE = 21;
    private static final int DEALER_HIT_NUMBER = 16;

    private final Deck deck;
    private final Referee referee;

    public BlackJackGame(CardShuffle cardShuffle) {
        deck = new Deck(cardShuffle);
        referee = new Referee();
    }

    public void initHit(Players players, Dealer dealer) {
        dealer.initHit(deck);
        players.initHit(deck);
    }

    public void hit(Participant participant) {
        participant.hit(deck.draw());
    }

    public int calculateScore(Participant participant) {
        return participant.calculateScore();
    }

    public boolean isBurst(Participant participant) {
        return BURST_SCORE < calculateScore(participant);
    }

    public boolean isValidScore(Participant participant) {
        return BURST_SCORE > calculateScore(participant);
    }

    public boolean isContinueToHit(int dealerScore) {
        return dealerScore <= DEALER_HIT_NUMBER;
    }

    public List<Result> getPlayersResult(Dealer dealer, Players players) {
        return referee.judgeResult(dealer, players);
    }

    public Map<String, Long> getDealerResult(List<Result> results) {
        return referee.countDealerResult(results);
    }

    public void bet(Players players, List<Integer> amounts) {
        players.bet(amounts);
    }

    public List<Integer> getPlayersProfit(Participant dealer, Players players) {
        return referee.calculatePlayersProfit(referee.judgeResult(dealer, players), players);
    }

    public int getDealerProfit(Participant dealer, Players players) {
        return getPlayersProfit(dealer, players).stream().mapToInt(value -> -value).sum();
    }
}
