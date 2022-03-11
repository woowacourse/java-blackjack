package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.User;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int MINIMUM_USER_COUNT_TO_PLAY_GAME = 1;

    private static final CardDeck CARD_DECK = CardDeckGenerator.createCardDeckByCardNumber();

    public void run() {
        List<User> users = initializeUsers();
        Dealer dealer = initializeDealer();

        OutputView.printPlayerInitialCards(users, dealer);
    }

    private List<User> initializeUsers() {
        List<String> userNames = InputView.requestUserNamesToPlayGame();
        checkUserMinimumCountToPlayGame(userNames.size());
        checkDuplicateUserName(userNames);
        return userNames.stream()
                .map(name -> new User(name, CARD_DECK.drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Dealer initializeDealer() {
        return new Dealer(CARD_DECK.drawInitialCards());
    }

    private void checkUserMinimumCountToPlayGame(int userCount) {
        if (userCount < MINIMUM_USER_COUNT_TO_PLAY_GAME) {
            throw new IllegalArgumentException("게임을 하기 위한 유저를 1명이상 입력해주세요.");
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
}
