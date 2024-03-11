package domain;

import domain.cards.Card;
import domain.cards.CardsGenerator;
import domain.cards.Deck;
import domain.gamer.Dealer;
import domain.gamer.Gamer;
import domain.gamer.Gamers;
import domain.gamer.Player;
import view.InputView;
import view.ResultView;

import java.util.List;

public class BlackJackGame {

    private static final int INIT_CARD_COUNT = 2;

    private final InputView inputView;
    private final ResultView resultView;

    public BlackJackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void play() {
        Gamers gamers = new Gamers(inputView.readPlayersNames());
        Deck deck = generateDeck();

        configureSetup(gamers, deck);
        progressGame(gamers, deck);

        makeFinalResult(gamers);
    }

    private Deck generateDeck() {
        List<Card> cards = CardsGenerator.generateRandomCards();
        return new Deck(cards);
    }

    private void configureSetup(Gamers gamers, Deck deck) {
        for (Gamer gamer : gamers.getGamers()) {
            shareInitCards(gamer, deck);
        }
        resultView.printInitialCards(gamers);
    }

    private void shareInitCards(Gamer gamer, Deck deck) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            gamer.hit(deck.pickOneCard());
        }
    }

    private void progressGame(Gamers gamers, Deck deck) {
        progressPlayersGame(gamers.callPlayers(), deck);
        progressDealerGame(gamers.callDealer(), deck);
        resultView.printAllGamersCardsResult(gamers);
    }

    private void progressPlayersGame(List<Player> players, Deck deck) {
        for (Gamer player : players) {
            progressPlayerGame(player, deck);
        }
    }

    private void progressPlayerGame(Gamer player, Deck deck) {
        while (player.isNotBust()) {
            HitOption hitOption = new HitOption(inputView.readHitOrNot(player));
            if (!hitOption.doHit()) {
                break;
            }
            player.hit(deck.pickOneCard());
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(Dealer dealer, Deck deck) {
        while (dealer.canHit()) {
            Card pickedCard = deck.pickOneCard();
            dealer.hit(pickedCard);
            resultView.printDealerHitMessage(dealer, pickedCard);
        }
    }

    private void makeFinalResult(Gamers gamers) {
        Judge judge = new Judge();
        judge.decideResult(gamers);
        resultView.printFinalResults(gamers.callDealer(), judge);
    }
}
