package controller;

import domain.Command;
import domain.FinalResult;
import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.RandomShuffleStrategy;
import domain.gamer.Dealer;
import domain.gamer.Gamers;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final String DEALER_NAME = "딜러";

    public void run() {
        final Players players = generatePlayers(readNicknames());
        final Dealer dealer = generateDealer();
        final Gamers gamers = Gamers.of(players, dealer);
        final Deck deck = Deck.createShuffledDeck(new RandomShuffleStrategy());

        gamers.dealInitialCards(deck);
        printInitialSetting(gamers);

        processPlayerHit(players, deck);
        processDealerHit(dealer, deck);
        processFinalResult(dealer, players);
    }

    private List<Nickname> readNicknames() {
        final String inputNicknames = InputView.readPlayerName();
        return Arrays.stream(inputNicknames.split(","))
                .map(Nickname::new)
                .toList();
    }

    private Players generatePlayers(final List<Nickname> nicknames) {
        final List<Player> players = nicknames.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private Dealer generateDealer() {
        return new Dealer(new Nickname(DEALER_NAME));
    }

    private void printInitialSetting(final Gamers gamers) {
        OutputView.printCardsInHandAtFirst(gamers.getCardsAtStartWithNickname());
    }

    private void processPlayerHit(final Players players, final Deck deck) {
        for (final Player player : players.getPlayers()) {
            if (isNotMoreCard(deck, player)) {
                continue;
            }
            processAdditionalHit(deck, player);
        }
    }

    private void processDealerHit(final Dealer dealer, final Deck deck) {
        while (dealer.canHit()) {
            final Card card = deck.drawCard();
            dealer.hit(card);

            OutputView.printDealerHit(dealer.getDisplayName());
        }
    }

    private static boolean isNotMoreCard(final Deck deck, final Player player) {
        if (Command.isYes(readAdditionalHit(player))) {
            final Card card = deck.drawCard();
            player.hit(card);
        }
        OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

        if (player.isBust()) {
            OutputView.printBustMessage(player.getDisplayName());
            return true;
        }

        return Command.isNo(readAdditionalHit(player));
    }

    private static void processAdditionalHit(final Deck deck, final Player player) {
        while (Command.isYes(readAdditionalHit(player))) {
            final Card card = deck.drawCard();
            player.hit(card);
            OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

            if (player.isBust()) {
                OutputView.printBustMessage(player.getDisplayName());
                break;
            }
        }
    }

    private static String readAdditionalHit(final Player player) {
        return InputView.readQuestOneMoreCard(player.getDisplayName());
    }

    private void processFinalResult(final Dealer dealer, final Players players) {
        OutputView.printCardsInHandWithResults(dealer, players.getPlayers());

        final Map<Player, FinalResult> playerResults = FinalResult.makePlayerResult(players.getPlayers(), dealer);
        final Map<FinalResult, Integer> resultCounts = FinalResult.makeDealerResult(playerResults);
        OutputView.printFinalResults(dealer.getDisplayName(), resultCounts, playerResults);
    }
}
