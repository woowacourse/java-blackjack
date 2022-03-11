package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int MIN_USER_COUNT_TO_PLAY_GAME = 1;
    private static final int MAX_USER_COUNT_TO_PLAY_GAME = 8;

    private static final CardDeck CARD_DECK = CardDeckGenerator.createCardDeckByCardNumber();

    public void run() {
        List<User> users = initializeUsers();
        Dealer dealer = initializeDealer();

        OutputView.printPlayerInitialCards(users, dealer);

        playGame(users, dealer);

        PlayerGameResult playerGameResult = PlayerGameResult.create(dealer.calculateScore(), users);
        OutputView.printGameResult(playerGameResult.getDealerResultCount(), playerGameResult.getUserResults());
    }

    private List<User> initializeUsers() {
        List<String> userNames = InputView.requestUserNamesToPlayGame();
        checkUserCountToPlayGame(userNames.size());
        checkDuplicateUserName(userNames);
        return userNames.stream()
                .map(name -> new User(name, CARD_DECK.drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Dealer initializeDealer() {
        return new Dealer(CARD_DECK.drawInitialCards());
    }

    private void checkUserCountToPlayGame(int userCount) {
        if (userCount < MIN_USER_COUNT_TO_PLAY_GAME || userCount > MAX_USER_COUNT_TO_PLAY_GAME) {
            throw new IllegalArgumentException("게임을 하기 위한 유저 수는 1명이상 8명이하로 입력해주세요.");
        }
    }

    private void checkDuplicateUserName(List<String> userNames) {
        boolean duplicated = userNames.stream()
                .distinct()
                .count() != userNames.size();

        if (duplicated) {
            throw new IllegalArgumentException("중복된 사용자 이름이 있습니다.");
        }
    }

    private void playGame(final List<User> users, final Dealer dealer) {
        for (User user : users) {
            playUser(user);
        }
        playDealer(dealer);

        OutputView.printAllPlayerCardStatus(users, dealer);
    }

    private void playUser(User user) {
        if (user.isPossibleToReceiveCard()) {
            DrawStatus drawStatus = requestDrawStatus(user);
            if (drawStatus == DrawStatus.YES) {
                user.receiveCard(CARD_DECK.drawCard());
                OutputView.printPlayerCardStatus(user.getName(), user.getCards().getCards());
                playUser(user);
            } else {
                OutputView.printPlayerCardStatus(user.getName(), user.getCards().getCards());
            }
        }
    }

    private DrawStatus requestDrawStatus(User user) {
        try {
            return DrawStatus.from(InputView.requestDrawCardResponse(user.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return requestDrawStatus(user);
        }
    }

    private void playDealer(Dealer dealer) {
        if (dealer.isPossibleToReceiveCard()) {
            OutputView.printDealerDrawOneMoreCard();
        }
    }
}
