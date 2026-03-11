package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Card;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Participant;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.dto.ParticipantDto;
import blackjack.dto.PlayerGameResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    InputView inputView = new InputView();
    OutputView outputView = new OutputView();

    public void run() {
        List<String> names = inputView.readNames();
        Players players = new Players(names);
        Dealer dealer = new Dealer();
        Deck deck = new Deck();

        BlackJackGame blackJackGame = new BlackJackGame(players, dealer, deck);
        blackJackGame.initDraw();

        printInitDrawResult(dealer, players);

        playerTurn(players, deck);

        dealerTurn(dealer, deck);

        printFinalCardResult(dealer, players);

        printFinalGameResult(dealer, players);
    }

    private ParticipantDto convertToDto(Participant participant) {
        List<String> cardNames = participant.getCards().stream().map(Card::getName).toList();
        return new ParticipantDto(participant.getName(), cardNames, participant.getTotalPoint());
    }

    private void dealerTurn(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            outputView.printDealerDraw();
            dealer.receiveCard(deck.draw());
        }
    }

    private void playerTurn(Players players, Deck deck) {
        for (Player player : players.getPlayers()) {
            while (!player.isBust()) {
                boolean isHit = inputView.readHitAnswer(player.getName());
                if (isHit) {
                    player.receiveCard(deck.draw());
                }

                outputView.printCard(convertToDto(player));

                if (!isHit) {
                    break;
                }
            }
        }
    }

    private void printInitDrawResult(Dealer dealer, Players players) {
        ParticipantDto dealerDto = convertToDto(dealer);
        List<ParticipantDto> playerDtos = players.getPlayers().stream().map(this::convertToDto).toList();
        outputView.printInitDraw(dealerDto, playerDtos);
    }

    private void printFinalCardResult(Dealer dealer, Players players) {
        ParticipantDto dealerDto = convertToDto(dealer);
        List<ParticipantDto> playerDtos = players.getPlayers().stream().map(this::convertToDto).toList();
        outputView.printFinalCardResult(dealerDto, playerDtos);
    }

    private void printFinalGameResult(Dealer dealer, Players players) {
        List<PlayerGameResultDto> playerGameResultDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerGameResultDtos.add(new PlayerGameResultDto(player.getName(), player.compareResult(dealer).getName()));
        }
        outputView.printFinalGameResult(playerGameResultDtos);
    }
}
