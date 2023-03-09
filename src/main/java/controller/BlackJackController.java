package controller;

import domain.BlackJackGame;
import domain.PlayerCommand;
import domain.card.CardDeck;
import domain.card.shuffler.CardsShuffler;
import domain.participant.Dealer;
import domain.participant.Participants;
import domain.participant.Player;
import domain.result.WinningResult;
import view.InputView;
import view.OutputView;

public final class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run(CardsShuffler cardsShuffler) {
        BlackJackGame blackJackGame = createBlackJackGame(cardsShuffler);
        playGame(blackJackGame);
        showResult(blackJackGame);
    }

    private BlackJackGame createBlackJackGame(CardsShuffler cardsShuffler) {
        CardDeck cardDeck = new CardDeck(cardsShuffler);
        Participants participants = new Participants(inputView.readPlayerNames());

        return new BlackJackGame(participants, cardDeck);
    }

    private void playGame(final BlackJackGame blackJackGame) {
        blackJackGame.distributeInitialCards();

        outputView.printInitialMessage(blackJackGame.getPlayers());
        outputView.printAllState(blackJackGame.getParticipants());

        for (Player player : blackJackGame.getPlayers()) {
            distributeCardToPlayer(blackJackGame, player);
        }

        distributeCardToDealer(blackJackGame);
    }

    private void distributeCardToPlayer(final BlackJackGame blackJackGame, final Player player) {
        boolean isRepeat = player.isHittable();

        while (isRepeat) {
            PlayerCommand command = PlayerCommand.from(inputView.readHit(player.getName()));
            distributeByCommand(blackJackGame, player, command);
            isRepeat = player.isHittable() && command.isHit();
        }
    }

    private void distributeByCommand(final BlackJackGame blackJackGame, final Player player,
                                     final PlayerCommand command) {
        if (command.isHit()) {
            blackJackGame.giveCardTo(player);
        }

        outputView.printSingleState(player);
    }

    private void distributeCardToDealer(final BlackJackGame blackJackGame) {
        Dealer dealer = blackJackGame.getDealer();

        while (dealer.isHittable()) {
            outputView.printFillDealerCards();

            blackJackGame.giveCardTo(dealer);
        }
    }

    private void showResult(final BlackJackGame blackJackGame) {
        outputView.printFinalState(blackJackGame.getParticipants());

        WinningResult winningResult = new WinningResult(blackJackGame.getParticipants());

        outputView.printResult(winningResult);
    }
}
