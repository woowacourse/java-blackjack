package blackjack.controller;

import blackjack.domain.card.Deck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participant;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.state.Hit;
import blackjack.dto.GameResultDto;
import blackjack.dto.WinPrizeDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {

    public void play() {
        Deck deck = Deck.generate();
        Participants participants = getParticipants(InputView.inputNames(), deck);
        OutputView.printInitialCardStatus(participants);

        playBlackJackGame(participants, deck);
        OutputView.printAllParticipantsCards(participants);

        Dealer dealer = participants.extractDealer();
        List<Player> players = participants.extractPlayers();
        OutputView.printWinPrizeResult(getGameResultDto(players, dealer));
    }


    private Participants getParticipants(List<String> names, Deck deck) {
        List<Participant> participants = names.stream()
                .map(name -> new Player(name, 0, new Hit(deck.handOutInitCards())))
                .collect(Collectors.toList());
        bettingPlayer(participants);
        participants.add(0, new Dealer(new Hit(deck.handOutInitCards())));
        return new Participants(participants);
    }

    private void playBlackJackGame(Participants participants, Deck deck) {
        playAllPlayersTurn(participants.extractPlayers(), deck);
        playDealerTurn(participants.extractDealer(), deck);
    }

    private void playAllPlayersTurn(List<Player> players, Deck deck) {
        for (Player player : players) {
            playPlayerTurn(player, deck);
        }
    }

    private void playPlayerTurn(Player player, Deck deck) {
        while (!player.getStatus().isEndState()) {
            if (!InputView.inputAskMoreCard(player)) {
                player.stay();
                break;
            }

            player.receiveCard(deck.draw());
            OutputView.printParticipantCards(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (!dealer.getStatus().isEndState()) {
            OutputView.printMessage("딜러는 16이하라 한장의 카드를 더 받았습니다.");
            dealer.receiveCard(deck.draw());
        }
    }

    private void bettingPlayer(List<Participant> participantList) {
        for (Participant participant : participantList) {
            int bettingMoney = InputView.inputBettingMoney(participant);
            participant.betting(bettingMoney);
        }
    }

    public GameResultDto getGameResultDto(List<Player> players, Dealer dealer) {
        List<WinPrizeDto> playersWinPrizeDto = players.stream().map(
                player -> new WinPrizeDto(player.getName(), player.getWinPrize(dealer.getStatus()))
        ).collect(Collectors.toList());


        int totalPlayerWinPrize = players.stream().mapToInt(player -> player.getWinPrize(dealer.getStatus())).sum();
        WinPrizeDto dealerWinPrizeDto = new WinPrizeDto(dealer.getName(), totalPlayerWinPrize * -1);

        return new GameResultDto(dealerWinPrizeDto, playersWinPrizeDto);
    }
}
