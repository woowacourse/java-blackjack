package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackJackController {
    public static final int INITIAL_CARD_COUNT = 2;
    public static final String YES = "y";

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        try {
            Trump trump = new Trump(new RandomNumberGenerator());
            Dealer dealer = new Dealer(getInitialCards(trump));
            Players players = generatePlayers(trump);
            showInitialCards(dealer, players);
            playGame(trump, dealer, players);
            closeGame(dealer, players);
        } catch (IllegalArgumentException e) {
            outputView.printErrorMessage(e.getMessage());
        }
    }

    private List<Card> getInitialCards(final Trump trump) {
        List<Card> initialCards = new ArrayList<>();

        for (int i = 0; i < INITIAL_CARD_COUNT; i++) {
            initialCards.add(trump.getCard());
        }

        return initialCards;
    }

    private Players generatePlayers(final Trump trump) {
        outputView.printRequestPlayerNames();
        List<String> playerNames = inputView.readPlayerNames();

        Validator.getInstance().validatePlayerNames(playerNames);

        List<Player> players = new ArrayList<>();
        for (String playerName : playerNames) {
            players.add(new Player(playerName, getInitialCards(trump)));
        }

        return new Players(players);
    }

    private void showInitialCards(final Dealer dealer, final Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());

        outputView.printInitialCardMessage(playerNames);
        showDealerCards(dealer);
        players.getPlayers().forEach(this::showEachPlayerCards);
    }

    private void showDealerCards(final Dealer dealer) {
        Card openedDealerCard = dealer.getCards().get(0);
        outputView.printInitialDealerCard(openedDealerCard.getCardName());
    }

    private void showEachPlayerCards(final Player player) {
        outputView.printInitialPlayerCards(player.getName(), player.getCardNames());
    }

    private void playGame(final Trump trump, final Dealer dealer, final Players players) {
        players.getPlayers().forEach(
                player -> playEachPlayer(trump, player));

        playDealer(trump, dealer);
    }

    private void playEachPlayer(final Trump trump, final Player player) {
        boolean isRepeat = true;

        while (player.isAbleToReceive() && isRepeat) {
            isRepeat = isHit(trump, player);
        }
    }

    private boolean isHit(final Trump trump, final Player player) {
        String intention = requestIntention(player.getName());

        if (intention.equals(YES)) {
            hit(trump, player);
            return true;
        }
        return false;
    }

    private String requestIntention(final String playerName) {
        outputView.printRequestIntention(playerName);
        String intention = inputView.readPlayerIntention();
        Validator.getInstance().validatePlayerIntention(intention);
        return intention;
    }

    private void hit(final Trump trump, final Player player) {
        player.receiveCard(trump.getCard());
        showEachPlayerCards(player);
    }

    private void playDealer(final Trump trump, final Dealer dealer) {
        while (dealer.isAbleToReceive()) {
            outputView.printDealerReceived();
            dealer.receiveCard(trump.getCard());
        }
    }

    private void closeGame(final Dealer dealer, final Players players) {
        showFinalCards(dealer, players);

        GameResult gameResult = new GameResult(dealer, players);

        showDealerResult(gameResult.getDealerResults());
        players.getPlayers().forEach(
                player -> showPlayerResult(player, gameResult.getPlayerResult(player)));
    }

    private void showFinalCards(final Dealer dealer, final Players players) {
        outputView.printFinalCards(dealer.getName(), dealer.getCardNames(), dealer.getScore());

        players.getPlayers().forEach(
                player -> outputView.printFinalCards(player.getName(), player.getCardNames(), player.getScore()));
    }

    private void showDealerResult(final Map<Result, Integer> dealerResults) {
        Map<String, Integer> convertedDealerResult = dealerResults.keySet().stream()
                .collect(Collectors.toMap(
                        Result::getTerm,
                        dealerResults::get
                ));

        outputView.printDealerResults(convertedDealerResult);
    }

    private void showPlayerResult(final Player player, final Result playerResult) {
        outputView.printPlayerResult(player.getName(), playerResult.getTerm());
    }
}
