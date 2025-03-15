package blackjack;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;
import blackjack.domain.card.Hand;
import blackjack.domain.card.generator.ShuffleDeckGenerator;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.result.DealerWinningResult;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public class BlackjackGame {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackGame(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final Deck deck = new Deck(new ShuffleDeckGenerator());
        Participants participants = Participants.of(makePlayers());

        spreadInitialCards(participants, deck);
        spreadExtraCards(participants, deck);
        showParticipantScore(participants);
        showWinningResult(participants);
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseByComma(names);
        return new Players(parsedNames.stream()
                .map(Player::createEmpty)
                .toList());
    }

    private void spreadInitialCards(final Participants participants, final Deck deck) {
        final Hand hand = deck.spreadInitialCards(participants.getSize());
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
        Players availablePlayers = participants.findHitAvailablePlayers();
        for (Player player : availablePlayers.getPlayers()) {
            while (player.canHit() && inputView.askHit(player)) {
                final Card card = deck.spreadOneCard();
                player.receiveCard(card);
                resultView.printParticipantTotalCards(player.getNickname(), player.showAllCards());
            }
        }
    }

    private void spreadExtraCardsToDealer(final Participants participants, final Deck deck) {
        while (participants.canDealerHit()) {
            final Card card = deck.spreadOneCard();
            participants.receiveCardToDealer(new Hand(List.of(card)));
            resultView.printDealerExtraCard();
        }
    }

    private void showParticipantScore(final Participants participants) {
        resultView.makeParticipantsWithScoreMessage(Map.entry(participants.getDealerName(),
                participants.showAllDealerCard()), participants.showAllPlayersCard());
    }

    private void showWinningResult(final Participants participants) {
        DealerWinningResult result = participants.makeWinningResult();
        resultView.showDealerWinningResult(result);
    }
}
