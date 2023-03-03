package blackjack.controller;

import blackjack.domain.*;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackController {
    public static final int REPEAT_COUNT = 2;
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
        for (int i = 0; i < REPEAT_COUNT; i++) {
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
        outputView.printInitialCardDistribution(playerNames);
        showDealerCards(dealer);
        players.getPlayers().forEach(this::showEachPlayerCards);
    }

    private void showDealerCards(final Dealer dealer) {
        Card openedDealerCard = dealer.getCards().get(0);
        outputView.printDealerCard(makeCardName(openedDealerCard));
    }

    private void showEachPlayerCards(final Player player) {
        List<String> cards = makeCardNames(player);
        outputView.printEachPlayerCards(player.getName(), cards);
    }

    private List<String> makeCardNames(final Participant participant) {
        return participant.getCards().stream()
                .map(this::makeCardName)
                .collect(Collectors.toList());
    }

    private String makeCardName(final Card card) {
        return card.getTrumpNumber().getName() + card.getTrumpShape().getShape();
    }

    private void playGame(final Trump trump, final Dealer dealer, final Players players) {
        playAllPlayer(trump, players);
        playDealer(trump, dealer);
    }

    private void playAllPlayer(final Trump trump, final Players players) {
        for (Player player : players.getPlayers()) {
            playEachPlayer(trump, player);
        }
    }

    private void playEachPlayer(final Trump trump, final Player player) {
        boolean isRepeat = true;
        while (player.isAbleToReceive() && isRepeat) {
            isRepeat = isHit(trump, player);
        }
    }

    private boolean isHit(final Trump trump, final Player player) {
        String intention = getIntention(player.getName());
        if (intention.equals(YES)) {
            hit(trump, player);
            return true;
        }
        return false;
    }

    private String getIntention(final String playerName) {
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
        List<Result> dealerResults = gameResult.getDealerResults();

        showDealerResult(dealerResults);
        players.getPlayers().forEach(
                player -> showPlayerResult(player, gameResult.getPlayerResult(player)));
    }

    private void showFinalCards(final Dealer dealer, final Players players) {
        outputView.printFinalCards(dealer.getName(), makeCardNames(dealer), dealer.getScore());
        players.getPlayers().forEach(
                player -> outputView.printFinalCards(player.getName(), makeCardNames(player), player.getScore()));
    }

    private void showDealerResult(final List<Result> dealerResults) {
        int winCount = getResultsCount(dealerResults, Result.WIN);
        int loseCount = getResultsCount(dealerResults, Result.LOSE);
        int drawCount = getResultsCount(dealerResults, Result.DRAW);
        outputView.printDealerResult(winCount, loseCount, drawCount);
    }

    private int getResultsCount(final List<Result> dealerResults, final Result result) {
        return (int) dealerResults.stream()
                .filter(dealerResult -> dealerResult.equals(result))
                .count();
    }

    private void showPlayerResult(final Player player, final Result playerResult) {
        outputView.printPlayerResult(player.getName(), playerResult.getTerm());
    }
}
