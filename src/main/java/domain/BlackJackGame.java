package domain;

import domain.cards.Card;
import domain.cards.CardPack;
import domain.cards.CardsGenerator;
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
        CardPack cardPack = generateCardPack();

        configureSetup(gamers, cardPack);
        progressGame(gamers, cardPack);

        makeFinalResult(gamers);
    }

    private CardPack generateCardPack() {
        List<Card> cards = CardsGenerator.generateRandomCards();
        return new CardPack(cards);
    }

    private void configureSetup(Gamers gamers, CardPack cardPack) {
        for (Gamer gamer : gamers.getGamers()) {
            shareInitCards(gamer, cardPack);
        }
        resultView.printInitialCards(gamers);
    }

    private void shareInitCards(Gamer gamer, CardPack cardPack) {
        for (int i = 0; i < INIT_CARD_COUNT; i++) {
            gamer.hit(cardPack.pickOneCard());
        }
    }

    private void progressGame(Gamers gamers, CardPack cardPack) {
        progressPlayersGame(gamers.callPlayers(), cardPack);
        progressDealerGame(gamers.callDealer(), cardPack);
        resultView.printAllGamersCardsResult(gamers);
    }

    private void progressPlayersGame(List<Player> players, CardPack cardPack) {
        for (Gamer player : players) {
            progressPlayerGame(player, cardPack);
        }
    }

    private void progressPlayerGame(Gamer player, CardPack cardPack) {
        while (player.isNotBust()) {
            HitOption hitOption = new HitOption(inputView.readHitOrNot(player));
            if (!hitOption.doHit()) {
                break;
            }
            player.hit(cardPack.pickOneCard());
            resultView.printPlayerCards(player);
        }
    }

    private void progressDealerGame(Dealer dealer, CardPack cardPack) {
        while (dealer.canHit()) {
            Card pickedCard = cardPack.pickOneCard();
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
