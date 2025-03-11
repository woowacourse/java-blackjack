package controller;

import static domain.BlackJackConstants.INITIAL_CARD_AMOUNT;
import static domain.BlackJackConstants.THRESHOLD;

import domain.Command;
import domain.FinalResult;
import domain.deck.Card;
import domain.deck.CardSetGenerator;
import domain.deck.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
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

    private static final String DEALER_NAME = "딜러";

    public void run() {
        try {
            final Players players = createPlayers();
            final Dealer dealer = createDealer();
            final Deck deck = generateDeck();
            setGame(players.getPlayers(), dealer, deck);
            printGameSetting(dealer, players);
            playGame(dealer, players.getPlayers(), deck);
            processFinalResult(dealer, players.getPlayers());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private Players createPlayers() {
        return new Players(readNicknames().stream()
                .map(Player::new)
                .toList());
    }

    private List<Nickname> readNicknames() {
        final String inputNicknames = InputView.readPlayerName();
        return Arrays.stream(inputNicknames.split(","))
                .map(Nickname::new)
                .toList();
    }

    private Deck generateDeck() {
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();
        final List<Card> cards = cardSetGenerator.generate();
        cardSetGenerator.shuffle(cards, new Random());
        return new Deck(cards);
    }

    private Dealer createDealer() {
        return new Dealer(new Nickname(DEALER_NAME));
    }

    private void setGame(final List<Player> players, Dealer dealer, final Deck deck) {
        players.forEach(player -> receiveInitialCards(player, deck));
        receiveInitialCards(dealer, deck);

    }

    private void receiveInitialCards(Gamer gamer, Deck deck) {
        final Card firstCard = deck.drawCard();
        final Card secondCard = deck.drawCard();
        gamer.receiveInitialCards(List.of(firstCard, secondCard));
    }

    private void printGameSetting(final Dealer dealer,
                                  final Players players) {
        final List<String> playerNicknames = players.getPlayersDisplayNicknames();
        OutputView.printInitialSettingMessage(dealer.getDisplayName(), playerNicknames, INITIAL_CARD_AMOUNT);
        OutputView.printCardsInHand(dealer.getDisplayName(), List.of(dealer.getFirstCard()));
        players.getPlayers().forEach(player -> OutputView.printCardsInHand(player.getDisplayName(), player.getCards()));
    }

    private void playGame(final Dealer dealer, final List<Player> players, final Deck deck) {
        processPlayerHit(players, deck);
        processDealerHit(dealer, deck);
    }

    private void processPlayerHit(final List<Player> players, final Deck deck) {
        players.stream()
                .filter(player -> isMoreCard(deck, player))
                .forEach(player -> processAdditionalHit(deck, player));
    }

    private boolean isMoreCard(final Deck deck, final Player player) {
        final String input = InputView.readQuestOneMoreCard(player.getDisplayName());
        if (Command.find(input).equals(Command.YES)) {
            final Card card = deck.drawCard();
            player.hit(card);
        }
        OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

        if (player.isBust()) {
            OutputView.printBustMessage(player.getDisplayName());
            return false;
        }

        return Command.find(input).equals(Command.YES);
    }

    private void processAdditionalHit(final Deck deck, final Player player) {
        String input = InputView.readQuestOneMoreCard(player.getDisplayName());
        Command command = Command.find(input);
        while (command.equals(Command.YES)) {
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
        while (dealer.canHit()) {
            final Card card = deck.drawCard();
            dealer.hit(card);

            OutputView.printDealerHit(THRESHOLD, dealer.getDisplayName());
        }
    }

    private void processFinalResult(final Dealer dealer, final List<Player> players) {
        OutputView.printCardsInHandWithResults(dealer, players);
        final Map<Player, FinalResult> playerResults = FinalResult.makePlayerResult(players, dealer);
        final Map<FinalResult, Integer> resultCounts = FinalResult.makeDealerResult(playerResults);
        OutputView.printFinalResults(dealer.getDisplayName(), resultCounts, playerResults);
    }
}
