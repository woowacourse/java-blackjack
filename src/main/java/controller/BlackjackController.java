package controller;

import static domain.blackjack.GameResultCalculator.calculate;
import static java.util.stream.Collectors.summingInt;

import domain.blackjack.DealerCardDrawCondition;
import domain.blackjack.DrawResult;
import domain.blackjack.GameResult;
import domain.blackjack.Gamer;
import domain.card.Card;
import domain.card.Deck;
import domain.card.RandomCardSelectStrategy;
import dto.DealerGameResultDTO;
import dto.GamerDTO;
import dto.PlayerGameResultDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.OutputView;
import view.YesOrNoInputView;
import view.gamer.GamerOutputView;
import view.gameresult.GameResultOutputView;

public class BlackjackController {
    private final Gamer dealer;
    private final List<Gamer> players;

    public BlackjackController(Gamer dealer, List<Gamer> players) {
        this.dealer = dealer;
        this.players = players;
    }

    public void startBlackjackGame(Deck deck) {
        initDealerAndPlayers(deck);
        printDealerAndPlayers();

        playersTryDraw(deck);
        dealerTryDraw(deck);

        printDealerWithPoint();
        printPlayersWithPoint();

        printDealerGameResult();
        printPlayersGameResult();
    }

    private void initDealerAndPlayers(Deck deck) {
        for (int index = 0; index < 2; index++) {
            dealerDraw(deck);
            players.forEach(player -> playerDraw(deck, player));
        }
        String namesOutput = players.stream().map(Gamer::getRawName).collect(Collectors.joining(", "));
        OutputView.print("딜러와 %s에게 2장을 나누었습니다.".formatted(namesOutput));
    }

    private DrawResult dealerDraw(Deck deck) {
        return dealer.draw(deck, new RandomCardSelectStrategy(), new DealerCardDrawCondition(dealer));
    }

    private DrawResult playerDraw(Deck deck, Gamer player) {
        return player.draw(deck, new RandomCardSelectStrategy(), new DealerCardDrawCondition(player));
    }

    private void printDealerAndPlayers() {
        printDealer(dealer);
        players.forEach(BlackjackController::printPlayer);
    }

    private static void printDealer(Gamer dealer) {
        List<Card> rawHoldingCards = new ArrayList<>(dealer.getRawHoldingCards());
        rawHoldingCards.remove(0);
        GamerDTO gamerDTO = new GamerDTO(dealer.getRawName(), rawHoldingCards,
                dealer.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private static void printPlayer(Gamer player) {
        GamerDTO gamerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private void playersTryDraw(Deck deck) {
        players.forEach(player -> playerTryDraw(deck, player));
    }

    private void playerTryDraw(Deck deck, Gamer player) {
        boolean hasNextDrawChance = true;
        while (hasNextDrawChance) {
            hasNextDrawChance = playerTryDrawOnce(deck, player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Gamer player) {
        OutputView.print("%s은(는) 한장의 카드를 더 받겠습니까?(예는 y, 아니오는 n)".formatted(player.getRawName()));
        boolean needToDraw = YesOrNoInputView.getYNAsBoolean();
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


    private void dealerTryDraw(Deck deck) {
        DrawResult drawResult = dealerDraw(deck);
        if (drawResult.isSuccess()) {
            OutputView.print("딜러는 16이하라 한장의 카드를 더 받았습니다.\n");
        }
    }

    private void printDealerWithPoint() {
        GamerDTO dealerDTO = new GamerDTO(dealer.getRawName(), dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        GamerOutputView.print(dealerDTO);
    }

    private void printPlayersWithPoint() {
        for (Gamer player : players) {
            GamerDTO playerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            GamerOutputView.print(playerDTO);
        }
    }

    private void printDealerGameResult() {
        Map<GameResult, Integer> dealerGameResultCounts = players.stream()
                .collect(Collectors.groupingBy(player -> calculate(dealer, player), summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);
        GameResultOutputView.print(dealerGameResultDTO);
    }

    private void printPlayersGameResult() {
        List<PlayerGameResultDTO> playerGameResultDTOS = players.stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(), calculate(player, dealer)))
                .toList();
        for (PlayerGameResultDTO playerGameResultDTO : playerGameResultDTOS) {
            GameResultOutputView.print(playerGameResultDTO);
        }
    }
}
