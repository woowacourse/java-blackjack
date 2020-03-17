package blackjack.controller;

import java.util.List;

import blackjack.domain.card.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.domain.result.SimpleResult;
import blackjack.exceptions.InvalidPlayerException;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJack {
    public static final int BLACK_JACK_SCORE = 21;

    private final Deck deck;
    private final Dealer dealer;
    private Participants participants;

    private BlackJack() {
        this.deck = Deck.create();
        this.dealer = new Dealer();
    }

    public static void run() {
        new BlackJack().play();
    }

    private void play() {
        OutputView.nameInstruction();
        this.participants = getParticipants();
        initialPhase();
        userGamePhase();
        dealerGamePhase();
        endPhase();
    }

    private Participants getParticipants() {
        try {
            return new Participants(dealer, InputView.getInput());
        } catch (InvalidPlayerException e) {
            OutputView.printError(e.getMessage());
            return getParticipants();
        }
    }

    private void initialPhase() {
        OutputView.shareFirstPair(participants);
        participants.initialDraw(deck);
        OutputView.participantsStatus(participants);
    }

    private void userGamePhase() {
        dealerDrawsMore(participants.getDealer());
        playersDrawMore(participants.getPlayers());
    }

    private void dealerDrawsMore(final Participant participant) {
        participant.drawMoreCard(deck);
    }

    private void playersDrawMore(final List<Participant> players) {
        for (Participant player : players) {
            playersChooseToDraw((Player)player);
        }
    }

    private void playersChooseToDraw(final Player player) {
        boolean wantsMoreCard;
        do {
            OutputView.moreCardInstruction(player);
            wantsMoreCard = wantsToDrawMore(player);
            OutputView.participantStatus(player);
        } while (wantsMoreCard && player.score() < BLACK_JACK_SCORE);
    }

    private boolean wantsToDrawMore(final Player player) {
        final boolean wantsMoreCard;
        wantsMoreCard = InputView.yesOrNo();
        if (wantsMoreCard) {
            player.draw(deck);
        }
        return wantsMoreCard;
    }

    private void dealerGamePhase() {
        OutputView.moreCardInstruction(dealer);
    }

    private void endPhase() {
        OutputView.result(participants);
        OutputView.statistics(new SimpleResult(participants));
    }
}
