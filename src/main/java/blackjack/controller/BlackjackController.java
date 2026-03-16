package blackjack.controller;

import blackjack.model.cardDeck.CardDeck;
import blackjack.model.cardDeck.PickStrategy;
import blackjack.model.participant.Dealer;
import blackjack.model.participant.Player;
import blackjack.model.participant.Players;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class BlackjackController {

    private final InputView inputView;
    private final OutputView outputView;
    private final PickStrategy pickStrategy;

    public BlackjackController(
            InputView inputView,
            OutputView outputView,
            PickStrategy pickStrategy
    ) {
        validate(inputView, outputView, pickStrategy);

        this.inputView = inputView;
        this.outputView = outputView;
        this.pickStrategy = pickStrategy;
    }

    private void validate(
            InputView inputView,
            OutputView outputView,
            PickStrategy pickStrategy
    ) {
        if (inputView == null) {
            throw new IllegalArgumentException("inputView가 null입니다.");
        }

        if (outputView == null) {
            throw new IllegalArgumentException("outputView가 null입니다.");
        }

        if (pickStrategy == null) {
            throw new IllegalArgumentException("pickStrategy가 null입니다.");
        }
    }

    public void run() {
        Players players = createPlayers();
        Dealer dealer = new Dealer();

        CardDeck cardDeck = CardDeck.of(pickStrategy);
        distributeInitialCards(dealer, players, cardDeck);

        players = players.applyBlackjack();

        players.perform(player -> askHitOrStand(cardDeck, player));
        players = players.applyBust();

        drawUntilSeventeen(dealer, cardDeck);
        players = players.award(dealer);

        openDealerHands(dealer);
        openPlayersHands(players);

        outputView.printPlayerPrizes(players, dealer.getName());
    }

    private Players createPlayers() {
        List<String> names = inputPlayerNames();
        List<Integer> betAmounts = inputBetAmounts(names);

        return Players.of(
                names,
                betAmounts
        );
    }

    private List<String> inputPlayerNames() {
        outputView.printPlayerNamesInputPrompt();
        return inputView.inputPlayerNames();
    }

    private List<Integer> inputBetAmounts(List<String> names) {
        List<Integer> betAmounts = new ArrayList<>();

        for (String name: names) {
            outputView.printBetAmountInputPrompt(name);
            betAmounts.add(inputView.inputBetAmount());
        }

        return betAmounts;
    }

    private void distributeInitialCards(
            Dealer dealer,
            Players players,
            CardDeck cardDeck
    ) {
        dealer.pickInitialCards(cardDeck);
        players.pickInitialCards(cardDeck);

        outputView.printCardDistributionCompleted(
                players.getNames(),
                dealer.getName()
        );

        outputView.printParticipantCards(
                dealer.getName(),
                dealer.getOpenedCards()
        );
        players.perform(
                player -> outputView.printParticipantCards(
                        player.getName(),
                        player.getOpenedCards()
                )
        );
    }

    private void askHitOrStand(
            CardDeck cardDeck,
            Player player
    ) {
        while (!player.isBust() && inputIsContinued(player)) {
            player.pickAdditionalCard(cardDeck);
            outputView.printParticipantCards(player.getName(), player.getAllCard());
        }
    }

    private boolean inputIsContinued(Player player) {
        outputView.printMoreCardInputPrompt(player.getName());
        return inputView.inputMoreCard();
    }

    private void drawUntilSeventeen(
            Dealer dealer,
            CardDeck cardDeck
    ) {
        while (dealer.canPick()) {
            dealer.pickAdditionalCard(cardDeck);
            outputView.printDealerPicksCard();
        }
    }

    private void openDealerHands(Dealer dealer) {
        outputView.printParticipantCardsWithScore(
                dealer.getName(),
                dealer.getAllCard(),
                dealer.getCurrentTotalScore()
        );
    }

    private void openPlayersHands(Players players) {
        players.perform(player -> outputView.printParticipantCardsWithScore(
                player.getName(),
                player.getAllCard(),
                player.getCurrentTotalScore()
        ));
    }
}
