package blackjack.controller;

import blackjack.domain.BlackjackGame;
import blackjack.domain.ResultStatus;
import blackjack.domain.card.CardManager;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private static final String YES = "y";
    private static final String NO = "n";
    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        final CardManager cardManager = new CardManager(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(cardManager, participants);

        spreadInitialCards(blackjackGame, participants);
        spreadExtraCards(blackjackGame);
        resultView.printCardsAndSum(participants);
        showWinningResult(blackjackGame);
    }

    private void showWinningResult(final BlackjackGame blackjackGame) {
        final Map<String, ResultStatus> result = blackjackGame.calculateWinningResult();
        resultView.showWinningResult(result);
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame) {
        spreadPlayersExtraCards(blackjackGame);
        spreadDealerExtraCards(blackjackGame);
    }

    private void spreadInitialCards(final BlackjackGame blackjackGame, final Participants participants) {
        blackjackGame.spreadInitialCards();
        resultView.printSpreadCard(participants);
    }

    private void spreadDealerExtraCards(final BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerMoreCard()) {
            blackjackGame.spreadOneCardToDealer();
            resultView.printDealerExtraCard();
        }
    }

    private void spreadPlayersExtraCards(final BlackjackGame blackjackGame) {
        for (int index = 0; index < blackjackGame.getPlayerSize(); index++) {
            spreadExtraCards(blackjackGame, index);
        }
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame, final int index) {
        final Player player = blackjackGame.getPlayer(index);
        while (blackjackGame.canPlayerMoreCard(index) && isMoreCard(player)) {
            blackjackGame.spreadOneCardToPlayer(index);
            resultView.printParticipantTotalCards(player);
        }
    }

    private Participants makeParticipants() {
        final Dealer dealer = Dealer.createEmpty();
        final Players players = makePlayers();
        return new Participants(dealer, players);
    }

    private boolean isMoreCard(final Player player) {
        String answer = inputView.askMoreCard(player);
        if (isValidAnswer(answer)) {
            return answer.equals(YES);
        }
        resultView.showln("잘못된 응답입니다. 다시 입력해주세요.");
        return isMoreCard(player);
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseByComma(names);
        return new Players(parsedNames.stream()
                .map(Player::createEmpty)
                .toList());
    }

    private boolean isValidAnswer(final String answer) {
        return answer.equals(YES) || answer.equals(NO);
    }
}
