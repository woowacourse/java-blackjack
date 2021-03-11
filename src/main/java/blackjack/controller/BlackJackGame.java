package blackjack.controller;

import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.card.Cards;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Participants;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final String YES = "y";

    public void start() {
        Players players = registerPlayers();
        Dealer dealer = new Dealer();
        CardDeck cardDeck = new CardDeck();
        cardDeck.shuffleCard();
        List<Participants> participants = generateAllParticipants(players, dealer);

        firstDraw(participants, cardDeck);
        playerTurn(players, cardDeck);
        dealerTurn(dealer, cardDeck);
        showResult(players, dealer);
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

    private List<Participants> generateAllParticipants(Players players, Dealer dealer) {
        List<Participants> participants = new ArrayList<>(players.getPlayers());
        participants.add(0, dealer);

        return participants;
    }

    private void firstDraw(List<Participants> participants, CardDeck cardDeck) {
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
    }

    private void showResult(Players players, Dealer dealer) {
        OutputView.showAllCards(players, dealer);
        BlackJackResult blackJackResult = new BlackJackResult(players.verifyResultByCompareScore(dealer));
        OutputView.showFinalResult(blackJackResult);
    }
}
