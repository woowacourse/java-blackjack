package blackjack;

import blackjack.domain.Answer;
import blackjack.domain.card.Deck;
import blackjack.domain.gamer.Dealer;
import blackjack.domain.gamer.Player;
import blackjack.domain.gamer.PlayersValidator;
import blackjack.domain.result.BlackJackResults;
import blackjack.view.InputView;
import blackjack.view.OutputView;

import java.util.ArrayList;
import java.util.List;

import static blackjack.domain.gamer.Gamer.INIT_DISTRIBUTION_COUNT;

public class BlackJackApplication {
    public static void main(String[] args) {
        Deck deck = new Deck();
        List<Player> players = createPlayers(deck);
        Dealer dealer = new Dealer(List.of(deck.draw(), deck.draw()));
        OutputView.printFirstCards(dealer, players);

        drawAdditionalCard(deck, players);
        printAdditionalDrawDealer(deck, dealer);
        OutputView.printFinalCards(dealer, players);

        OutputView.printFinalResult(new BlackJackResults(players, dealer));
    }

    private static List<Player> createPlayers(Deck deck) {
        List<Player> players = new ArrayList<>();
        for (String name : InputView.getNames()) {
            players.add(createPlayer(deck, name));
        }
        PlayersValidator.validate(players);
        return players;
    }

    private static Player createPlayer(Deck deck, String name) {
        return new Player(name, List.of(deck.draw(), deck.draw()), InputView.getBettingMoney(name));
    }

    private static void drawAdditionalCard(Deck deck, List<Player> players) {
        for (Player player : players) {
            printPlayerDrawCard(deck, player);
        }
    }

    private static void printPlayerDrawCard(Deck deck, Player player) {
        while (player.canDraw() && Answer.from(InputView.getAnswerOfAdditionalDraw(player.getName())).isYes()) {
            player.addCard(deck.draw());
            OutputView.printPlayerCard(player);
        }
    }

    private static void printAdditionalDrawDealer(Deck deck, Dealer dealer) {
        distributeToDealer(dealer, deck);
        OutputView.printAdditionalDrawDealer(dealer.getCardsSize() - INIT_DISTRIBUTION_COUNT);
    }

    private static void distributeToDealer(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw());
        }
    }
}
