package domain;

import domain.card.CurrentHand;
import domain.card.CurrentHands;
import domain.card.Deck;
import domain.card.Shuffler;
import domain.participant.Dealer;
import domain.participant.Participant;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.BetProfits;
import domain.result.FinalResult;
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
        return BetProfits.from(participants.getDealer(), participants.getPlayers());
    }

    public Participants getParticipants() {
        return participants;
    }


    private void drawCards(final Participant participant, final int count) {
        for (int i = 0; i < count; i++) {
            participant.draw(deck.draw());
        }
    }


}

