package controller;

import domain.game.BlackJack;
import domain.deck.ShuffledDeck;
import domain.participant.Player;
import domain.participant.Players;
import domain.participant.Name;
import view.InputView;
import view.OutputView;

import java.util.List;
import java.util.stream.Collectors;

import static template.Repeater.repeat;

public final class BlackJackController {

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackController(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    private BlackJack createBlackJack() {
        final List<Name> playerNames = repeat(this::userNameRequest);
        final List<Integer> bets = playerNames.stream()
                .map(name -> repeat(() -> inputView.betRequest(name)))
                .collect(Collectors.toList());
        return BlackJack.getInstance(playerNames, bets, new ShuffledDeck());
    }

    public void process() {
        final BlackJack blackJack = repeat(this::createBlackJack);

        outputView.printInitialStatus(blackJack.getDealer(), blackJack.getPlayers());
        getPlayersDecision(blackJack);
        getDealerResult(blackJack);
        outputView.printStatus(blackJack.getDealer(), blackJack.getPlayers());
        outputView.printFinalResult(blackJack.getDealer(), blackJack.getGameResult());
    }

    private List<Name> userNameRequest() {
        return inputView.userNameRequest()
                .stream()
                .map(Name::of)
                .collect(Collectors.toList());
    }

    private void getPlayersDecision(final BlackJack blackJack) {
        final Players participants = blackJack.getPlayers();
        for (Player participant : participants.getPlayers()) {
            getPlayerDecision(blackJack, participant);
        }
    }

    private void getPlayerDecision(final BlackJack blackJack, final Player player) {
        while (!blackJack.isBusted(player) && !player.isBlackJack() && wantMoreCard(player)) {
            blackJack.giveCard(player);
            outputView.printPlayerCards(player);
        }
    }

    private boolean wantMoreCard(final Player player) {
        return repeat(() -> inputView.cardRequest(player.getName()));
    }

    private void getDealerResult(final BlackJack blackJack) {
        final int cardCount = blackJack.getAdditionalCardCount();
        final boolean haveAdditionalCard = cardCount > 0;
        outputView.printAdditionalCardCount(cardCount, haveAdditionalCard);
    }
}
