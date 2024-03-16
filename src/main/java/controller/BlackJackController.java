package controller;

import domain.blackjack.BlackJackGame;
import domain.blackjack.Dealer;
import domain.blackjack.EarningMoney;
import domain.blackjack.Player;
import domain.blackjack.Players;
import domain.blackjack.WithOutFirstCardShowStrategy;
import domain.card.Card;
import domain.card.Deck;
import dto.DealerDTO;
import dto.GameResultDTO;
import dto.PlayerDTO;
import java.util.List;
import view.BattingMoneyInputView;
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
        initBlackJackGame(deck, blackJackGame);
        printPlayersAndDealerBeforeRunGame(blackJackGame);
        runGame(deck, blackJackGame);
        printBlackJackGameResults(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        List<String> playerNames = NameInputView.getNames();
        List<Integer> playersBattingMoney = BattingMoneyInputView.getMoney(playerNames);
        return new BlackJackGame(playerNames, playersBattingMoney);
    }

    private static void initBlackJackGame(Deck deck, BlackJackGame blackJackGame) {
        blackJackGame.initialDraw(deck);
        Players players = blackJackGame.getPlayers();
        List<String> playerNames = players.getPlayerNames();
        OutputView.printInitGameDoneMessage(playerNames);
    }

    private void printPlayersAndDealerBeforeRunGame(BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        printPlayers(players);
        Dealer dealer = blackJackGame.getDealer();
        printDealerWithoutFirstCard(dealer);
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
        List<Card> rawHoldingCards = dealer.getRawHoldingCards(WithOutFirstCardShowStrategy.INSTANCE);
        DealerDTO dealerDTO = new DealerDTO(rawHoldingCards, dealer.calculateSummationCardPointAsInt());
        DealerOutputView.printWithoutSummationCardPoint(dealerDTO);
    }

    private void runGame(Deck deck, BlackJackGame blackJackGame) {
        playersTryDraw(deck, blackJackGame);
        dealerTryDraw(deck, blackJackGame);
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

    private void printBlackJackGameResults(BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        Dealer dealer = blackJackGame.getDealer();
        printDealerWithPoint(dealer);
        printPlayersWithPoint(players);

        List<String> playerNames = players.getPlayerNames();
        List<EarningMoney> playersEarningMoney = blackJackGame.calculatePlayersEarningMoney();
        EarningMoney dealerEarningMoney = blackJackGame.calculateDealerEarningMoney();
        GameResultDTO gameResultDTO = new GameResultDTO(playerNames, playersEarningMoney, dealerEarningMoney);
        GameResultOutputView.print(gameResultDTO);
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
