package blackjack.controller;

import blackjack.blackjack.BlackjackGame;
import blackjack.blackjack.card.Card;
import blackjack.blackjack.card.Deck;
import blackjack.blackjack.card.Hand;
import blackjack.blackjack.participant.Participants;
import blackjack.blackjack.participant.Player;
import blackjack.blackjack.participant.Players;
import blackjack.blackjack.result.ProfitResult;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class Controller {

    private final InputView inputView;
    private final ResultView resultView;

    public Controller(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void startGame(final Deck deck) {
        BlackjackGame blackjackGame = makeBlackjackGame();
        runGame(blackjackGame, deck);
    }

    private BlackjackGame makeBlackjackGame() {
        List<String> names = readNames();
        List<BigDecimal> amount = readBettingAmount(names);
        return new BlackjackGame(Participants.of(Players.from(names, amount)));
    }

    private List<String> readNames() {
        return StringParser.parseByComma(inputView.readNames());
    }

    private List<BigDecimal> readBettingAmount(final List<String> names) {
        return names.stream()
                .map(inputView::askBettingAmount)
                .map(StringParser::parseInt)
                .map(BigDecimal::valueOf)
                .toList();
    }

    private void runGame(final BlackjackGame blackjackGame, final Deck deck) {
        blackjackGame.dealInitialCards(deck);
        showInitialCards(blackjackGame.getParticipants());
        dealAdditionalCards(blackjackGame, deck);

        showParticipantsScore(blackjackGame.getParticipants());
        showProfitResult(blackjackGame.makeProfitResult());
    }

    private void showInitialCards(final Participants participants) {
        resultView.dealInitialCards(participants.getPlayerNames(),
                Map.entry(participants.getDealerName(), participants.showInitialDealerCards()),
                participants.showInitialPlayersCards());
    }

    private void dealAdditionalCards(final BlackjackGame blackjackGame, final Deck deck) {
        final Participants participants = blackjackGame.getParticipants();
        dealAdditionalCardsToPlayers(blackjackGame, deck);
        dealAdditionalCardsToDealer(participants, deck);
    }

    private void dealAdditionalCardsToPlayers(final BlackjackGame blackjackGame, final Deck deck) {
        while (blackjackGame.isPlaying()) {
            Player currentTurnPlayer = blackjackGame.findCurrentTurnPlayer();
            dealAdditionalCardToPlayer(currentTurnPlayer, deck);
            currentTurnPlayer.stayIfRunning();
        }
    }

    private void dealAdditionalCardToPlayer(final Player player, final Deck deck) {
        while (player.canHit() && askHit(player)) {
            final Card card = deck.drawCard();
            player.receiveCard(card);
            showParticipantTotalCards(player);
        }
    }

    private boolean askHit(final Player player) {
        return inputView.askHit(player);
    }

    private void showParticipantTotalCards(final Player player) {
        resultView.showParticipantTotalCards(player.getNickname(), player.showAllCards());
    }

    private void dealAdditionalCardsToDealer(final Participants participants, final Deck deck) {
        while (participants.canDealerHit()) {
            participants.receiveCardToDealer(new Hand(deck.drawCard()));
            showDealerExtraCard();
        }
        participants.stayToDealerIfRunning();
    }

    private void showDealerExtraCard() {
        resultView.showDealerExtraCard();
    }

    private void showParticipantsScore(final Participants participants) {
        resultView.showParticipantsScore(Map.entry(participants.getDealerName(),
                participants.showAllDealerCard()), participants.showAllPlayersCard());
    }

    private void showProfitResult(final ProfitResult profitResult) {
        resultView.showProfit(profitResult.getResult());
    }
}
