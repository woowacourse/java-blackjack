package controller;

import static domain.blackjack.GameResultCalculator.calculate;
import static java.util.stream.Collectors.summingInt;

import domain.blackjack.Dealer;
import domain.blackjack.DrawResult;
import domain.blackjack.GameResult;
import domain.blackjack.HoldingCards;
import domain.blackjack.Player;
import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;
import dto.DealerDTO;
import dto.DealerGameResultDTO;
import dto.PlayerDTO;
import dto.PlayerGameResultDTO;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
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
        final List<Player> players = generatePlayers();

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

    private List<Player> generatePlayers() {
        return NameInputView.getNames().stream()
                .map(name -> Player.from(name, HoldingCards.of()))
                .toList();
    }

    private void initialDraw(Deck deck, Dealer dealer, List<Player> players) {
        final int initialDrawCount = 2;
        IntStream.range(0, initialDrawCount).forEach(index -> {
            players.forEach(player -> playerDraw(deck, player));
            dealerDraw(deck, dealer);
        });
    }

    private DrawResult dealerDraw(Deck deck, Dealer dealer) {
        return dealer.draw(deck, new RandomCardSelectStrategy());
    }

    private DrawResult playerDraw(Deck deck, Player player) {
        return player.draw(deck, new RandomCardSelectStrategy());
    }

    private void printDealerAndPlayers(Dealer dealer, List<Player> players) {
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

    private void playersTryDraw(Deck deck, List<Player> players) {
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

    private void printPlayersWithPoint(List<Player> players) {
        for (Player player : players) {
            PlayerDTO playerDTO = new PlayerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            PlayerOutputView.print(playerDTO);
        }
    }

    private void printDealerGameResult(Dealer dealer, List<Player> players) {
        Map<GameResult, Integer> dealerGameResultCounts = players.stream()
                .collect(Collectors.groupingBy(player -> calculate(dealer.getGamer(), player.getGamer()),
                        summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);
        GameResultOutputView.print(dealerGameResultDTO);
    }

    private void printPlayersGameResult(Dealer dealer, List<Player> players) {
        List<PlayerGameResultDTO> playerGameResultDTOS = players.stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        calculate(player.getGamer(), dealer.getGamer())))
                .toList();
        for (PlayerGameResultDTO playerGameResultDTO : playerGameResultDTOS) {
            GameResultOutputView.print(playerGameResultDTO);
        }
    }
}
