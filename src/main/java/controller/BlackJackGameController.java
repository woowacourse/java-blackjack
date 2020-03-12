package controller;

import java.util.Map;

import domain.Players;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.result.Result;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    private static final int DEALER_STANDARD_SCORE = 16;

    public static void run() {
        Players players = setPlayers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();

        dealer.firstDraw(cardDeck);
        players.firstDraw(cardDeck);
        OutputView.printInitialDrawInstruction(players);
        OutputView.printDealerInitialDraw(dealer);
        OutputView.printCardStatusForAllPlayers(players);

        for (Player player : players.getPlayers()) {
            performPlayersHit(cardDeck, player);
        }
        performDealerHit(dealer, cardDeck);

        OutputView.printFinalScoreBoard(dealer, players);
        Map<String, Result> userResultMap = players.putResultIntoMap(dealer);
        OutputView.printFinalResult(userResultMap);
    }

    private static void performPlayersHit(CardDeck cardDeck, Player player) {
        try {
            while (InputUtils.isAnswerHit(InputView.inputHitOrNot(player))) {
                player.receive(cardDeck);
                OutputView.printCardStatus(player);
            }
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            performPlayersHit(cardDeck, player);
        }
    }

    private static Players setPlayers() {
        try {
            String names = InputView.inputUserNames();
            return new Players(names);
        } catch (Exception e) {
            OutputView.printErrorMessage(e.getMessage());
            return setPlayers();
        }
    }

    static void performDealerHit(Dealer dealer, CardDeck cardDeck) {
        while (dealer.calculateScore() <= DEALER_STANDARD_SCORE) {
            OutputView.printDealerAdditionalCard();
            dealer.receive(cardDeck);
        }
    }

}
