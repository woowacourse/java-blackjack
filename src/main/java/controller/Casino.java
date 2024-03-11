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

public class Casino {

    private final InputView inputView;
    private final ResultView resultView;

    public Casino(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play(final BlackjackGame game) {
        Dealer dealer = new Dealer(Cards.deck());
        dealer.shuffleCards();
        Players players = ParticipantDto.toPlayers(inputView.askPlayerNames());
        game.ready(dealer, players);
        proceed(dealer, players);
        result(game, dealer, players);
    }

    private void proceed(final Dealer dealer, final Players players) {
        resultView.printInitialCards(
                new ParticipantDto(dealer, dealer.peek()),
                ParticipantDto.fromPlayers(players));
        players.forEach(player -> askReceiveMoreCard(dealer, player));
        if (dealer.canHit()) {
            resultView.printDealerCardMessage(new ParticipantDto(dealer));
            dealer.deal(dealer);
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

    private void result(final BlackjackGame game, final Dealer dealer, final Players players) {
        resultView.printResults(
                new ParticipantDto(dealer),
                ParticipantDto.fromPlayers(players),
                game.resultsOf(dealer, players)
        );
    }
}
