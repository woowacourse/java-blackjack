package controller;

import static domain.BlackJackConstants.INITIAL_CARD_AMOUNT;
import static domain.BlackJackConstants.THRESHOLD;

import domain.Command;
import domain.FinalResults;
import domain.deck.Card;
import domain.deck.CardSetGenerator;
import domain.deck.Deck;
import domain.gamer.Betting;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Nickname;
import domain.gamer.Player;
import domain.gamer.Players;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public void run() {
        try {
            final Players players = createPlayers();
            final Dealer dealer = createDealer();
            final Deck deck = generateDeck();
            setGame(players, dealer, deck);
            printGameSetting(dealer, players);
            playGame(dealer, players, deck);
            finishGame(players, dealer);
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
        }
    }

    private Players createPlayers() {
        List<Nickname> nicknames = readNicknames();
        return new Players(nicknames.stream()
                .map(nickname -> {
                    Betting betting = readBetAmount(nickname.getDisplayName());
                    return new Player(nickname, betting);
                })
                .toList());
    }

    private List<Nickname> readNicknames() {
        final String inputNicknames = InputView.readPlayerName();
        return Arrays.stream(inputNicknames.split(","))
                .map(Nickname::new)
                .toList();
    }

    private Betting readBetAmount(final String playerName) {
        return new Betting(Integer.parseInt(InputView.readPlayerBetAmount(playerName)));
    }

    private Deck generateDeck() {
        final CardSetGenerator cardSetGenerator = new CardSetGenerator();
        final List<Card> cards = cardSetGenerator.generate();
        cardSetGenerator.shuffle(cards, new Random());
        return new Deck(cards);
    }

    private Dealer createDealer() {
        return new Dealer();
    }

    private void setGame(final Players players, Dealer dealer, final Deck deck) {
        players.receiveInitialCards(deck);
        List<Card> initialCards = deck.getInitialGameCards();
        dealer.receiveInitialCards(initialCards);
    }

    private void printGameSetting(final Dealer dealer, final Players players) {
        final List<String> playerNicknames = players.getPlayersDisplayNicknames();
        OutputView.printInitialSettingMessage(dealer.getDisplayName(), playerNicknames, INITIAL_CARD_AMOUNT);
        OutputView.printCardsInHand(dealer.getDisplayName(), List.of(dealer.getFirstCard()));
        players.getPlayers().forEach(player -> OutputView.printCardsInHand(player.getDisplayName(), player.getCards()));
    }

    private void playGame(final Dealer dealer, final Players players, final Deck deck) {
        processPlayerHit(players, deck);
        processDealerHit(dealer, deck);
    }

    private void processPlayerHit(final Players players, final Deck deck) {
        players.getPlayers().stream()
                .filter(player -> isMoreCard(deck, player))
                .forEach(player -> processAdditionalHit(deck, player));
    }

    private boolean isMoreCard(final Deck deck, final Player player) {
        final String input = InputView.readQuestOneMoreCard(player.getDisplayName());
        if (Command.find(input) == Command.YES) {
            final Card card = deck.drawCard();
            player.hit(card);
        }
        OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

        if (player.isBust()) {
            OutputView.printBustMessage(player.getDisplayName());
            return false;
        }

        if (player.isImPossibleDrawCard()) {
            return false;
        }

        return Command.find(input) == Command.YES;
    }

    private void processAdditionalHit(final Deck deck, final Player player) {
        while (Command.find(InputView.readQuestOneMoreCard(player.getDisplayName())).equals(Command.YES)) {
            final Card card = deck.drawCard();
            player.hit(card);
            OutputView.printCardsInHand(player.getDisplayName(), player.getCards());

            if (player.isBust()) {
                OutputView.printBustMessage(player.getDisplayName());
                break;
            }
            if (player.isImPossibleDrawCard()) {
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

    private void finishGame(final Players players, final Dealer dealer) {
        OutputView.printCardsInHandWithResults(dealer, players);
        final FinalResults finalResults = new FinalResults(players.createFinalResults(dealer));
        final Map<Gamer, Integer> profitResults = new LinkedHashMap<>(finalResults.createProfitResult(dealer));
        OutputView.printProfitResults(profitResults);
    }
}
