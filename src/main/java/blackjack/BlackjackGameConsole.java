package blackjack;

import blackjack.domain.card.BlackjackCardFactory;
import blackjack.domain.card.Deck;
import blackjack.domain.card.RandomShuffler;
import blackjack.domain.participant.BetRecord;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Name;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.ProfitDetails;
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
        BetRecord betRecord = repeat(() -> inputView.readAmountOfBet(playerNames));

        ProfitDetails profitDetails = playBlackjack(playerNames, betRecord);
        outputView.printProfitDetails(profitDetails);
    }

    private ProfitDetails playBlackjack(List<Name> playerNames, BetRecord betRecord) {
        Deck deck = Deck.of(new BlackjackCardFactory(), new RandomShuffler());
        Players players = initializePlayersWithHand(playerNames, deck);
        Dealer dealer = initializeDealerWithHand(deck);
        outputView.printPlayersInitialHand(players, dealer);

        players = proceedPlayersTurn(players, deck);
        dealer = proceedDealerTurn(dealer, deck);
        outputView.printParticipantResult(players, dealer);

        return betRecord.calculateProfit(players, dealer);
    }

    private Players initializePlayersWithHand(List<Name> playerNames, Deck deck) {
        Players players = Players.createInitialPlayers(playerNames);
        return players.initializePlayersHands(deck);
    }

    private Dealer initializeDealerWithHand(Deck deck) {
        Dealer dealer = Dealer.createInitialStateDealer();
        return dealer.draw(deck);
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
