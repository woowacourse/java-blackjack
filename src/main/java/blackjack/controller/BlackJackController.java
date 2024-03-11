package blackjack.controller;

import blackjack.domain.participant.Dealer;
import blackjack.domain.card.Deck;
import blackjack.domain.game.GameProfitBoard;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerMeta;
import blackjack.domain.participant.Players;
import blackjack.domain.card.Card;
import blackjack.domain.participant.dto.PlayerDto;
import blackjack.domain.participant.dto.PlayerResultDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackJackController {
    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        Dealer dealer = new Dealer();
        Players players = initPlayers();
        Deck deck = Deck.createShuffledDeck();

        playGame(dealer, players, deck);
        printGameResult(dealer, players);
    }

    private Players initPlayers() {
        List<String> playerNames = inputView.inputPlayerNames();
        List<PlayerMeta> playerMetas = new ArrayList<>();
        for (String name : playerNames) {
            int betAmount = inputView.inputBetAmount(name);
            playerMetas.add(new PlayerMeta(name, betAmount));
        }
        return Players.from(playerMetas);
    }

    private void playGame(Dealer dealer, Players players, Deck deck) {
        doInitialDraw(dealer, players, deck);

        players.getPlayers().forEach(
                player -> doRound(player, deck)
        );
        doDealerRound(dealer, deck);
    }

    private void doInitialDraw(Dealer dealer, Players players, Deck deck) {
        players.initialDraw(deck);
        dealer.initialDraw(deck);

        outputView.printInitialMessage(players.getPlayerNames());
        printInitialCards(dealer, players);
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
        while (!player.isBusted() && hasAdditionalCardRequest(player)) {
            player.draw(deck);
            outputView.printPlayerCard(PlayerDto.from(player));
        }
    }

    private boolean hasAdditionalCardRequest(Player player) {
        return inputView.inputDrawChoice(player.getName());
    }

    private void doDealerRound(Dealer dealer, Deck deck) {
        dealer.drawUntilExceedMinimum(deck);
        outputView.printExtraDealerDraw(dealer.getExtraCardsCount());
    }

    private void printGameResult(Dealer dealer, Players players) {
        printCardStatus(dealer, players);
        GameProfitBoard gameProfitBoard = new GameProfitBoard(dealer, players);

        outputView.printDealerProfit(gameProfitBoard.getDealerProfit());
        for (Player player : players.getPlayers()) {
            outputView.printPlayerProfit(player.getName(), gameProfitBoard.getProfitOf(player));
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
