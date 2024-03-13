package controller;

import domain.BlackjackGame;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.BlackJackGameCommand;
import view.InputView;
import view.ResultView;
import view.dto.participant.ParticipantDto;
import view.dto.result.GameResultDto;

public class Casino {

    private final InputView inputView;
    private final ResultView resultView;

    public Casino(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void open() {
        BlackjackGame game = new BlackjackGame(new Dealer(Cards.deck()),
                ParticipantDto.toPlayers(inputView.askPlayerNames()));
        game.setUp();
        playOf(game);
        resultOf(game);
    }

    private void playOf(final BlackjackGame game) {
        Dealer dealer = game.dealer();
        Players players = game.players();
        resultView.printInitialCards(new ParticipantDto(dealer, dealer.peekCard()),
                ParticipantDto.fromPlayers(players));
        players.forEach(player -> askReceiveMoreCard(dealer, player));
        if (dealer.canReceiveMoreCard()) {
            resultView.printDealerCardMessage(new ParticipantDto(dealer));
            game.handOutCards(dealer, dealer, 1);
        }
    }

    private void askReceiveMoreCard(final Dealer dealer, final Player player) {
        if (!player.canReceiveMoreCard()) {
            return;
        }
        BlackJackGameCommand command = inputView.askMoreCard(new ParticipantDto(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(new ParticipantDto(player));
            askReceiveMoreCard(dealer, player);
        }
    }

    private void resultOf(final BlackjackGame game) {
        resultView.printResults(
                new ParticipantDto(game.dealer()),
                ParticipantDto.fromPlayers(game.players()),
                new GameResultDto(game.resultsOfDealerPosition(),
                        game.resultsOfPlayerPosition())
        );
    }
}
