package blackjack.controller;

import blackjack.domain.Dealer;
import blackjack.domain.Deck;
import blackjack.domain.GameResultBoard;
import blackjack.domain.Player;
import blackjack.domain.Players;
import blackjack.domain.card.Card;
import blackjack.domain.dto.PlayerDto;
import blackjack.domain.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    public static final int INITIAL_CARDS_COUNT = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Dealer dealer = new Dealer();
        List<String> playerNames = inputView.inputPlayerNames();
        Players players = Players.from(playerNames);
        Deck deck = Deck.createShuffledDeck();

        doInitialDraw(dealer, players, deck);
        outputView.printInitialMessage(playerNames);
        printInitialCards(dealer, players);

        for (Player player : players.getPlayers()) {
            doRound(player, deck);
        }

        dealer.drawUntilExceedMinimum(deck);
        printExtraDealerDraw(dealer);

        printCardStatus(dealer, players);
        printGameResult(dealer, players);
    }

    private void doRound(Player player, Deck deck) {
        while (!player.isBusted()) {
            String drawChoice = inputView.inputDrawChoice(player.getName());
            if (drawChoice.equals("n")) {
                break;
            }
            player.draw(deck);
            outputView.printPlayerCard(PlayerDto.from(player));
        }
    }

    private void printGameResult(Dealer dealer, Players players) {
        GameResultBoard gameResultBoard = new GameResultBoard(dealer, players.getPlayers());

        outputView.printDealerResult(gameResultBoard.getDealerResult());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerResult(player.getName(), gameResultBoard.getGameResult(player));
        }
    }

    private void printCardStatus(Dealer dealer, Players players) {
        PlayerResultDto dealerResult = PlayerResultDto.from(dealer.getPlayer());

        List<PlayerResultDto> playerResultDtos = players.getPlayers().stream()
                .map(PlayerResultDto::from)
                .toList();
        outputView.printCardStatus(dealerResult, playerResultDtos);
    }

    private void printExtraDealerDraw(Dealer dealer) {
        int dealerCardsCount = dealer.getCardsCount();
        int extraDrawCount = dealerCardsCount - INITIAL_CARDS_COUNT;
        if (extraDrawCount > 0) {
            outputView.printExtraDealerDraw(extraDrawCount);
        }
    }

    private void printInitialCards(Dealer dealer, Players players) {
        Card dealerCard = dealer.getFirstCard();
        outputView.printDealerInitialCard(dealerCard);

        List<PlayerDto> playerDtos = players.getPlayers().stream()
                .map(PlayerDto::from)
                .toList();
        outputView.printPlayerInitialCards(playerDtos);
    }

    private void doInitialDraw(Dealer dealer, Players players, Deck deck) {
        players.getPlayers().forEach(
                player -> drawCard(player, deck, INITIAL_CARDS_COUNT)
        );
        drawCard(dealer.getPlayer(), deck, INITIAL_CARDS_COUNT);
    }

    private void drawCard(Player player, Deck deck, int amount) {
        for (int i = 0; i < amount; i++) {
            player.draw(deck);
        }
    }
}
