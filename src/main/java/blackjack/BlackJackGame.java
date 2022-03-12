package blackjack;

import blackjack.domain.HitRequest;
import blackjack.domain.Name;
import blackjack.domain.card.BlackJackCardsGenerator;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.winResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    public void play() {
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());
        Dealer dealer = new Dealer(deck.drawDouble());
        List<Player> players = createPlayers(inputPlayerNames(), deck);
        proceed(deck, dealer, players);
    }

    private List<Name> inputPlayerNames() {
        try {
            return InputView.inputPlayerNames().stream()
                    .map(Name::new)
                    .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private List<Player> createPlayers(List<Name> playerNames, CardDeck deck) {
        return playerNames.stream()
                .map(name -> new Player(name, deck.drawDouble()))
                .collect(Collectors.toUnmodifiableList());
    }

    private void proceed(CardDeck deck, Dealer dealer, List<Player> players) {
        alertStart(dealer, players);
        proceedPlayersTurn(players, deck);
        proceedDealer(dealer, deck);
        showResult(dealer, players);
    }

    private void alertStart(Dealer dealer, List<Player> players) {
        OutputView.printStartMessage(dealer, players);
        OutputView.printDealerFirstCard(dealer);
        players.forEach(player -> OutputView.printParticipantCards(player, player.calculateSum()));
    }

    private void proceedPlayersTurn(List<Player> players, CardDeck deck) {
        players.forEach(player -> proceedPlayer(player, deck));
    }

    private void proceedPlayer(Player player, CardDeck deck) {
        while (player.isHittable() && inputHitRequest(player) == HitRequest.YES) {
            player.hit(deck);
            OutputView.printParticipantCards(player, player.calculateSum());
        }
        showStopReason(player);
    }

    private HitRequest inputHitRequest(Player player) {
        try {
            return HitRequest.find(InputView.inputHitRequest(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputHitRequest(player);
        }
    }

    private void showStopReason(Player player) {
        if (player.isBlackJack()) {
            OutputView.printBlackJackMessage(player.getName());
            return;
        }
        if (player.isBust()) {
            OutputView.printBustMessage(player.getName());
        }
    }

    private void proceedDealer(Dealer dealer, CardDeck deck) {
        while (dealer.isHittable()) {
            dealer.hit(deck);
            OutputView.printDealerHitMessage();
        }
    }

    private void showResult(Dealer dealer, List<Player> players) {
        OutputView.printCardResultMessage();
        OutputView.printParticipantCards(dealer, dealer.calculateSum());
        players.forEach(
                player -> OutputView.printParticipantCards(player, player.calculateSum()));
        OutputView.printWinResult(new winResult(dealer, players));
    }
}
