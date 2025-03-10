package controller;

import domain.Command;
import domain.FinalResult;
import domain.deck.Card;
import domain.deck.Deck;
import domain.deck.DeckGenerator;
import domain.gamer.Dealer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import view.InputView;
import view.OutputView;

public class BlackJackController {

    private static final int INITIAL_CARD_AMOUNT = 2;
    private static final String DEALER_NAME = "딜러";
    public static final int THRESHOLD = 16;
    public static final int BUST_NUMBER = 21;

    public void run() {
        try {
            final Players players = createPlayers();
            final Deck deck = generateDeck();
            final Dealer dealer = setupDealer(deck);

            printInitialGameState(dealer, players);
            playGame(dealer, players.getPlayers(), deck);
            showResults(dealer, players.getPlayers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private Players createPlayers() {
        final List<Nickname> nicknames = readNicknames();
        return generatePlayers(nicknames);
    }

    private Players generatePlayers(final List<Nickname> nicknames) {
        final List<Player> players = nicknames.stream()
                .map(Player::new)
                .toList();

        return new Players(players);
    }

    private List<Nickname> readNicknames() {
        final String inputNicknames = InputView.readPlayerName();
        return Arrays.stream(inputNicknames.split(","))
                .map(Nickname::new)
                .toList();
    }

    private Deck generateDeck() {
        final DeckGenerator deckGenerator = new DeckGenerator();
        final List<Card> cards = deckGenerator.generate();
        deckGenerator.shuffle(cards, new Random());
        return new Deck(cards);
    }

    private Dealer setupDealer(final Deck deck) {
        final Dealer dealer = generateDealer();
        dealer.receiveInitialCards(List.of(deck.drawCard(), deck.drawCard()));
        return dealer;
    }

    private Dealer generateDealer() {
        return new Dealer(new Nickname(DEALER_NAME));
    }

    private void printInitialGameState(final Dealer dealer, final Players players) {
        final List<Player> playerGroup = players.getPlayers();
        final List<Nickname> nicknames = playerGroup.stream()
                .map(player -> new Nickname(player.getDisplayName()))
                .toList();

        printGameSetting(dealer, nicknames, playerGroup);
    }

    private void playGame(final Dealer dealer, final List<Player> players, final Deck deck) {
        setGame(players, deck);
        processPlayerHit(players, deck);
        processDealerHit(dealer, deck);
    }

    private void setGame(final List<Player> players, final Deck deck) {
        players.forEach(player -> {
            final Card firstCard = deck.drawCard();
            final Card secondCard = deck.drawCard();
            player.receiveInitialCards(List.of(firstCard, secondCard));
        });
    }

    private void processPlayerHit(final List<Player> players, final Deck deck) {
        for (final Player player : players) {
            if (isNotMoreCard(deck, player)) {
                continue;
            }
            processAdditionalHit(deck, player);
        }
    }

    private static boolean isNotMoreCard(final Deck deck, final Player player) {
        final String input = InputView.readQuestOneMoreCard(player.getDisplayName());
        if (Command.isYes(input)) {
            final Card card = deck.drawCard();
            player.hit(card);
        }
        OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

        if (player.isBust()) {
            OutputView.printBustMessage(player.getDisplayName());
            return true;
        }

        return Command.isNo(input);
    }

    private static void processAdditionalHit(final Deck deck, final Player player) {
        while (Command.isYes(InputView.readQuestOneMoreCard(player.getDisplayName()))) {
            final Card card = deck.drawCard();
            player.hit(card);
            OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

            if (player.isBust()) {
                OutputView.printBustMessage(player.getDisplayName());
                break;
            }
        }
    }

    private void processDealerHit(final Dealer dealer, final Deck deck) {
        while (dealer.canHit(THRESHOLD)) {
            final Card card = deck.drawCard();
            dealer.hit(card);

            OutputView.printDealerHit(THRESHOLD, dealer.getDisplayName());
        }
    }

    private void showResults(final Dealer dealer, final List<Player> players) {
        processFinalResult(dealer, players);
    }

    private void printGameSetting(final Dealer dealer, final List<Nickname> nicknames,
                                  final List<Player> players) {
        OutputView.printInitialSettingMessage(dealer.getDisplayName(), nicknames, INITIAL_CARD_AMOUNT);
        OutputView.printCardsInHand(dealer.getDisplayName(), List.of(dealer.getFirstCard()));
        players.forEach(player -> OutputView.printCardsInHand(player.getDisplayName(), player.getCards()));
    }

    private void processFinalResult(final Dealer dealer, final List<Player> players) {
        OutputView.printCardsInHandWithResults(dealer, players);
        final Map<Player, FinalResult> playerResults = FinalResult.makePlayerResult(players, dealer);
        final Map<FinalResult, Integer> resultCounts = FinalResult.makeDealerResult(playerResults);
        OutputView.printFinalResults(dealer.getDisplayName(), resultCounts, playerResults);
    }
}
