package blackjack.controller;

import blackjack.domain.BlackJackGame;
import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.Player;
import blackjack.domain.Players;
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

        outputView.printInitDraw(players, dealer);

        playerTurn(players, deck);

        dealerTurn(dealer, deck);

        outputView.printFinalCardResult(dealer, players);

        List<PlayerGameResultDto> playerGameResultDtos = new ArrayList<>();
        for (Player player : players.getPlayers()) {
            playerGameResultDtos.add(new PlayerGameResultDto(player.getName(), player.compareResult(dealer).getName()));
        }
        outputView.printFinalGameResult(playerGameResultDtos);


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

                outputView.printCard(player);

                if (!isHit) {
                    break;
                }
            }
        }
    }
}
