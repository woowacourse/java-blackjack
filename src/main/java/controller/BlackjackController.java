package controller;

import domain.blackjack.DealerRandomCardDrawStrategy;
import domain.blackjack.GameResult;
import domain.blackjack.GameResultCalculator;
import domain.blackjack.Gamer;
import domain.blackjack.PlayerRandomCardDrawStrategy;
import domain.card.Card;
import domain.card.Deck;
import dto.DealerGameResultDTO;
import dto.GamerDTO;
import dto.PlayerGameResultDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import view.GameResultOutputView;
import view.GamerOutputView;
import view.OutputView;
import view.YesOrNoInputView;

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

        printDealerAndPlayersWithPoint();
        printDealerAndPlayersGameResult();
    }

    private void initDealerAndPlayers(Deck deck) {
        dealerDraw(deck);
        dealerDraw(deck);
        players.forEach(player -> playerDraw(deck, player));
        players.forEach(player -> playerDraw(deck, player));

        List<String> playerNames = players.stream()
                .map(Gamer::getRawName)
                .toList();
        OutputView.printCardSplitMessage(dealer.getRawName(), playerNames);
    }

    private void dealerDraw(Deck deck) {
        dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer));
    }

    private void playerDraw(Deck deck, Gamer player) {
        player.draw(deck, new PlayerRandomCardDrawStrategy(player));
    }

    private void printDealerAndPlayers() {
        printDealer(dealer);
        players.forEach(this::printPlayer);
    }

    private void printDealer(Gamer dealer) {
        List<Card> rawHoldingCards = new ArrayList<>(dealer.getRawHoldingCards());
        rawHoldingCards.remove(0);
        GamerDTO gamerDTO = new GamerDTO(dealer.getRawName(), rawHoldingCards,
                dealer.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private void printPlayer(Gamer player) {
        GamerDTO gamerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                player.getRawSummationCardPoint());
        GamerOutputView.printWithoutSummationCardPoint(gamerDTO);
    }

    private void playersTryDraw(Deck deck) {
        players.forEach(player -> playerTryDraw(deck, player));
    }

    private void playerTryDraw(Deck deck, Gamer player) {
        boolean needToDraw = true;
        while (needToDraw) {
            needToDraw = playerTryDrawOnce(deck, player);
        }
    }

    private boolean playerTryDrawOnce(Deck deck, Gamer player) {
        boolean needToDraw;
        OutputView.printPlayerAdditionalCardMessage(player.getRawName());
        needToDraw = YesOrNoInputView.getYNAsBoolean();
        if (needToDraw) {
            try {
                playerDraw(deck, player);
                printPlayer(player);
            } catch (IllegalStateException e) {
                return false;
            }
        }
        return needToDraw;
    }

    private void dealerTryDraw(Deck deck) {
        try {
            dealerDraw(deck);
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException ignored) {

        }
    }

    private void printDealerAndPlayersWithPoint() {
        GamerDTO dealerDTO = new GamerDTO(dealer.getRawName(), dealer.getRawHoldingCards(),
                dealer.getRawSummationCardPoint());
        GamerOutputView.print(dealerDTO);

        for (Gamer player : players) {
            GamerDTO playerDTO = new GamerDTO(player.getRawName(), player.getRawHoldingCards(),
                    player.getRawSummationCardPoint());
            GamerOutputView.print(playerDTO);
        }
    }

    private void printDealerAndPlayersGameResult() {
        Map<GameResult, Integer> dealerGameResultCounts = players.stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(dealer, player),
                        Collectors.summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);

        List<PlayerGameResultDTO> playerGameResultDTOS = players.stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        GameResultCalculator.calculate(player, dealer)))
                .toList();

        OutputView.printGameResultMessage();
        GameResultOutputView.print(dealerGameResultDTO);
        playerGameResultDTOS.forEach(GameResultOutputView::print);
    }
}
