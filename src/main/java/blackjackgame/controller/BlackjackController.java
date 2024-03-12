package blackjackgame.controller;

import blackjackgame.domain.blackjack.BlackjackGame;
import blackjackgame.domain.blackjack.GameResult;
import blackjackgame.domain.blackjack.GameResultCalculator;
import blackjackgame.domain.blackjack.CardHolderGamer;
import blackjackgame.domain.blackjack.Gamers;
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

    private final CardHolderGamer dealer;
    private final Gamers players;

    private final BlackjackGame blackjackGame;

    public BlackjackController(CardHolderGamer dealer, Gamers players) {
        this.dealer = dealer;
        this.players = players;

        blackjackGame = new BlackjackGame(dealer, players);
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
        blackjackGame.initDealerAndPlayers(deck);
        OutputView.printCardSplitMessage(dealer.getRawName(), players.getRawPlayersNames());
    }

    private void printDealerAndPlayers() {
        printDealer(dealer);
        printPlayers(players);
    }

    private void printDealer(CardHolderGamer dealer) {
        GamerDTO dealerDTO = makeGamerDTO(dealer);
        GamerOutputView.printOutputWithoutSummationCardPoint(dealerDTO);
    }

    private void printPlayers(Gamers players) {
        players.getPlayers().forEach(this::printPlayer);
    }

    private void printPlayer(CardHolderGamer player) {
        GamerDTO gamerDTO = makeGamerDTO(player);
        GamerOutputView.printOutputWithoutSummationCardPoint(gamerDTO);
    }

    private GamerDTO makeGamerDTO(CardHolderGamer cardHolderGamer) {
        List<Card> rawHoldingCards = new ArrayList<>(cardHolderGamer.getRawHoldingCards());
        List<CardDTO> cardDTOs = rawHoldingCards.stream()
                .map(card -> new CardDTO(card.name(), card.cardType()))
                .toList();
        return new GamerDTO(cardHolderGamer.getRawName(), cardDTOs,
                cardHolderGamer.getRawSummationCardPoint());
    }

    private void playersTryDraw(Deck deck) {
        Gamers players = blackjackGame.getPlayers();
        players.getPlayers().forEach(player -> playerTryDraw(deck, player));
    }

    private void playerTryDraw(Deck deck, CardHolderGamer player) {
        while(!blackjackGame.isPlayerDead(player) && inputYesOrNo(player)) {
            blackjackGame.playerTryDraw(deck, player);
            printPlayer(player);
        }
    }

    private void dealerTryDraw(Deck deck) {
        blackjackGame.dealerTryDraw(deck);
    }

    private static boolean inputYesOrNo(CardHolderGamer player) {
        OutputView.printPlayerAdditionalCardMessage(player.getRawName());
        return YesOrNoInputView.getYNAsBoolean();
    }

    private void printDealerAndPlayersWithPoint() {
        GamerDTO dealerDTO = makeGamerDTO(dealer);
        GamerOutputView.generateOutputWithSummationCardPoint(dealerDTO);

        for (CardHolderGamer player : players.getPlayers()) {
            GamerDTO playerDTO = makeGamerDTO(player);
            GamerOutputView.generateOutputWithSummationCardPoint(playerDTO);
        }
    }

    private void printDealerAndPlayersGameResult() {
        OutputView.printGameResultMessage();
        GameResultOutputView.print(makeDealerGameResultDTO());

        List<PlayerGameResultDTO> playerGameResultDTOS = makePlayerGameResultDTO();
        playerGameResultDTOS.forEach(GameResultOutputView::print);
    }

    private DealerGameResultDTO makeDealerGameResultDTO() {
        Map<GameResult, Integer> dealerGameResultCounts = players.getPlayers().stream()
                .collect(Collectors.groupingBy(player -> GameResultCalculator.calculate(dealer, player),
                        Collectors.summingInt(value -> 1)));
        return new DealerGameResultDTO(dealerGameResultCounts);
    }

    private List<PlayerGameResultDTO> makePlayerGameResultDTO() {
        return players.getPlayers().stream()
                .map(player -> new PlayerGameResultDTO(player.getRawName(),
                        GameResultCalculator.calculate(player, dealer)))
                .toList();
    }
}
