package blackjack;

import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.utils.CardDeck;
import blackjack.utils.RandomCardDeck;
import blackjack.view.InputView;
import blackjack.view.OutputView;

public class Application {
    public static void main(String[] args) {
        final CardDeck cardDeck = new RandomCardDeck();
        final Dealer dealer = new Dealer(cardDeck.initCards());
        final Players players = new Players(InputView.getNames(), cardDeck);

        setBetMoney(players);

        OutputView.printParticipantsCards(dealer, players);

        simulate(cardDeck, dealer, players);

        OutputView.printResult(dealer, players);
    }

    private static void setBetMoney(Players players) {
        for (Player player : players.values()) {
            int betMoney = Integer.parseInt(InputView.getBetMoney(player));
            player.setBetMoney(betMoney);
        }
    }

    private static void simulate(CardDeck cardDeck, Dealer dealer, Players players) {
        for (Player player : players.values()) {
            turnForPlayer(cardDeck, player);
        }

        if (dealer.isAvailableToTake()) {
            dealer.takeCard(cardDeck.pop());
            OutputView.printDealerGetCard();
        }
    }

    private static void turnForPlayer(CardDeck cardDeck, Player player) {
        while (player.isAvailableToTake() && InputView.requestOneMoreCard(player.getName())) {
            player.takeCard(cardDeck.pop());
            OutputView.printCardsOf(player);
        }
    }
}
