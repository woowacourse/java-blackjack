package blackjack.controller;

import blackjack.domain.player.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.result.BettingBoard;
import blackjack.domain.result.GameResultBoard;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.domain.result.Money;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private static final int INITIAL_CARDS_COUNT = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Dealer dealer = new Dealer();
        Players players = new Players(inputView.inputPlayerNames());
        BettingBoard bettingBoard = new BettingBoard();
        Deck deck = Deck.createShuffledDeck();

        betMoney(bettingBoard, players);
        playGame(dealer, players, deck);
        printGameResult(dealer, players, bettingBoard);
    }

    private void betMoney(BettingBoard bettingBoard, Players players) {
        for (Player player : players.getPlayers()) {
            bettingBoard.bet(player.getPlayerName(), new Money(inputView.inputMoney(player.getName())));
        }
    }

    private void playGame(Dealer dealer, Players players, Deck deck) {
        doInitialDraw(dealer, players, deck);

        players.getPlayers().forEach(
                player -> doRound(player, deck)
        );
        if (players.isAllBusted()) {
            outputView.printDealerDoesNotDraw();
            return;
        }
        doDealerRound(dealer, deck);
    }

    private void doInitialDraw(Dealer dealer, Players players, Deck deck) {
        players.getPlayers().forEach(
                player -> drawInitialCard(player, deck)
        );
        drawInitialCard(dealer.getPlayer(), deck);

        outputView.printInitialMessage(players.getPlayerNames());
        printInitialCards(dealer, players);
    }

    private void drawInitialCard(Player player, Deck deck) {
        for (int i = 0; i < INITIAL_CARDS_COUNT; i++) {
            player.hit(deck);
        }
    }

    private void printInitialCards(Dealer dealer, Players players) {
        List<Card> dealerCards = dealer.getCards();
        outputView.printDealerInitialCard(dealerCards.get(0));

        List<PlayerDto> playerDtos = players.getPlayers().stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printPlayerInitialCards(playerDtos);
    }

    private void doRound(Player player, Deck deck) {
        while (shouldHit(player)) {
            player.hit(deck);
            outputView.printPlayerCard(PlayerDto.from(player));
        }
    }

    private boolean shouldHit(Player player) {
        return player.shouldHit() && hasAdditionalCardRequest(player);
    }

    private boolean hasAdditionalCardRequest(Player player) {
        return inputView.inputDrawChoice(player.getName());
    }

    private void doDealerRound(Dealer dealer, Deck deck) {
        dealer.drawUntilExceedMinimum(deck);
        printExtraDealerDraw(dealer);
    }

    private void printExtraDealerDraw(Dealer dealer) {
        int dealerCardsCount = dealer.getCardsCount();
        int extraDrawCount = dealerCardsCount - INITIAL_CARDS_COUNT;
        if (extraDrawCount > 0) {
            outputView.printExtraDealerDraw(extraDrawCount);
        }
    }

    private void printGameResult(Dealer dealer, Players players, BettingBoard bettingBoard) {
        printCardStatus(dealer, players);
        GameResultBoard gameResultBoard = new GameResultBoard(dealer, players.getPlayers());
        bettingBoard.calculateMoney(gameResultBoard.getResultBoard());

        Money dealerMoney = bettingBoard.calculateDealerMoney();
        outputView.printDealerMoney(dealerMoney.money());
        for (Player player : players.getPlayers()) {
            Money playerMoney = bettingBoard.findByPlayerName(player.getPlayerName());
            outputView.printPlayerMoney(player.getName(), playerMoney.money());
        }
    }

    private void printCardStatus(Dealer dealer, Players players) {
        PlayerResultDto dealerResult = PlayerResultDto.from(dealer.getPlayer());

        List<PlayerResultDto> playerResultDtos = players.getPlayers().stream()
                .map(PlayerResultDto::from)
                .toList();
        outputView.printCardStatus(dealerResult, playerResultDtos);
    }
}
