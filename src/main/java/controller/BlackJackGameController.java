package controller;

import domain.GameParticipant;
import domain.PlayerResult;
import domain.Players;
import domain.card.CardDeck;
import domain.exception.OverScoreException;
import domain.participant.Dealer;
import domain.participant.Player;
import utils.InputUtils;
import view.InputView;
import view.OutputView;

public class BlackJackGameController {

    public static void run() {
        Players players = setPlayers();
        GameParticipant participant = new GameParticipant(players);
        participant.initialDraw();
        OutputView.printInitialDraw(participant);

        Dealer dealer = participant.getDealer();
        CardDeck cardDeck = participant.getCardDeck();
        for (Player player : players.getPlayers()) {
            performPlayersHit(cardDeck, player);
        }
        OutputView.printDealerAdditionalCard(dealer.performHit(cardDeck));

        OutputView.printFinalScoreBoard(participant);
        PlayerResult playerResult = new PlayerResult();
        playerResult.deduceResult(participant);
        OutputView.printFinalResult(playerResult);
    }

    private static void performPlayersHit(CardDeck cardDeck, Player player) {
        try {
            while (InputUtils.isAnswerHit(InputView.inputHitOrNot(player))) {
                player.receive(cardDeck.draw());
                OutputView.printCardStatus(player);
            }
        } catch (OverScoreException e) {
            OutputView.printErrorMessage(e.getMessage());
            OutputView.printCardStatus(player);
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
