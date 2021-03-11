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
        OutputView.printAllParticipantsCards(participants);
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

            player.handOutCard(deck.draw());
            OutputView.printParticipantCards(player);
        }
    }

    private void playDealerTurn(Dealer dealer, Deck deck) {
        while (!dealer.getStatus().isEndState()) {
            OutputView.printMessage(MSG_DEALER_GET_MORE_CARD);
            dealer.handOutCard(deck.draw());
        }
    }

    private void bettingPlayer(List<Participant> participantList) {
        for (Participant participant : participantList) {
            int bettingMoney = InputView.inputBettingMoney(participant);
            participant.betting(bettingMoney);
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
                .map(player -> new WinPrizeDto(player.getName(), player.calculateWinPrize(dealer.getStatus())))
                .collect(Collectors.toList());
    }

    private int getTotalPlayerWinPrize(List<Player> players, Dealer dealer) {
        return players.stream()
                .mapToInt(player -> player.calculateWinPrize(dealer.getStatus()))
                .sum();
    }
}
