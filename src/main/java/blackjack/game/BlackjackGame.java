package blackjack.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.gamer.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerWinningResult;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public final class BlackjackGame {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final Deck deck = Deck.shuffled();
        Participants participants = makeParticipants();

        spreadInitialCards(participants, deck);
        spreadExtraCards(participants, deck);
        showParticipantScore(participants);
        showProfit(participants);
    }

    private Participants makeParticipants() {
        List<String> names = readNames();
        List<Integer> bettingAmounts = readBettingAmount(names);
        return Participants.of(Players.from(names, bettingAmounts));
    }

    private List<String> readNames() {
        return StringParser.parseByComma(inputView.readNames());
    }

    private List<Integer> readBettingAmount(final List<String> names) {
        return names.stream()
                .map(inputView::askBettingAmount)
                .map(StringParser::parseInt)
                .toList();
    }

    private void spreadInitialCards(final Participants participants, final Deck deck) {
        final Hand hand = deck.spreadInitialCards(participants.getInitialCardSize());
        participants.receiveInitialCards(hand);
        resultView.printSpreadCard(participants.getPlayerNames(),
                Map.entry(participants.getDealerName(), participants.showInitialDealerCards()),
                participants.showInitialPlayersCards());
    }

    private void spreadExtraCards(final Participants participants, final Deck deck) {
        spreadExtraCardsToPlayer(participants, deck);
        spreadExtraCardsToDealer(participants, deck);
    }

    private void spreadExtraCardsToPlayer(final Participants participants, final Deck deck) {
        Players availablePlayers = participants.findCanHitPlayers();
        for (Player player : availablePlayers.getPlayers()) {
            askPlayerMoreCard(deck, player);
        }
    }

    private void askPlayerMoreCard(final Deck deck, final Player player) {
        while (player.canHit() && inputView.askHit(player)) {
            final Card card = deck.spreadOneCard();
            player.receiveCard(card);
            resultView.printParticipantTotalCards(player.getNickname(), player.showAllCards());
        }
    }

    private void spreadExtraCardsToDealer(final Participants participants, final Deck deck) {
        while (participants.canDealerHit()) {
            participants.receiveCardToDealer(Hand.from(deck.spreadOneCard()));
            resultView.printDealerExtraCard();
        }
    }

    private void showParticipantScore(final Participants participants) {
        resultView.makeParticipantsWithScoreMessage(Map.entry(participants.getDealerName(),
                participants.showAllDealerCard()), participants.showAllPlayersCard());
    }

    private void showProfit(final Participants participants) {
        DealerWinningResult result = participants.makeWinningResult();
        resultView.showProfit(result.calculateProfit(participants.getDealer()));
    }
}
