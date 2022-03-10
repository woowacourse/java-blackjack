package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackResult;
import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Gambler;
import blackjack.domain.Player;
import blackjack.domain.PlayerDto;
import blackjack.domain.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BlackJackController {
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        List<Player> gamblers = setupGamblers();
        Player dealer = new Dealer("딜러");
        final CardDeck cardDeck = setupCards();

        spreadCards(gamblers, dealer, cardDeck);
        printSpreadCards(dealer, gamblers);

        playGameForGambler(gamblers, cardDeck);
        playGameForDealer(dealer, cardDeck);

        printCardAndScore(dealer, gamblers);
        outputView.printResult(BlackJackResult.of(dealer, gamblers));
    }

    public List<Player> setupGamblers() {
        final List<String> playerNames = inputView.scanPlayerNames();

        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    private CardDeck setupCards() {
        return CardDeck.getInstance();
    }

    public void spreadCards(List<Player> gamblers, Player dealer, CardDeck cardDeck) {
        IntStream.range(0, 2)
            .forEach(i -> spreadCard(gamblers, dealer, cardDeck));
    }

    private void spreadCard(final List<Player> gamblers, final Player dealer, final CardDeck cardDeck) {
        Stream.concat(
            gamblers.stream(),
            Stream.of(dealer)
        ).forEach(cardDeck::drawTo);
    }

    private void printSpreadCards(Player dealer, List<Player> gamblers) {
        outputView.printSpreadInstruction(PlayersDto.from(concatPlayers(dealer, gamblers)));
        outputView.printSingleCardForDealer(PlayerDto.from(dealer));
        outputView.printCardsForGambler(PlayersDto.from(gamblers));
    }

    private List<Player> concatPlayers(final Player dealer, final List<Player> gamblers) {
        return Stream.concat(
                gamblers.stream(),
                Stream.of(dealer)
            ).collect(Collectors.toList());
    }

    private void playGameForGambler(final List<Player> gamblers, CardDeck cardDeck) {
        outputView.printNewLine();
        for (Player gambler : gamblers) {
            playGame(gambler, cardDeck);
        }
    }

    private void playGame(Player gambler, CardDeck cardDeck) {
        PlayerDto currentGamblerDto = PlayerDto.from(gambler);
        boolean isHit = inputView.scanHitOrStay(currentGamblerDto);

        if (!isHit) {
            outputView.printCards(currentGamblerDto);
            return;
        }

        while(isHit) {
            if (gambler.isFinished(cardDeck)){
                outputView.printBurst(currentGamblerDto);
                break;
            }

            gambler.addCard(cardDeck);
            currentGamblerDto = PlayerDto.from(gambler);
            outputView.printCards(currentGamblerDto);
            isHit = inputView.scanHitOrStay(currentGamblerDto);
        }
    }

    private void playGameForDealer(Player dealer, CardDeck cardDeck) {
        while (!dealer.isFinished(cardDeck)) {
            dealer.addCard(cardDeck);
            outputView.printDealerAddCard(dealer);
        }
    }

    private void printCardAndScore(Player dealer, List<Player> gamblers) {
        System.out.println();
        outputView.printCardAndScore(PlayerDto.from(dealer));
        gamblers.stream()
            .map(PlayerDto::from)
            .forEach(outputView::printCardAndScore);
    }
}
