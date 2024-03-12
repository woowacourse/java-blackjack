package controller;

import domain.blackjack.Dealer;
import domain.blackjack.DrawResult;
import domain.blackjack.GameResult;
import domain.blackjack.HoldingCards;
import domain.blackjack.Player;
import domain.blackjack.Players;
import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;
import dto.DealerDTO;
import dto.DealerGameResultDTO;
import dto.PlayerDTO;
import dto.PlayerGameResultDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;
import view.NameInputView;
import view.OutputView;
import view.YesOrNoInputView;
import view.gamer.DealerOutputView;
import view.gamer.PlayerOutputView;
import view.gameresult.GameResultOutputView;

public class BlackjackController {

    public void startBlackjackGame(Deck deck) {
        OutputView.printStartGame();
        final Dealer dealer = generateDealer();
        final Players players = generatePlayers();

        initialDraw(deck, dealer, players);
        printDealerAndPlayers(dealer, players);

        playersTryDraw(deck, players);
        dealerTryDraw(deck, dealer);

        printDealerWithPoint(dealer);
        printPlayersWithPoint(players);

        printDealerGameResult(dealer, players);
        printPlayersGameResult(dealer, players);
    }


    private Dealer generateDealer() {
        return Dealer.of(HoldingCards.of());
    }

    private Players generatePlayers() {
        return new Players(NameInputView.getNames());
    }

    private void initialDraw(Deck deck, Dealer dealer, Players players) {
        final int initialDrawCount = 2;
        IntStream.range(0, initialDrawCount).forEach(index -> {
            players.forEach(player -> playerDraw(deck, player));
            dealerDraw(deck, dealer);
        });
    }

    private DrawResult dealerDraw(Deck deck, Dealer dealer) {
        return dealer.draw(deck, RandomCardSelectStrategy.INSTANCE);
    }

    private DrawResult playerDraw(Deck deck, Player player) {
        return player.draw(deck, RandomCardSelectStrategy.INSTANCE);
    }

    private void printDealerAndPlayers(Dealer dealer, Players players) {
        printDealer(dealer);
        players.forEach(BlackjackController::printPlayer);
    }

    private static void printDealer(Dealer dealer) {
        List<Card> rawHoldingCards = dealer.getRawHoldingCardsWithoutFirstCard();
        DealerDTO dealerDTO = new DealerDTO(rawHoldingCards, dealer.getRawSummationCardPoint());
        DealerOutputView.printWithoutSummationCardPoint(dealerDTO);
    }

    private static void printPlayer(Player player) {
        PlayerDTO playerDTO = new PlayerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.getRawSummationCardPoint());
        PlayerOutputView.printWithoutSummationCardPoint(playerDTO);
    }

    private void playersTryDraw(Deck deck, Players players) {
        players.forEach(player -> playerTryDraw(deck, player));
    }

    private void playerTryDraw(Deck deck, Player player) {
        boolean hasNextDrawChance = true;
        while (hasNextDrawChance) {
            hasNextDrawChance = playerTryDrawOnce(deck, player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Player player) {
        boolean needToDraw = YesOrNoInputView.getYNAsBoolean(player.getRawName());
        DrawResult drawResult = null;
        if (needToDraw) {
            drawResult = playerDraw(deck, player);
        }
        printPlayer(player);
        if (drawResult == null) {
            return false;
        }
        return drawResult.hasNextChance();
    }

    private void dealerTryDraw(Deck deck, Dealer dealer) {
        DrawResult drawResult = dealerDraw(deck, dealer);
        if (drawResult.isSuccess()) {
            OutputView.printDealerDrawDone();
        }
    }

    private void printDealerWithPoint(Dealer dealer) {
        DealerDTO dealerDTO = new DealerDTO(dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        DealerOutputView.print(dealerDTO);
    }

    private void printPlayersWithPoint(Players players) {
        players.forEach(player -> {
            PlayerDTO playerDTO = new PlayerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            PlayerOutputView.print(playerDTO);
        });
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
}
