package blackjack.controller;

import java.util.List;

import blackjack.domain.Rule;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.Dealer;
import blackjack.domain.participants.Participant;
import blackjack.domain.participants.Participants;
import blackjack.domain.participants.Player;
import blackjack.exceptions.InvalidPlayerException;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {
    public static void run() {
        OutputView.nameInstruction();
        Deck deck = Deck.create();
        Dealer dealer = new Dealer();
        Participants participants = getParticipants(dealer);
        initialPhase(deck, participants);
        userGamePhase(deck, participants);
        dealerGamePhase(dealer);
        endPhase(participants);
    }

    private static Participants getParticipants(final Dealer dealer) {
        try {
            return new Participants(dealer, InputView.getInput());
        } catch (InvalidPlayerException e) {
            OutputView.printError(e.getMessage());
            return getParticipants(dealer);
        }
    }

    private static void initialPhase(final Deck deck, final Participants participants) {
        OutputView.shareFirstPair(participants);
        participants.initialDraw(deck);
        OutputView.participantsStatus(participants);
    }

    private static void userGamePhase(final Deck deck, final Participants participants) {
        dealerDrawsMore(deck, participants.getDealer());
        playersDrawMore(deck, participants.getPlayers());
    }

    private static void dealerDrawsMore(final Deck deck, final Participant participant) {
        participant.drawMoreCard(deck);
    }

    private static void playersDrawMore(final Deck deck, final List<Participant> players) {
        for (Participant player : players) {
            playersChooseToDraw(deck, (Player)player);
        }
    }

    private static void playersChooseToDraw(final Deck deck, final Player player) {
        boolean wantsMoreCard;
        do {
            OutputView.moreCardInstruction(player);
            wantsMoreCard = wantsToDrawMore(deck, player);
            OutputView.participantStatus(player);
        } while (wantsMoreCard && !player.isBusted());
    }

    private static boolean wantsToDrawMore(final Deck deck, final Player player) {
        final boolean wantsMoreCard;
        wantsMoreCard = InputView.yesOrNo();
        if (wantsMoreCard) {
            player.draw(deck);
        }
        return wantsMoreCard;
    }

    private static void dealerGamePhase(final Dealer dealer) {
        OutputView.moreCardInstruction(dealer);
    }

    private static void endPhase(final Participants participants) {
        Rule.judge(participants);
        OutputView.result(participants);
        OutputView.statistics(participants);
    }
}
