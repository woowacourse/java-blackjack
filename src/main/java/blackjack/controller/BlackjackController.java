package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gamer;
import blackjack.domain.player.Name;
import blackjack.domain.result.GameResult;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BlackjackController {
    private final Deck deck;

    public BlackjackController() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
            .flatMap(denomination -> Arrays.stream(Shape.values())
                .map(shape -> Card.of(denomination, shape)))
            .collect(Collectors.toList());
        deck = new Deck(wholeCards);
        deck.shuffle();
    }

    public void play() {
        String[] gamerNames = InputView.inputGamerNames();
        List<Gamer> gamers = initGamers(gamerNames);
        Dealer dealer = initDealer();
        OutputView.printGameStartMessage(dealer, gamers);
        drawGamersCard(gamers);
        drawDealerCard(dealer);
        OutputView.printPlayersScoreInfo(dealer, gamers);
        GameResult gameResult = dealer.judgeGameResultWithGamers(gamers);
        OutputView.printGameResult(gameResult, dealer, gamers);
    }

    private List<Gamer> initGamers(String[] gamerNames) {
        return Arrays.stream(gamerNames)
                .map(gamerName -> new Gamer(new Name(gamerName), Cards.of(deck.drawTwoStartCards())))
                .collect(Collectors.toList());
    }

    private Dealer initDealer() {
        return new Dealer(Cards.of(deck.drawTwoStartCards()));
    }


    private void drawGamersCard(List<Gamer> gamers) {
        for (Gamer gamer : gamers) {
            drawPerGamer(gamer);
        }
    }

    private void drawPerGamer(Gamer gamer) {
        while (gamer.canDraw() && InputView.inputHitOrStand(gamer.getName())) {
            gamer.addCard(deck.draw());
            OutputView.printPlayerCardInfo(gamer);
        }
    }

    private void drawDealerCard(Dealer dealer) {
        while (dealer.canDraw()) {
            dealer.addCard(deck.draw());
            OutputView.printDealerOneMoreDrawMessage();
        }
    }
}
