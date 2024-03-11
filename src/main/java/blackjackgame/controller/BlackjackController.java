package blackjackgame.controller;

import blackjackgame.domain.blackjack.DealerRandomCardDrawStrategy;
import blackjackgame.domain.blackjack.GameResult;
import blackjackgame.domain.blackjack.GameResultCalculator;
import blackjackgame.domain.blackjack.Gamer;
import blackjackgame.domain.blackjack.Gamers;
import blackjackgame.domain.blackjack.PlayerRandomCardDrawStrategy;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Deck;
import blackjackgame.dto.CardDTO;
import blackjackgame.dto.DealerGameResultDTO;
import blackjackgame.dto.GamerDTO;
import blackjackgame.dto.PlayerGameResultDTO;
import blackjackgame.view.GameResultOutputView;
import blackjackgame.view.GamerOutputView;
import blackjackgame.view.OutputView;
import blackjackgame.view.YesOrNoInputView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {
    private static final int EXECUTION_COUNT = 2;

    private final Gamer dealer;
    private final Gamers players;

    public BlackjackController(Gamer dealer, Gamers players) {
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
        dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer), EXECUTION_COUNT);
        players.drawNTimes(deck, EXECUTION_COUNT);

        OutputView.printCardSplitMessage(dealer.getRawName(), players.getPlayersNames());
    }

    private void printDealerAndPlayers() {
        printDealer(dealer);
        printPlayers(players);
    }

    private void printDealer(Gamer dealer) {
        GamerDTO dealerDTO = makeGamerDTO(dealer);
        GamerOutputView.printOutputWithoutSummationCardPoint(dealerDTO);
    }

    private void printPlayers(Gamers players) {
        players.getPlayers().forEach(this::printPlayer);
    }

    private void printPlayer(Gamer player) {
        GamerDTO gamerDTO = makeGamerDTO(player);
        GamerOutputView.printOutputWithoutSummationCardPoint(gamerDTO);
    }

    private GamerDTO makeGamerDTO(Gamer gamer) {
        List<Card> rawHoldingCards = new ArrayList<>(gamer.getRawHoldingCards());
        List<CardDTO> cardDTOs = rawHoldingCards.stream()
                .map(card -> new CardDTO(card.name(), card.cardType()))
                .toList();
        return new GamerDTO(gamer.getRawName(), cardDTOs,
                gamer.getRawSummationCardPoint());
    }

    private void playersTryDraw(Deck deck) {
        players.getPlayers().forEach(player -> playerTryDraw(deck, player));
    }

    public void playerTryDraw(Deck deck, Gamer player) {
        while(!player.isDead() && inputYesOrNo(player)) {
            player.draw(deck, new PlayerRandomCardDrawStrategy(player));
            printPlayer(player);
        }
    }

    private static boolean inputYesOrNo(Gamer player) {
        OutputView.printPlayerAdditionalCardMessage(player.getRawName());
        return YesOrNoInputView.getYNAsBoolean();
    }

    private void dealerTryDraw(Deck deck) {
        try {
            dealer.draw(deck, new DealerRandomCardDrawStrategy(dealer));
            OutputView.printDealerAdditionalCardMessage();
        } catch (IllegalStateException e) {
            OutputView.printDealerNoAdditionalCardMessage();
        }
    }

    private void printDealerAndPlayersWithPoint() {
        GamerDTO dealerDTO = makeGamerDTO(dealer);
        GamerOutputView.generateOutputWithSummationCardPoint(dealerDTO);

        for (Gamer player : players.getPlayers()) {
            GamerDTO playerDTO = makeGamerDTO(player);
            GamerOutputView.generateOutputWithSummationCardPoint(playerDTO);
        }
    }

    private void printDealerAndPlayersGameResult() {
        Map<GameResult, Integer> dealerGameResultCounts = players.getPlayers().stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(dealer, player),
                        Collectors.summingInt(value -> 1)));
        DealerGameResultDTO dealerGameResultDTO = new DealerGameResultDTO(dealerGameResultCounts);

        List<PlayerGameResultDTO> playerGameResultDTOS = players.getPlayers().stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        GameResultCalculator.calculate(player, dealer)))
                .toList();

        OutputView.printGameResultMessage();
        GameResultOutputView.print(dealerGameResultDTO);
        playerGameResultDTOS.forEach(GameResultOutputView::print);
    }
}
