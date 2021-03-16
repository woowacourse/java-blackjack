package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.card.InitialCardsDrawStrategy;
import blackjack.domain.participant.*;
import blackjack.domain.state.Hit;
import blackjack.dto.GameResultDto;
import blackjack.dto.WinPrizeDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static blackjack.view.OutputView.MSG_DEALER_GET_MORE_CARD;

public class BlackJackController {

    public void play() {
        Deck deck = Deck.generate();
        Participants participants = getParticipants(InputView.inputNames(), deck);
        OutputView.printInitialCardStatus(participants);

        playBlackJackGame(participants, deck);
        OutputView.printWinPrizeResult(getGameResultDto(participants));
    }

    private Participants getParticipants(List<String> names, Deck deck) {
        List<AbstractParticipant> participants = names.stream()
                .map(name -> new Player(name, new Hit(deck.drawCards(new InitialCardsDrawStrategy()))))
                .collect(Collectors.toList());
        bettingPlayer(participants);
        participants.add(0, new Dealer(new Hit(deck.drawCards(new InitialCardsDrawStrategy()))));
        return new Participants(participants);
    }

    private void playBlackJackGame(Participants participants, Deck deck) {
        playAllPlayersTurn(participants.extractPlayers(), deck);
        playDealerTurn(participants.extractDealer(), deck);
        OutputView.printAllParticipantsCards(participants);
    }

    private void playAllPlayersTurn(List<Player> players, Deck deck) {
        for (Player player : players) {
            playPlayerTurn(player, deck);
        }
    }

    private void playPlayerTurn(Player player, Deck deck) {
        while (!player.getState().isEndState()) {
            if (!InputView.inputAskMoreCard(player)) {
                player.stay();
                break;
            }

            player.handOutCard(deck.draw());
            OutputView.printParticipantCards(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (!dealer.isEnd()) {
            OutputView.printMessage(MSG_DEALER_GET_MORE_CARD);
            dealer.handOutCard(deck.draw());
        }
    }

    private void bettingPlayer(List<AbstractParticipant> participantList) {
        for (AbstractParticipant participant : participantList) {
            int bettingMoney = InputView.inputBettingMoney(participant);
            ((Player) participant).betting(bettingMoney);
        }
    }

    public GameResultDto getGameResultDto(Participants participants) {
        List<Player> players = participants.extractPlayers();
        Dealer dealer = participants.extractDealer();

        List<WinPrizeDto> playersWinPrizeDto = getPlayerWinPrizeDtoList(players, dealer);

        int totalPlayerWinPrize = getTotalPlayerWinPrize(players, dealer);
        WinPrizeDto dealerWinPrizeDto = new WinPrizeDto(dealer.getName(), dealer.payWinPrize(totalPlayerWinPrize));

        return new GameResultDto(dealerWinPrizeDto, playersWinPrizeDto);
    }

    private List<WinPrizeDto> getPlayerWinPrizeDtoList(List<Player> players, Dealer dealer) {
        return players.stream()
                .map(player -> new WinPrizeDto(player.getName(), player.calculateWinPrize(dealer.getState())))
                .collect(Collectors.toList());
    }

    private int getTotalPlayerWinPrize(List<Player> players, Dealer dealer) {
        return players.stream()
                .mapToInt(player -> player.calculateWinPrize(dealer.getState()))
                .sum();
    }

}
