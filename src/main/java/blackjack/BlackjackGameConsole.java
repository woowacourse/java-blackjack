package blackjack;

import blackjack.domain.card.BlackjackCardsFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomShuffler;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.PlayerBetAmounts;
import blackjack.domain.participant.PlayerProfitAmounts;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class BlackjackGameConsole {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void play() {
        List<Name> playerNames = repeat(inputView::readPlayerNames);
        PlayerBetAmounts playerBetAmounts = repeat(() -> inputView.readBetAmount(playerNames));

        PlayerProfitAmounts playerProfitAmounts = playBlackjack(playerNames, playerBetAmounts);
        outputView.printProfitDetails(playerProfitAmounts);
    }

    private PlayerProfitAmounts playBlackjack(List<Name> playerNames, PlayerBetAmounts playerBetAmounts) {
        Deck deck = Deck.of(new BlackjackCardsFactory(), new RandomShuffler());
        Players players = initializePlayers(playerNames, deck);
        Dealer dealer = initializeDealer(deck);
        outputView.printPlayersInitialHand(players, dealer);

        players = proceedPlayersTurn(players, deck);
        dealer = proceedDealerTurn(dealer, deck);
        outputView.printParticipantResult(players, dealer);

        return PlayerProfitAmounts.calculateProfit(players, dealer, playerBetAmounts);
    }

    private Players initializePlayers(List<Name> playerNames, Deck deck) {
        Players players = Players.createInitialPlayers(playerNames);
        return players.takeFirstHand(deck);
    }

    private Dealer initializeDealer(Deck deck) {
        Dealer dealer = Dealer.createInitialStateDealer();
        return dealer.takeFirstHand(deck);
    }

    private Players proceedPlayersTurn(Players players, Deck deck) {
        return players.getPlayers().stream()
                .map(player -> {
                    if (player.isFinished()) {
                        return player;
                    }
                    return proceedPlayerTurn(player, deck);
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), Players::updatePlayers));
    }

    private Player proceedPlayerTurn(Player player, Deck deck) {
        boolean hit = inputView.readHitOrStand(player);
        player = player.decideHitOrStand(hit, deck);
        outputView.printParticipantHand(player);
        if (player.isFinished()) {
            return player;
        }
        return proceedPlayerTurn(player, deck);
    }

    private Dealer proceedDealerTurn(Dealer dealer, Deck deck) {
        dealer = dealer.decideHitOrStand(deck);
        if (dealer.isFinished()) {
            return dealer;
        }
        outputView.printDealerDraw();
        return proceedDealerTurn(dealer, deck);
    }

    private <T> T repeat(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                outputView.printError(e.getMessage());
            }
        }
    }
}
