package blackjack.game;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Players;
import blackjack.domain.participant.Player;
import blackjack.domain.result.ProfitResult;
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

        dealInitialCards(participants, deck);
        dealAdditionalCards(participants, deck);
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

    private void dealInitialCards(final Participants participants, final Deck deck) {
        final Hand hand = deck.drawInitialCards(participants.getInitialCardSize());
        participants.dealInitialCards(hand);
        resultView.showSpreadCard(participants.getPlayerNames(),
                Map.entry(participants.getDealerName(), participants.showInitialDealerCards()),
                participants.showInitialPlayersCards());
    }

    private void dealAdditionalCards(final Participants participants, final Deck deck) {
        dealAdditionalCardsToPlayers(participants, deck);
        dealAdditionalCardsToDealer(participants, deck);
    }

    private void dealAdditionalCardsToPlayers(final Participants participants, final Deck deck) {
        Players hitEligiblePlayers = participants.findHitEligiblePlayers();
        for (Player player : hitEligiblePlayers.getPlayers()) {
            dealAdditionalCardToPlayer(deck, player);
        }
    }

    private void dealAdditionalCardToPlayer(final Deck deck, final Player player) {
        while (player.canHit() && inputView.askHit(player)) {
            final Card card = deck.drawCard();
            player.receiveCard(card);
            resultView.showParticipantTotalCards(player.getNickname(), player.showAllCards());
        }
    }

    private void dealAdditionalCardsToDealer(final Participants participants, final Deck deck) {
        while (participants.canDealerHit()) {
            participants.receiveCardToDealer(new Hand(deck.drawCard()));
            resultView.showDealerExtraCard();
        }
    }

    private void showParticipantScore(final Participants participants) {
        resultView.showParticipantsScore(Map.entry(participants.getDealerName(),
                participants.showAllDealerCard()), participants.showAllPlayersCard());
    }

    private void showProfit(final Participants participants) {
        ProfitResult result = participants.makeDealerWinningResult();
        resultView.showProfit(result.getResult());
    }
}
