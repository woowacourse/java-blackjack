package blackjack.controller;

import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.BlackJackParticipant;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.util.GameInitializer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BlackJackController {

    public static void play() {
        Deck deck = GameInitializer.initializeDeck();
        OutputView.printInputNames();
        Players players = GameInitializer.initializePlayers(InputView.inputString(), deck);
        Dealer dealer = GameInitializer.initializeDealer(deck);
        List<BlackJackParticipant> participants = getBlackJackParticipants(players, dealer);
        OutputView.printGameInitializeMessage(participants, GameInitializer.STARTING_CARD_COUNT);

        playersDrawStage(deck, players);
        dealerDrawStage(deck, dealer);
        showGameResult(players, dealer, participants);
    }

    private static List<BlackJackParticipant> getBlackJackParticipants(Players players, Dealer dealer) {
        List<BlackJackParticipant> participants = new ArrayList<>(Collections.singletonList(dealer));
        participants.addAll(players.unwrap());
        return participants;
    }

    private static void playersDrawStage(Deck deck, Players players) {
        for (Player player : players.unwrap()) {
            playerDrawStage(deck, player);
        }
    }

    private static void playerDrawStage(Deck deck, Player player) {
        while (player.isContinue()) {
            OutputView.willDrawCard(player);
            drawOrStop(deck, player);
        }
    }

    private static void drawOrStop(Deck deck, Player player) {
        if (player.willContinue(InputView.inputString())) {
            player.draw(deck);
            OutputView.printParticipantStatus(player, false);
        }
    }

    private static void dealerDrawStage(Deck deck, Dealer dealer) {
        while (dealer.isContinue()) {
            dealer.draw(deck);
            OutputView.printDealerDrawCard(dealer);
        }
    }

    private static void showGameResult(Players players, Dealer dealer, List<BlackJackParticipant> participants) {
        OutputView.printParticipantsStatus(participants);
        OutputView.printResult(players.match(dealer));
    }
}

