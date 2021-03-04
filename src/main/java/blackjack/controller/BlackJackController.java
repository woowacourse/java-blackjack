package blackjack.controller;

import blackjack.util.GameInitializer;
import blackjack.domain.Deck;
import blackjack.domain.Players;
import blackjack.domain.participant.Dealer;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class BlackJackController {

    public static void play() {
        OutputView.printInputNames();
        Deck deck = GameInitializer.initializeDeck();
        Players players = GameInitializer.initializePlayers(InputView.inputString(), deck);
        Dealer dealer = GameInitializer.initializeDealer(deck);
//        blackJackGame.prepare();
//        OutputView.printGameInitializeMessage(blackJackGame.getDealer(), blackJackGame.getPlayers(),
//            BlackJackGame.STARTING_CARD_COUNT);
//        while (!blackJackGame.isPrepared()) {
//            Player player = blackJackGame.nextPlayer();
//            while (player.isContinue()) {
//                OutputView.willDrawCard(player);
//                player.willContinue(InputView.inputString(), blackJackGame.getDeck());
//                OutputView.printParticipantStatus(player, false);
//            }
//        }
//
//        while (blackJackGame.getDealer().isContinue()) {
//            blackJackGame.getDealer().drawCard(blackJackGame.getDeck());
//            OutputView.printDealerDrawCard(blackJackGame.getDealer());
//        }
//
//        OutputView.printParticipantsStatus(blackJackGame.getParticipants());
//        OutputView.printResult(blackJackGame.getGameResult());
    }
}

