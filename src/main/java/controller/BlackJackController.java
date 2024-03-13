package controller;

import domain.blackjack.BlackJackGame;
import domain.blackjack.Dealer;
import domain.blackjack.GameResult;
import domain.blackjack.Player;
import domain.blackjack.Players;
import domain.card.Card;
import domain.card.Deck;
import dto.DealerDTO;
import dto.DealerGameResultDTO;
import dto.PlayerDTO;
import dto.PlayerGameResultDTO;
import java.util.List;
import java.util.Map;
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
        OutputView.printInitGameDoneMessage(players.getPlayerNames());

        printPlayers(players);
        printDealerWithoutFirstCard(dealer);

        playersTryDraw(deck, blackJackGame);
        dealerTryDraw(deck, blackJackGame);

        printDealerWithPoint(dealer);
        printPlayersWithPoint(players);

        printDealerGameResult(dealer, players);
        printPlayersGameResult(dealer, players);
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

    private void printDealerGameResult(Dealer dealer, Players players) {
        Map<GameResult, Integer> dealerGameResultCounts = dealer.calculateGameResultWithPlayers(players);
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);
        GameResultOutputView.print(dealerGameResultDTO);
    }

    private void printPlayersGameResult(Dealer dealer, Players players) {
        List<PlayerGameResultDTO> playerGameResultDTOs = players.calculateGameResultsWithAsMap(dealer)
                .entrySet().stream()
                .map(entry -> new PlayerGameResultDTO(entry.getKey(), entry.getValue()))
                .toList();
        GameResultOutputView.print(playerGameResultDTOs);
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
