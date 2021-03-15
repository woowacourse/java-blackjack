package blackjack.controller;

import blackjack.domain.BlackJackResult;
import blackjack.domain.ProfitResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participant;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.*;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final String YES = "y";

    public void start() {
        Players players = registerPlayers();
        ProfitResult profitResult = new ProfitResult(inputBettingMoney(players));
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffleCard();
        List<Participant> participants = generateAllParticipants(players, dealer);

        firstDraw(participants, cardDeck);
        playerTurn(players, cardDeck);
        dealerTurn(dealer, cardDeck);
        showResult(players, dealer, profitResult);
    }

    private Players registerPlayers() {
        try {
            OutputView.enterPlayersName();
            List<String> playersNames = InputView.inputName();
            return new Players(generatePlayer(playersNames));
        } catch (IllegalArgumentException e) {
            OutputView.printError(e.getMessage());
            return registerPlayers();
        }
    }

    private List<Player> generatePlayer(List<String> playerNames) {
        return playerNames
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    private Map<String, Double> inputBettingMoney(Players players) {
        Map<String, Double> result = new HashMap<>();
        for (Player player : players.getPlayers()) {
            OutputView.printAskBettingMoney(player.getName());
            result.put(player.getName(), InputView.inputMoney());
        }

        return result;
    }

    private List<Participant> generateAllParticipants(Players players, Dealer dealer) {
        List<Participant> participants = new ArrayList<>(players.getPlayers());
        participants.add(0, dealer);

        return participants;
    }

    private void firstDraw(List<Participant> participants, CardDeck cardDeck) {
        participants.forEach(participant ->
                participant.firstDraw(new Cards(Arrays.asList(
                        cardDeck.drawCard(),
                        cardDeck.drawCard()))));
        OutputView.distributeCardMessage(participants);
        OutputView.showDealerFirstCard(participants.get(0).getTakenCards().peekCard());
        participants.stream()
                .filter(participant -> !participant.getName().equals(Dealer.DEALER_NAME))
                .forEach(OutputView::showCards);
    }

    private void playerTurn(Players players, CardDeck cardDeck) {
        for (Player player : players.getPlayers()) {
            askDraw(player, cardDeck);
        }
    }

    private void askDraw(Player player, CardDeck cardDeck) {
        while (playerCanDraw(player)) {
            player.draw(cardDeck.drawCard());
            OutputView.showCards(player);
        }
    }

    private boolean playerCanDraw(Player player) {
        if (!player.canDraw()) {
            return false;
        }
        OutputView.askOneMoreCard(player);
        if (YES.equals(InputView.inputAnswer())) {
            return true;
        }
        OutputView.showCards(player);
        player.stay();
        return false;
    }

    private void dealerTurn(Dealer dealer, CardDeck cardDeck) {
        while (dealer.canDraw()) {
            dealer.draw(cardDeck.drawCard());
            OutputView.dealerReceiveOneCard();
        }
        if (dealer.isHit()) {
            dealer.stay();
        }
    }

    private void showResult(Players players, Dealer dealer, ProfitResult profitResult) {
        OutputView.showAllCards(players, dealer);
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        profitResult.calculateProfit(blackJackResult.getResult(), players);
        OutputView.showFinalResult(profitResult, profitResult.calculateDealerProfit());
    }
}
