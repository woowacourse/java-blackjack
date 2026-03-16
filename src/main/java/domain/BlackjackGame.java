package domain;

import static domain.result.GameResult.BLACKJACK;
import static domain.result.GameResult.DRAW;
import static domain.result.GameResult.LOSE;
import static domain.result.GameResult.WIN;

import domain.card.CurrentHand;
import domain.card.CurrentHands;
import domain.card.Deck;
import domain.card.Shuffler;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BetProfit;
import domain.result.BetProfits;
import domain.result.FinalResult;
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


    public CurrentHands getInitHands() {
        final List<CurrentHand> playerHands = new ArrayList<>();

        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();

        final CurrentHand dealerHand = new CurrentHand(dealer.getName(), List.of(dealer.getHand().getFirst()));
        for (final Player player : players) {
            playerHands.add(new CurrentHand(player.getName(), player.getHand()));
        }

        return new CurrentHands(dealerHand, playerHands);
    }

    public List<FinalResult> getFinalResult() {
        final List<FinalResult> finalResults = new ArrayList<>();

        final Dealer dealer = participants.getDealer();
        final List<Player> players = participants.getPlayers();
        finalResults.add(new FinalResult(new CurrentHand(dealer.getName(), dealer.getHand()), dealer.getScore()));

        for (final Player player : players) {
            finalResults.add(new FinalResult(new CurrentHand(player.getName(), player.getHand()), player.getScore()));
        }

        return finalResults;
    }

    public BetProfits getBetProfits() {
        final Dealer dealer = participants.getDealer();
        final List<BetProfit> playerResults = new ArrayList<>();

        int dealerProfit = 0;
        for (final Player player : participants.getPlayers()) {
            final GameResult result = judge(dealer, player);

            final int playerProfit = result.calculateBetProfit(player.getBetAmount());

            playerResults.add(new BetProfit(player.getName(), playerProfit));

            dealerProfit -= playerProfit;
        }
        final BetProfit dealerResult = new BetProfit(dealer.getName(), dealerProfit);

        return new BetProfits(dealerResult, playerResults);
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

