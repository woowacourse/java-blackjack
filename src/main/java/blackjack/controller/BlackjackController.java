package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private Dealer dealer;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        List<String> playerNames = inputView.receivePlayersName();
        Players players = new Players(playerNames);
        dealer = new Dealer(players);
        setting();
        for (Player player : dealer.getPlayers()) {
            play(player);
        }
        turnOfDealer();
        finishGame();
    }

    private void setting() {
        dealer.settingSelfCards();
        dealer.settingCards();

        outputView.printDistributeCardsMessage(getPlayerNames(dealer.getPlayers()));

        Card card = dealer.showOneCard();
        outputView.printDealerInitCards(card.toString());

        outputView.printPlayersInitCards(getPlayersCards());
    }

    private List<String> getPlayerNames(final List<Player> players) {
        return players.stream()
                .map(Player::getName)
                .collect(toList());
    }

    private void play(Player player) {
        boolean proceed = true;
        while (proceed && !player.isBust()) {
            proceed = ask(player);
        }
    }

    private boolean ask(Player player) {
        String answer = inputView.askReceiveMoreCard(player.getName());

        if (answer.equals("n")) {
            outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
            return false;
        }
        dealer.giveOneMoreCard(player);
        outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
        return true;
    }

    private void turnOfDealer() {
        while (dealer.canDraw()) {
            dealer.drawOneMoreCard();
            outputView.printDealerDrawOneMoreCard();
        }
    }

    private void finishGame() {
        List<Integer> scores = getPlayersScore(dealer.getPlayers());

        outputView.printDealerFinalCards(getCurrentCards(dealer.getCards()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(), scores);

        Map<Player, Result> playerResults = dealer.makePlayerResults();
        List<Integer> dealerResult = dealer.countSelfResults(playerResults);

        Map<String, String> playerResult = getPlayerResult(playerResults);

        outputView.printGameResult(dealerResult, playerResult);
    }

    private List<Integer> getPlayersScore(final List<Player> players) {
        return players.stream()
                .map(Player::calculateTotalScore)
                .collect(toList());
    }

    private List<String> getCurrentCards(List<Card> cards) {
        return cards.stream()
                .map(Card::toString)
                .collect(toList());
    }

    private Map<String, List<String>> getPlayersCards() {
        List<Player> players = dealer.getPlayers();
        Map<String, List<String>> playersCards = new HashMap<>();

        for (Player player : players) {
            playersCards.put(player.getName(), player.getCards()
                    .stream()
                    .map(Card::toString)
                    .collect(toList()));
        }
        return playersCards;
    }

    private Map<String, String> getPlayerResult(final Map<Player, Result> playerResultMap) {
        Map<String, String> playerResult = new HashMap<>();
        for(Player player: playerResultMap.keySet()) {
            playerResult.put(player.getName(), playerResultMap.get(player).getState());
        }
        return playerResult;
    }
}
