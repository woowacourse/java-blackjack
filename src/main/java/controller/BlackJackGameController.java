package controller;

import domain.PlayerResult;
import domain.Players;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Player;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    private static final int CRITERION = 21;

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
        OutputView.printDealerAdditionalCard(dealer.performHit(cardDeck));

        OutputView.printFinalScoreBoard(dealer, players);
        PlayerResult playerResult = new PlayerResult(players.putResultIntoMap(dealer));
        OutputView.printFinalResult(playerResult);
    }

    private static void performPlayersHit(CardDeck cardDeck, Player player) {
        try {
            while (InputUtils.isAnswerHit(InputView.inputHitOrNot(player)) && player.calculateScore() <= CRITERION) {
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

}
