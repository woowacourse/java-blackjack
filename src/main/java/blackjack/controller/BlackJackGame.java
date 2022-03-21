package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BetMoney;
import blackjack.domain.BlackJackCommand;
import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        final Players players = new Players(getDealer(), getGamblers());
        final CardDeck cardDeck = new CardDeck(new RandomCardGenerator());

        spreadCards(players, cardDeck);
        printCardsOfPlayers(players);

        playGame(players, cardDeck);
        processResult(players);
    }

    private Player getDealer() {
        return new Dealer();
    }

    private List<Player> getGamblers() {
        final List<String> playerNames = inputView.scanPlayerNames();
        return playerNames.stream()
            .map(name -> new Gambler(name, scanAndGetBetMoney(name)))
            .collect(toList());
    }

    private BetMoney scanAndGetBetMoney(final String name) {
        return new BetMoney(Integer.valueOf(inputView.scanBetMoney(name)));
    }

    private void spreadCards(final Players players, final CardDeck cardDeck) {
        players.receiveCard(cardDeck);
    }

    private void printCardsOfPlayers(final Players players) {
        outputView.printSpreadInstruction(PlayersDto.from(players));
        outputView.printSingleCardForDealer(PlayerDto.from(players.getDealer()));
        outputView.printCardsForGambler(PlayersDto.from(players.getGamblers()));
    }

    private void playGame(final Players players, final CardDeck cardDeck) {
        playGameForGambler(players.getGamblers(), cardDeck);
        playGameForDealer(players.getDealer(), cardDeck);
    }

    private void playGameForGambler(final List<Player> gamblers, CardDeck cardDeck) {
        outputView.printNewLine();
        for (Player gambler : gamblers) {
            playGame(gambler, cardDeck);
        }
    }

    private void playGame(Player gambler, CardDeck cardDeck) {
        while (canGamblerReceiveCard(gambler, cardDeck)) {
            outputView.printCards(PlayerDto.from(gambler));
        }
        outputView.printCards(PlayerDto.from(gambler));
    }

    private boolean canGamblerReceiveCard(final Player gambler, final CardDeck cardDeck) {
        return isHitThenReceiveCard(gambler, cardDeck) && isNotBurst(gambler) && gambler.isNotFinished();
    }

    private boolean isHitThenReceiveCard(final Player gambler, final CardDeck cardDeck) {
        final BlackJackCommand inputCommand = askHitOrStay(gambler);
        if (inputCommand.isHit()) {
            gambler.receiveCard(cardDeck);
            return true;
        }
        return false;
    }

    private BlackJackCommand askHitOrStay(final Player gambler) {
        return BlackJackCommand.from(inputView.scanHitOrStay(gambler));
    }

    private boolean isNotBurst(final Player player) {
        if (player.isBurst()) {
            outputView.printBurst(player);
            return false;
        }
        return true;
    }

    private void playGameForDealer(Dealer dealer, CardDeck cardDeck) {
        while (canDealerReceiveCard(dealer)) {
            dealer.receiveCard(cardDeck);
            outputView.printDealerAddCard(PlayerDto.from(dealer));
        }
    }

    private boolean canDealerReceiveCard(final Dealer dealer) {
        return isNotBurst(dealer) && dealer.isNotFinished();
    }

    private void processResult(Players players) {
        outputView.printNewLine();
        outputView.printCardAndScore(PlayersDto.from(players));
        final BlackJackResult blackJackResult = BlackJackResult.from(players);
        outputView.printResult(blackJackResult);
    }
}
