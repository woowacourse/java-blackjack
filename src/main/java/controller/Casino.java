package controller;

import domain.BlackjackGame;
import domain.card.Cards;
import domain.name.Names;
import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;
import mapper.GameResultMapper;
import mapper.NameMapper;
import mapper.ParticipantMapper;
import view.BlackJackGameCommand;
import view.InputView;
import view.ResultView;
import view.dto.participant.NameDto;
import domain.vo.BettingMoney;

import java.util.List;

public class Casino {

    private final InputView inputView;
    private final ResultView resultView;

    public Casino(final InputView inputView, final ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void open() {
        Names names = NameMapper.namesDtoToNames(inputView.askPlayerNames());
        List<BettingMoney> bettingMoneys = askBettingMoney(names);
        Dealer dealer = new Dealer(Cards.deck());
        dealer.shuffleCards();
        BlackjackGame game = new BlackjackGame(dealer, ParticipantMapper.namesAndBettingMoneyToPlayers(names, bettingMoneys));
        game.prepare();
        playOf(game);
        resultOf(game);
    }

    private List<BettingMoney> askBettingMoney(final Names names) {
        return names.playerNames()
                    .stream()
                    .map(name -> new BettingMoney(inputView.askBettingMoney(new NameDto(name.value()))))
                    .toList();
    }

    private void playOf(final BlackjackGame game) {
        Dealer dealer = game.dealer();
        Players players = game.players();
        resultView.printInitialCards(ParticipantMapper.participantAndCardToParticipantDto(dealer, dealer.peekCard()),
                ParticipantMapper.playersToParticipantsDto(players));
        players.forEach(player -> askReceiveMoreCard(dealer, player));
        while (dealer.canReceiveMoreCard()) {
            resultView.printDealerCardMessage(ParticipantMapper.participantToParticipantDto(dealer));
            game.handOutCards(dealer, 1);
        }
    }

    private void askReceiveMoreCard(final Dealer dealer, final Player player) {
        if (!player.canReceiveMoreCard()) {
            return;
        }
        BlackJackGameCommand command = inputView.askMoreCard(NameMapper.playerToNameDto(player));
        if (command.yes()) {
            dealer.deal(player);
            resultView.printParticipantHand(ParticipantMapper.participantToParticipantDto(player));
            askReceiveMoreCard(dealer, player);
        }
    }

    private void resultOf(final BlackjackGame game) {
        resultView.printResults(
                ParticipantMapper.participantToParticipantDto(game.dealer()),
                ParticipantMapper.playersToParticipantsDto(game.players()),
                GameResultMapper.gameResultToGameResultDto(game.resultsOfParticipants())
        );
    }
}
