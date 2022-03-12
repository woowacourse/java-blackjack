package blackjack;

import blackjack.domain.HitRequest;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Name;
import blackjack.domain.card.BlackJackCardsGenerator;
import blackjack.domain.card.CardDeck;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.WinResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

public class BlackJackApplication {

    public static void main(String[] args) {
        CardDeck deck = new CardDeck(new BlackJackCardsGenerator());
        Dealer dealer = createDealer(deck);
        List<Player> players = createPlayers(inputPlayerNames(), deck);
        play(deck, dealer, players);
    }

    private static Dealer createDealer(CardDeck deck) {
        return new Dealer(new Cards(deck.drawDouble()));
    }

    private static List<Name> inputPlayerNames() {
        try {
            return InputView.inputPlayerNames().stream()
                .map(Name::new)
                .collect(Collectors.toUnmodifiableList());
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputPlayerNames();
        }
    }

    private static List<Player> createPlayers(List<Name> playerNames, CardDeck deck) {
        return playerNames.stream()
            .map(name -> createPlayer(deck, name))
            .collect(Collectors.toUnmodifiableList());
    }

    private static Player createPlayer(CardDeck deck, Name name) {
        Cards cards = new Cards(deck.drawDouble());
        return new Player(name, cards);
    }

    private static void play(CardDeck deck, Dealer dealer, List<Player> players) {
        alertStart(dealer, players);
        proceedPlayersTurn(players, deck);
        proceedDealer(dealer, deck);
        showResult(dealer, players);
    }

    private static void alertStart(Dealer dealer, List<Player> players) {
        OutputView.printStartMessage(dealer, players);
        OutputView.printDealerFirstCard(dealer);
        players.forEach(player -> OutputView.printParticipantCards(player, player.calculateScore()));
    }

    private static void proceedPlayersTurn(List<Player> players, CardDeck deck) {
        players.forEach(player -> proceedPlayer(player, deck));
    }

    private static void proceedPlayer(Player player, CardDeck deck) {
        while (player.isHittable() && inputHitRequest(player) == HitRequest.YES) {
            player.hit(deck.draw());
            OutputView.printParticipantCards(player, player.calculateScore());
        }
        showStopReason(player);
    }

    private static HitRequest inputHitRequest(Player player) {
        try {
            return HitRequest.find(InputView.inputHitRequest(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printErrorMessage(e.getMessage());
            return inputHitRequest(player);
        }
    }

    private static void showStopReason(Player player) {
        if (player.isBlackJack()) {
            OutputView.printBlackJackMessage(player.getName());
            return;
        }
        if (player.isBust()) {
            OutputView.printBustMessage(player.getName());
        }
    }

    private static void proceedDealer(Dealer dealer, CardDeck deck) {
        while (dealer.isHittable()) {
            dealer.hit(deck.draw());
            OutputView.printDealerHitMessage();
        }
    }

    private static void showResult(Dealer dealer, List<Player> players) {
        OutputView.printCardResultMessage();
        OutputView.printParticipantCards(dealer, dealer.calculateScore());
        players.forEach(
            player -> OutputView.printParticipantCards(player, player.calculateScore()));
        OutputView.printWinResult(new WinResult(dealer, players));
    }
}
