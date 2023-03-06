package blackjack.controller;

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
        for (Player player : dealer.getPlayers().getPlayers()) {
            play(player);
        }
        turnOfDealer();
        finishGame();
    }

    private void setting() {
        dealer.drawSelfInitCards();
        dealer.settingCards();

        outputView.printDistributeCardsMessage(getPlayerNames(dealer.getPlayers()));

        Card card = dealer.getCards().getCards().get(0);
        outputView.printDealerInitCards(card.toString());

        outputView.printPlayersInitCards(getPlayersCards(dealer));
    }

    private List<String> getPlayerNames(final Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        return playerNames;
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
        List<Player> players = dealer.getPlayers().getPlayers();
        List<Integer> scores = getPlayersScore(players);

        outputView.printDealerFinalCards(getCurrentCards(dealer.getCards()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(dealer), scores);

        Map<Player, Result> playerResultMap = dealer.decideResult();
        List<Integer> dealerResult = dealer.decideSelfResult();

        Map<String, String> playerResult = getPlayerResult(playerResultMap);

        outputView.printGameResult(dealerResult, playerResult);
    }

    private List<Integer> getPlayersScore(final List<Player> players) {
        List<Integer> scores = players.stream()
                .map(Player::calculateTotalScore)
                .collect(Collectors.toList());
        return scores;
    }

    private List<String> getCurrentCards(Cards cards) {
        return cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    private Map<String, List<String>> getPlayersCards(final Dealer dealer) {
        List<Player> players = dealer.getPlayers().getPlayers();
        Map<String, List<String>> playersCards = new HashMap<>();
        for (Player player : players) {
            playersCards.put(player.getName(), player.getCards().getCards().stream()
                    .map(Card::toString)
                    .collect(Collectors.toList()));
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
