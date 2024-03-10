package controller;

import domain.BlackjackGame;
import domain.card.Cards;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import view.BlackJackGameCommand;
import view.InputView;
import view.ResultView;
import view.dto.participant.DealerDto;
import view.dto.participant.ParticipantDto;
import view.dto.participant.PlayerDto;
import view.dto.participant.PlayersDto;

import java.util.ArrayList;
import java.util.List;

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
        PlayersDto playersDto = inputView.askPlayerNames();
        Players players = playersDto.toDomain();
        game.ready(dealer, players);
        proceed(dealer, players);
        result(game, dealer, players);
    }

    private void proceed(final Dealer dealer, final Players players) {
        resultView.printInitialCards(new DealerDto(dealer, dealer.peek()), new PlayersDto(players));
        players.forEach(player -> askReceiveMoreCard(dealer, player));
        if (dealer.canHit()) {
            resultView.printDealerCardMessage(new DealerDto(dealer));
            dealer.deal(dealer);
        }
    }

    private void result(final BlackjackGame game, final Dealer dealer, final Players players) {
        List<ParticipantDto> dealerAndPlayers = new ArrayList<>();
        dealerAndPlayers.add(new DealerDto(dealer));
        dealerAndPlayers.addAll(new PlayersDto(players).players());
        resultView.printResults(dealerAndPlayers, game.resultsOf(dealer, players));
    }

    private void askReceiveMoreCard(final Dealer dealer, final Player player) {
        if (!player.canReceiveMoreCard()) {
            return;
        }
        BlackJackGameCommand command = inputView.askMoreCard(new PlayerDto(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(new PlayerDto(player));
            askReceiveMoreCard(dealer, player);
        }
    }
}
