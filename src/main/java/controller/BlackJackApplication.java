package controller;

import domain.blackjack.BlackJackGame;
import domain.blackjack.Dealer;
import domain.blackjack.DrawResult;
import domain.blackjack.EarningMoney;
import domain.blackjack.Player;
import domain.blackjack.Players;
import domain.blackjack.WithOutFirstCardShowStrategy;
import domain.card.Card;
import domain.card.Deck;
import dto.GameResultDTO;
import dto.GamerDTO;
import java.util.List;
import view.BettingMoneyInputView;
import view.NameInputView;
import view.OutputView;
import view.YesOrNoInputView;
import view.gamer.GamerOutputView;
import view.gameresult.GameResultOutputView;

public class BlackJackApplication {
    public void start() {
        Deck deck = Deck.fullDeck();
        BlackJackGame blackJackGame = generateBlackJackGame();
        initBlackJackGame(deck, blackJackGame);
        printAllGamerBeforeRunGame(blackJackGame);
        runGame(deck, blackJackGame);
        printBlackJackGameResults(blackJackGame);
    }

    private BlackJackGame generateBlackJackGame() {
        List<String> playerNames = NameInputView.getNames();
        List<Integer> playersBattingMoney = BettingMoneyInputView.getMoney(playerNames);
        return new BlackJackGame(playerNames, playersBattingMoney);
    }

    private static void initBlackJackGame(Deck deck, BlackJackGame blackJackGame) {
        blackJackGame.initialDraw(deck);
        Players players = blackJackGame.getPlayers();
        List<String> playerNames = players.getPlayerNames();
        OutputView.printInitGameDoneMessage(playerNames);
    }

    private void printAllGamerBeforeRunGame(BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        printPlayers(players);
        Dealer dealer = blackJackGame.getDealer();
        printDealerWithoutFirstCard(dealer);
    }

    private void printPlayers(Players players) {
        for (Player player : players.getPlayers()) {
            printPlayer(player);
        }
    }

    private void printPlayer(Player player) {
        GamerDTO playerDTO = GamerDTO.playerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.calculateSummationCardPointAsInt());
        GamerOutputView.printWithoutSummationCardPoint(playerDTO);
    }

    private void printDealerWithoutFirstCard(Dealer dealer) {
        List<Card> rawHoldingCards = dealer.getRawHoldingCards(WithOutFirstCardShowStrategy.INSTANCE);
        GamerDTO dealerDTO = GamerDTO.dealerDTO(rawHoldingCards, dealer.calculateSummationCardPointAsInt());
        GamerOutputView.printWithoutSummationCardPoint(dealerDTO);
    }

    private void runGame(Deck deck, BlackJackGame blackJackGame) {
        playersTryDraw(deck, blackJackGame);
        dealerTryDraw(deck, blackJackGame);
    }

    private void playersTryDraw(Deck deck, BlackJackGame blackJackGame) {
        Players players = blackJackGame.getPlayers();
        for (Player player : players.getPlayers()) {
            playerDraw(deck, player);
        }
    }

    private void playerDraw(Deck deck, Player player) {
        boolean hasNextDrawChance = true;
        while (hasNextDrawChance) {
            hasNextDrawChance = playerTryDrawOnce(deck, player);
            printPlayer(player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Player player) {
        boolean needToDraw = YesOrNoInputView.getYNAsBoolean(player.getRawName());
        DrawResult drawResult = null;
        if (needToDraw) {
            drawResult = player.draw(deck);
        }
        if (drawResult == null) {
            return false;
        }
        return drawResult.hasNextChance();
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
        GamerDTO dealerDTO = GamerDTO.dealerDTO(dealer.getRawHoldingCards(),
                dealer.calculateSummationCardPointAsInt());
        GamerOutputView.print(dealerDTO);
    }

    private void printPlayersWithPoint(Players players) {
        for (Player player : players.getPlayers()) {
            GamerDTO playerDTO = GamerDTO.playerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.calculateSummationCardPointAsInt());
            GamerOutputView.print(playerDTO);
        }
    }
}
