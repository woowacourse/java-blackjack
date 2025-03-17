package controller;

import domain.BlackJackGame;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.gamer.BetAmount;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Nicknames;
import domain.gamer.Player;
import domain.state.type.HittableState;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String DEALER_NAME = "딜러";

    public void run() {
        final Nicknames nicknames = readNicknames();
        final List<Player> players = generatePlayers(nicknames);
        final Dealer dealer = generateDealer();
        final Deck deck = Deck.createShuffledDeck(new RandomShuffleStrategy());
        final BlackJackGame blackJackGame = new BlackJackGame(deck);

        dealInitialCards(blackJackGame, players, dealer);
        OutputView.printCardsInHandAtFirst(players, dealer);

        processHit(players, blackJackGame, dealer);
        OutputView.printCardsInHandWithResult(dealer, players);

        final Map<String, Double> playersProfit = blackJackGame.calculatePlayersProfit(dealer, players);
        final double dealerProfit = blackJackGame.calculateDealerProfit(playersProfit);
        OutputView.printProfitResult(playersProfit, dealerProfit);
    }

    private Nicknames readNicknames() {
        final String inputNicknames = InputView.readPlayerName();
        final List<Nickname> nicknames = Arrays.stream(inputNicknames.split(","))
                .map(Nickname::new)
                .toList();
        return new Nicknames(nicknames);
    }

    private List<Player> generatePlayers(final Nicknames nicknames) {
        return nicknames.getNicknames()
                .stream()
                .map(nickname -> {
                    final String betAmount = InputView.readBetAmount(nickname);
                    return new Player(nickname, HittableState.initializePlayerState(), BetAmount.of(betAmount));
                })
                .toList();
    }

    private Dealer generateDealer() {
        return new Dealer(new Nickname(DEALER_NAME), HittableState.initializeDealerState());
    }

    private static void dealInitialCards(final BlackJackGame blackJackGame, final List<Player> players,
                                         final Dealer dealer) {
        players.forEach(blackJackGame::dealInitialCards);
        blackJackGame.dealInitialCards(dealer);
    }

    private static void processHit(final List<Player> players, final BlackJackGame blackJackGame, final Dealer dealer) {
        players.forEach(player -> {
            blackJackGame.hit(player, InputView::readQuestOneMoreCard, OutputView::printCardsInHand);
        });
        blackJackGame.hitUntilUnder16(dealer, OutputView::printDealerHit);
    }
}
