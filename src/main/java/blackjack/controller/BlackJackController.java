package blackjack.controller;

import blackjack.domain.Rule;
import blackjack.domain.RuleImpl;
import blackjack.domain.card.Deck;
import blackjack.domain.participants.*;
import blackjack.exceptions.InvalidPlayerException;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;

public class BlackJackController {
    public static void run() {
        OutputView.nameInstruction();
        Deck deck = new Deck();
        Dealer dealer = new Dealer();
        Players players = new Players(InputView.getInput());
        Participants participants = getParticipants(dealer, players);
        initialPhase(deck, participants);
        userGamePhase(deck, participants);
        dealerGamePhase(dealer);
        endPhase(participants);
    }

    private static Participants getParticipants(final Dealer dealer, Players players) {
        try {
            return new Participants(dealer, players.getPlayers());
        } catch (InvalidPlayerException e) {
            OutputView.printError(e.getMessage());
            return getParticipants(dealer, players);
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

    private static void playersDrawMore(final Deck deck, final List<Player> players) {
        for (Player player : players) {
            playersChooseToDraw(deck, player);
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
            player.draw(deck.pop());
        }
        return wantsMoreCard;
    }

    private static void dealerGamePhase(final Dealer dealer) {
        OutputView.moreCardInstruction(dealer);
    }

    private static void endPhase(final Participants participants) {
        Rule rule = new RuleImpl();
        rule.judgeBasic(participants);
        OutputView.result(participants);
        OutputView.statistics(participants);
    }
}
