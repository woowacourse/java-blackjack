package controller;

import config.CardDeckFactory;
import domain.BlackJack;
import domain.card.CardDeck;
import domain.participant.Dealer;
import domain.participant.Players;
import view.InputUntilValid;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        CardDeckFactory cardDeckFactory = new CardDeckFactory();
        CardDeck cardDeck = cardDeckFactory.create();
        Players players = createPlayersUntilValidate();
        Dealer dealer = new Dealer(cardDeck);

        playBlackJack(players, dealer);
    }

    private void playBlackJack(final Players players, final Dealer dealer) {
        BlackJack blackJack = new BlackJack(players, dealer);
        blackJack.hitCardsToParticipant();
        outputView.printParticipant(players, dealer);

        blackJack.drawPlayers(
                (player) -> InputUntilValid.validatePlayerAnswer(player, inputView::askPlayerForHitOrStand),
                outputView::printPlayerDeck);

        outputView.printDrawDealer(dealer);
        blackJack.drawDealer();
        outputView.printScore(players, dealer);
        outputView.printResult(blackJack.calculatePlayerResult());
    }

    private Players createPlayersUntilValidate() {
        try {
            return Players.from(inputView.readPlayersName());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return createPlayersUntilValidate();
        }
    }
}
