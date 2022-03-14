package blackjack.controller;

import static java.util.stream.Collectors.toList;

import blackjack.domain.BlackJackCommand;
import blackjack.domain.BlackJackResult;
import blackjack.domain.card.CardDeck;
import blackjack.domain.cardGenerator.RandomCardGenerator;
import blackjack.domain.player.Dealer;
import blackjack.domain.player.Gambler;
import blackjack.domain.player.Player;
import blackjack.domain.player.Players;
import blackjack.dto.BlackJackResultDto;
import blackjack.dto.PlayerDto;
import blackjack.dto.PlayersDto;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;

public class BlackJackGame {

    private static final int DEFAULT_SPREAD_COUNT_START_INDEX = 0;
    private static final int DEFAULT_SPREAD_COUNT_END_INDEX = 2;

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();

    public void start() {
        final Players players = new Players(getDealer(), getGambler());
        final CardDeck cardDeck = new CardDeck(new RandomCardGenerator());

        spreadCards(players, cardDeck);
        playGame(players, cardDeck);
        processResult(players);
    }

    private Player getDealer() {
        return new Dealer();
    }

    private List<Player> getGambler() {
        final List<String> playerNames = inputView.scanPlayerNames();
        return playerNames.stream()
            .map(Gambler::new)
            .collect(toList());
    }

    protected void spreadCards(final Players players, final CardDeck cardDeck) {
        for (int i = DEFAULT_SPREAD_COUNT_START_INDEX; i < DEFAULT_SPREAD_COUNT_END_INDEX; i++) {
            spreadCard(players, cardDeck);
        }
        printSpreadCards(players);
    }


    private void spreadCard(final Players players, final CardDeck cardDeck) {
        players.receiveCard(cardDeck);
    }

    private void printSpreadCards(final Players players) {
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
        return isHit(gambler, cardDeck) && isNotBurst(gambler) && gambler.isNotFinished();
    }

    private boolean isHit(final Player gambler, final CardDeck cardDeck) {
        if (askHitOrStay(PlayerDto.from(gambler)).equals(BlackJackCommand.YES)) {
            gambler.receiveCard(cardDeck.pop());
            return true;
        }
        return false;
    }

    private BlackJackCommand askHitOrStay(final PlayerDto currentGamblerDto) {
        return BlackJackCommand.from(inputView.scanHitOrStay(currentGamblerDto));
    }

    private boolean isNotBurst(final Player player) {
        if (player.isBurst()) {
            outputView.printBurst(PlayerDto.from(player));
            return true;
        }
        return false;
    }

    private void playGameForDealer(Dealer dealer, CardDeck cardDeck) {
        while (canDealerReceiveCard(dealer)) {
            dealer.receiveCard(cardDeck.pop());
            outputView.printDealerAddCard(PlayerDto.from(dealer));
        }
    }

    private boolean canDealerReceiveCard(final Dealer dealer) {
        return isNotBurst(dealer) && dealer.isNotFinished();
    }

    private void processResult(Players players) {
        outputView.printNewLine();
        outputView.printCardAndScore(PlayersDto.from(players));
        final BlackJackResult result = BlackJackResult.of(players);
        outputView.printResult(BlackJackResultDto.from(result));
    }
}
