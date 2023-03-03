package blackjack.controller;

import blackjack.domain.Result;
import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.PlayersFactory;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackjackController(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        String[] playersName = inputView.receivePlayersName();
        Players players = PlayersFactory.from(playersName);
        Dealer dealer = new Dealer(players);
        game(dealer);
    }

    private void game(Dealer dealer) {
        dealer.drawSelfInitCards();
        dealer.distributeTwoCards();

        outputView.printDistributeCardsMessage(getPlayerNames(dealer.getPlayers()));

        Card card = dealer.getCards().getCards().get(0);
        outputView.printDealerInitCards(card.toString());

        outputView.printPlayersInitCards(getPlayersCards(dealer));

        for (Player player : dealer.getPlayers().getPlayers()) {
            if (player.isBust()) {
                continue;
            }
            String answer = inputView.askReceiveMoreCard(player.getName());
            while (answer.equals("y")) {
                dealer.giveOneMoreCard(player);
                if (player.isBust()) {
                    outputView.printBustMessage();
                    break;
                }
                outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
                answer = inputView.askReceiveMoreCard(player.getName());
            }
            outputView.printCurrentCards(player.getName(), getCurrentCards(player.getCards()));
        }

        while (dealer.canDraw()) {
            dealer.drawOneMoreCard();
            outputView.printDealerDrawOneMoreCard();
        }

        List<Player> players = dealer.getPlayers().getPlayers();
        List<Integer> scores = players.stream()
                .map(Player::calculateTotalScore)
                .collect(Collectors.toList());
        outputView.printDealerFinalCards(getCurrentCards(dealer.getCards()), dealer.calculateTotalScore());
        outputView.printPlayerFinalCards(getPlayersCards(dealer), scores);

        Map<Player, Result> playerResultMap = dealer.decideResult();
        List<Integer> dealerResult = dealer.decideSelfResult();

        Map<String, String> playerResult = new HashMap<>();
        for(Player player: playerResultMap.keySet()) {
            playerResult.put(player.getName(), playerResultMap.get(player).getState());
        }

        outputView.printGameResult(dealerResult, playerResult);
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

    private List<String> getCurrentCards(Cards cards) {
        return cards.getCards().stream()
                .map(Card::toString)
                .collect(Collectors.toList());
    }

    private List<String> getPlayerNames(final Players players) {
        List<String> playerNames = players.getPlayers().stream()
                .map(Player::getName)
                .collect(Collectors.toList());
        return playerNames;
    }
}
