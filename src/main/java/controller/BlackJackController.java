package controller;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

import domain.BlackJackResult;
import domain.PlayerDto;
import domain.card.CardDeck;
import domain.player.Dealer;
import domain.player.Gambler;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackController {
    public void start() {
        List<Gambler> gamblers = setupGamblers();
        Dealer dealer = new Dealer("딜러");
        final CardDeck cardDeck = setupCards();
        spreadCards(gamblers, dealer, cardDeck);
        hitOrStay(gamblers, cardDeck);
        addCardForDealer(dealer, cardDeck);
        printCardAndScore(dealer, gamblers);
        OutputView.printResult(BlackJackResult.of(dealer, gamblers));
    }

    public List<Gambler> setupGamblers() {
        final List<String> playerNames = InputView.scanPlayerNames();

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
        OutputView.printAfterSpread(dealer.getName(), playerNames);
        OutputView.printSingleCardForDealer(PlayerDto.from(dealer));

        gamblers
                .forEach(gambler -> OutputView.printCards(PlayerDto.from(gambler)));
    }

    private void hitOrStay(final List<Gambler> gamblers, CardDeck cardDeck) {
        System.out.println();
        for (Gambler gambler : gamblers) {
            playGame(gambler, cardDeck);
        }
    }

    private void playGame(Gambler gambler, CardDeck cardDeck) {
        boolean isHit = InputView.scanHitOrStay(gambler.getName());

        if (!isHit) {
            OutputView.printCards(PlayerDto.from(gambler));
            return;
        }

        do {
            gambler.addCard(cardDeck.getCard());
            final PlayerDto playerDto = PlayerDto.from(gambler);
            OutputView.printCards(playerDto);
            if (gambler.isBust()) {
                OutputView.printBust(playerDto);
                break;
            }
        } while (InputView.scanHitOrStay(gambler.getName()));
    }

    private void addCardForDealer(Dealer dealer, CardDeck cardDeck) {
        while (dealer.isUnderSixteen()) {
            dealer.addCard(cardDeck.getCard());
            OutputView.printDealerAddCard(dealer);
        }
    }

    private void printCardAndScore(Dealer dealer, List<Gambler> gamblers) {
        System.out.println();
        OutputView.printCardAndScore(PlayerDto.from(dealer));
        gamblers.stream()
                .map(PlayerDto::from)
                .forEach(OutputView::printCardAndScore);
    }
}
