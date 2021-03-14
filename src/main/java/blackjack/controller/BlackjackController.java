package blackjack.controller;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import blackjack.domain.card.Denomination;
import blackjack.domain.card.Shape;
import blackjack.domain.deck.Deck;
import blackjack.domain.player.BettingMoney;
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
    public void play() {
        Deck deck = initDeck();
        String[] gamerNames = InputView.inputGamerNames();
        List<Gamer> gamers = initGamers(gamerNames, deck);
        Dealer dealer = initDealer(deck);
        OutputView.printGameStartMessage(dealer, gamers);
        drawGamersCard(gamers, deck);
        drawDealerCard(dealer, deck);
        OutputView.printPlayersScoreInfo(dealer, gamers);
        GameResult gameResult = GameResult.of(dealer, gamers);
        OutputView.printGameResult(gameResult, dealer, gamers);
    }

    private Deck initDeck() {
        List<Card> wholeCards = Arrays.stream(Denomination.values())
                .flatMap(denomination -> Arrays.stream(Shape.values())
                        .map(shape -> Card.of(denomination, shape)))
                .collect(Collectors.toList());
        Deck deck = new Deck(wholeCards);
        deck.shuffle();
        return deck;
    }

    private List<Gamer> initGamers(String[] gamerNames, Deck deck) {
        return Arrays.stream(gamerNames)
                .map(gamerName -> new Gamer(
                        new Name(gamerName),
                        Cards.of(deck.drawFirstCards()),
                        new BettingMoney(InputView.inputBettingMoney(gamerName))
                ))
                .collect(Collectors.toList());
    }

    private Dealer initDealer(Deck deck) {
        return new Dealer(Cards.of(deck.drawFirstCards()));
    }


    private void drawGamersCard(List<Gamer> gamers, Deck deck) {
        for (Gamer gamer : gamers) {
            drawPerGamer(gamer, deck);
        }
    }

    private void drawPerGamer(Gamer gamer, Deck deck) {
        while (gamer.canDraw() && InputView.inputHitOrStay(gamer.getName())) {
            gamer.draw(deck.draw());
            OutputView.printPlayerCardInfo(gamer);
        }
        gamer.stay();
    }

    private void drawDealerCard(Dealer dealer, Deck deck) {
        while (dealer.canDraw()) {
            dealer.draw(deck.draw());
            OutputView.printDealerOneMoreDrawMessage();
        }
        dealer.stay();
    }
}
