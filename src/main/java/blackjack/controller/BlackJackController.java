package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackResult;
import blackjack.domain.CardDeck;
import blackjack.domain.Dealer;
import blackjack.domain.Gambler;
import blackjack.domain.Player;
import blackjack.dto.BlackJackResultDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class BlackJackController {
    private static final int DEFAULT_SPREAD_COUNT_END_INDEX = 2;
    private static final int DEFAULT_SPREAD_COUNT_START_INDEX = 0;
    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        final List<Player> gamblers = setupGamblers();
        final Player dealer = new Dealer("딜러");
        final CardDeck cardDeck = new CardDeck();

        spreadCards(gamblers, dealer, cardDeck);

        playGame(gamblers, dealer, cardDeck);

        processResult(dealer, gamblers);
    }


    public List<Player> setupGamblers() {
        final List<String> playerNames = inputView.scanPlayerNames();

        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    public void spreadCards(List<Player> gamblers, Player dealer, CardDeck cardDeck) {
        IntStream.range(DEFAULT_SPREAD_COUNT_START_INDEX, DEFAULT_SPREAD_COUNT_END_INDEX)
            .forEach(i -> spreadCard(gamblers, dealer, cardDeck));
        printSpreadCards(dealer, gamblers);
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
            Stream.of(dealer),
            gamblers.stream()
        ).collect(Collectors.toList());
    }

    private void playGame(final List<Player> gamblers, final Player dealer, final CardDeck cardDeck) {
        playGameForGambler(gamblers, cardDeck);
        playGameForDealer(dealer, cardDeck);
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

        while (isHit) {
            if (gambler.isFinished(cardDeck)) {
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

    private void processResult(Player dealer, List<Player> gamblers) {
        outputView.printNewLine();
        outputView.printCardAndScore(PlayersDto.from(concatPlayers(dealer, gamblers)));
        final BlackJackResult result = BlackJackResult.of(dealer, gamblers);
        outputView.printResult(BlackJackResultDto.from(result));
    }
}
