package controller;

import domain.blackjack.BlackJackGame;
import domain.blackjack.Dealer;
import domain.blackjack.Player;
import domain.blackjack.Players;
import domain.card.Card;
import domain.card.Deck;
import dto.DealerDTO;
import dto.GameResultDTO;
import dto.PlayerDTO;
import java.util.List;
import view.NameInputView;
import view.OutputView;
import view.YesOrNoInputView;
import view.gamer.DealerOutputView;
import view.gamer.PlayerOutputView;
import view.gameresult.GameResultOutputView;

public class BlackJackController {
    public void start() {
        Deck deck = Deck.fullDeck();
        BlackJackGame blackJackGame = generateBlackJackGame();
        blackJackGame.initialDraw(deck);

        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        List<String> playerNames = players.getPlayerNames();
        OutputView.printInitGameDoneMessage(playerNames);

        printPlayers(players);
        printDealerWithoutFirstCard(dealer);

        playersTryDraw(deck, blackJackGame);
        dealerTryDraw(deck, blackJackGame);

        printDealerWithPoint(dealer);
        printPlayersWithPoint(players);

        GameResultDTO gameResultDTO = new GameResultDTO(playerNames, blackJackGame.calculatePlayersBettingMoney());
        GameResultOutputView.print(gameResultDTO);
    }

    private BlackJackGame generateBlackJackGame() {
        List<String> playerNames = NameInputView.getNames();
        return new BlackJackGame(playerNames);
    }

    private void printPlayers(Players players) {
        players.forEach(this::printPlayer);
    }

    private void printPlayer(Player player) {
        PlayerDTO playerDTO = new PlayerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.calculateSummationCardPointAsInt());
        PlayerOutputView.printWithoutSummationCardPoint(playerDTO);
    }

    private void printDealerWithoutFirstCard(Dealer dealer) {
        List<Card> rawHoldingCards = dealer.getRawHoldingCardsWithoutFirstCard();
        DealerDTO dealerDTO = new DealerDTO(rawHoldingCards, dealer.calculateSummationCardPointAsInt());
        DealerOutputView.printWithoutSummationCardPoint(dealerDTO);
    }

    private void playersTryDraw(Deck deck, BlackJackGame blackJackGame) {
        blackJackGame.playersDraw(deck, this::printPlayer, YesOrNoInputView::getYNAsBoolean);
    }

    private void dealerTryDraw(Deck deck, BlackJackGame blackJackGame) {
        boolean isDealerDraw = blackJackGame.dealerTryDraw(deck);
        if (isDealerDraw) {
            OutputView.printDealerDrawDone();
        }
    }

    private void printDealerWithPoint(Dealer dealer) {
        DealerDTO dealerDTO = new DealerDTO(dealer.getRawHoldingCards(),
                dealer.calculateSummationCardPointAsInt());
        DealerOutputView.print(dealerDTO);
    }

    private void printPlayersWithPoint(Players players) {
        players.forEach(player -> {
            PlayerDTO playerDTO = new PlayerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.calculateSummationCardPointAsInt());
            PlayerOutputView.print(playerDTO);
        });
    }
}
