package blackjack.controller;

import blackjack.domain.Answer;
import blackjack.domain.BlackjackGame;
import blackjack.domain.ResultStatus;
import blackjack.domain.card.Card;
import blackjack.domain.card.CardManager;
import blackjack.domain.card.Cards;
import blackjack.domain.participant.Dealer;
import blackjack.domain.participant.Participants;
import blackjack.domain.participant.Player;
import blackjack.domain.participant.Players;
import blackjack.domain.random.CardRandomGenerator;
import blackjack.util.StringParser;
import blackjack.view.InputView;
import blackjack.view.ResultView;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BlackjackController {

    private final InputView inputView;
    private final ResultView resultView;

    public BlackjackController(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void run() {
        // 블랙잭 게임 구성하기
        final CardManager cardManager = new CardManager(new CardRandomGenerator());
        final Participants participants = makeParticipants();
        final BlackjackGame blackjackGame = new BlackjackGame(cardManager, participants);

        // 초기 분배하기
        showInitialCards(blackjackGame, participants);

        // 플레이어들과 딜러에게 추가 카드 나눠주기
        spreadPlayersExtraCards(blackjackGame);
        spreadDealerExtraCards(blackjackGame);

        // 딜러와 플레이어가 가진 카드와 합 보여주기
        showAllCards(participants);

        // 딜러와 플레이어의 승패 보여주기
        final Map<String, ResultStatus> result = blackjackGame.calculateWinningResult();
        resultView.showWinningResult(result);
    }

    private void showInitialCards(final BlackjackGame blackjackGame, final Participants participants) {
        blackjackGame.spreadInitialCards();
        resultView.printNames(participants);

        final Dealer dealer = participants.getDealer();
        final List<Card> dealerCard = dealer.showOneCard();
        resultView.printCards(dealer, dealerCard);

        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            final List<Card> playerCards = player.showAllCard();
            resultView.printCards(player, playerCards);
        }
    }

    private void spreadPlayersExtraCards(final BlackjackGame blackjackGame) {
        for (int index = 0; index < blackjackGame.getPlayerSize(); index++) {
            spreadExtraCards(blackjackGame, index);
        }
    }

    private void spreadDealerExtraCards(final BlackjackGame blackjackGame) {
        while (blackjackGame.canDealerMoreCard()) {
            blackjackGame.spreadOneCardToDealer();
            resultView.printDealerExtraCard();
        }
    }

    private void showAllCards(final Participants participants) {
        final Dealer dealer = participants.getDealer();
        final List<Card> dealerCard = dealer.showAllCard();
        resultView.printCardsAndSum(dealer, dealerCard);

        final List<Player> players = participants.getPlayers();
        for (Player player : players) {
            final List<Card> playerCards = player.showAllCard();
            resultView.printCardsAndSum(player, playerCards);
        }
    }

    private void spreadExtraCards(final BlackjackGame blackjackGame, final int index) {
        final Player player = blackjackGame.getPlayer(index);
        while (blackjackGame.canPlayerMoreCard(player) && isMoreCard(player)) {
            blackjackGame.spreadOneCardToPlayer(player);
            resultView.printParticipantTotalCards(player);
        }
    }

    private Participants makeParticipants() {
        final Dealer dealer = new Dealer(new Cards(new ArrayList<>()));
        final Players players = makePlayers();
        return new Participants(dealer, players);
    }

    private boolean isMoreCard(final Player player) {
        try {
            final Answer answer = Answer.from(inputView.askMoreCard(player));
            return answer.isYes();
        } catch (IllegalArgumentException exception) {
            resultView.showException(exception);
            return isMoreCard(player);
        }
    }

    private Players makePlayers() {
        String names = inputView.readNames();
        List<String> parsedNames = StringParser.parseComma(names);
        return Players.from(parsedNames);
    }
}
