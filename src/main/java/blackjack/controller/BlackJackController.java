package blackjack.controller;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Gambler;
import blackjack.domain.PlayerDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        List<Gambler> gamblers = setupGamblers();
        Dealer dealer = new Dealer("딜러");
        final CardDeck cardDeck = setupCards();
        spreadCards(gamblers, dealer, cardDeck);
        hitOrStay(gamblers, cardDeck);
        addCardForDealer(dealer, cardDeck);
    }

    public List<Gambler> setupGamblers() {
        final List<String> playerNames = inputView.scanPlayerNames();

        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    private CardDeck setupCards() {
        return CardDeck.getInstance();
    }

    public void spreadCards(List<Gambler> gamblers, Dealer dealer, CardDeck cardDeck) {
        gamblers
            .forEach(player -> player.addCard(cardDeck.getCard()));
        dealer.addCard(cardDeck.getCard());

        gamblers
            .forEach(player -> player.addCard(cardDeck.getCard()));
        dealer.addCard(cardDeck.getCard());

        printCards(dealer, gamblers);
    }

    private void printCards(Dealer dealer, List<Gambler> gamblers) {
        final String playerNames = gamblers.stream().map(Gambler::getName).collect(joining(", "));
        outputView.printAfterSpread(dealer.getName(), playerNames);
        outputView.printSingleCardForDealer(PlayerDto.from(dealer));

        gamblers
            .forEach(gambler -> outputView.printCards(PlayerDto.from(gambler)));
    }

    private void hitOrStay(final List<Gambler> gamblers, CardDeck cardDeck) {
        System.out.println();
        for (Gambler gambler : gamblers) {
            playGame(gambler, cardDeck);
        }
    }

    private void playGame(Gambler gambler, CardDeck cardDeck) {
        boolean isHit = inputView.scanHitOrStay(gambler.getName());

        if (!isHit) {
            outputView.printCards(PlayerDto.from(gambler));
            return;
        }

        do {
            gambler.addCard(cardDeck.getCard());
            outputView.printCards(PlayerDto.from(gambler));
        } while (inputView.scanHitOrStay(gambler.getName()));
    }

    private void addCardForDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isUnderSixteen()) {
            dealer.addCard(cardDeck.getCard());
            outputView.printDealerAddCard(dealer);
        }
    }
}
