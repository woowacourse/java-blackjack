package blackjack.domain;

import blackjack.domain.player.Dealer;
import blackjack.domain.player.Player;
import blackjack.view.InputView;
import blackjack.view.OutputView;
import java.util.List;
import java.util.stream.Collectors;

public class BlackJackGame {

    private static final int MIN_PLAYER_COUNT_TO_PLAY_GAME = 1;
    private static final int MAX_PLAYER_COUNT_TO_PLAY_GAME = 8;

    private static final CardDeck CARD_DECK = CardDeckGenerator.createCardDeckByCardNumber();

    public void run() {
        List<Player> players = initializePlayers();
        Dealer dealer = initializeDealer();

        OutputView.printPlayerInitialCards(players, dealer);

        playGame(players, dealer);

        PlayerResult playerResult = PlayerResult.create(dealer.calculateScore(), players);
        OutputView.printGameResult(playerResult.getDealerResultCount(), playerResult.getPlayerResults());
    }

    private List<Player> initializePlayers() {
        List<String> playerNames = InputView.requestPlayerNamesToPlayGame();
        checkPlayerCountToPlayGame(playerNames.size());
        checkDuplicatePlayerName(playerNames);
        return playerNames.stream()
                .map(name -> new Player(name, CARD_DECK.drawInitialCards()))
                .collect(Collectors.toUnmodifiableList());
    }

    private Dealer initializeDealer() {
        return new Dealer(CARD_DECK.drawInitialCards());
    }

    private void checkPlayerCountToPlayGame(int playerCount) {
        if (playerCount < MIN_PLAYER_COUNT_TO_PLAY_GAME || playerCount > MAX_PLAYER_COUNT_TO_PLAY_GAME) {
            throw new IllegalArgumentException("게임을 하기 위한 플레이어 수는 1명이상 8명이하로 입력해주세요.");
        }
    }

    private void checkDuplicatePlayerName(List<String> playerNames) {
        boolean duplicated = playerNames.stream()
                .distinct()
                .count() != playerNames.size();

        if (duplicated) {
            throw new IllegalArgumentException("중복된 플레이어의 이름이 있습니다.");
        }
    }

    private void playGame(final List<Player> players, final Dealer dealer) {
        for (Player player : players) {
            playPlayer(player);
        }
        playDealer(dealer);

        OutputView.printAllPlayerCardStatus(players, dealer);
    }

    private void playPlayer(Player player) {
        if (player.isPossibleToReceiveCard()) {
            DrawStatus drawStatus = requestDrawStatus(player);
            if (drawStatus == DrawStatus.YES) {
                player.receiveCard(CARD_DECK.drawCard());
                OutputView.printPlayerCardStatus(player.getName(), player.getCards().getCards());
                playPlayer(player);
            } else {
                OutputView.printPlayerCardStatus(player.getName(), player.getCards().getCards());
            }
        }
    }

    private DrawStatus requestDrawStatus(Player player) {
        try {
            return DrawStatus.from(InputView.requestDrawCardResponse(player.getName()));
        } catch (IllegalArgumentException e) {
            OutputView.printException(e);
            return requestDrawStatus(player);
        }
    }

    private void playDealer(Dealer dealer) {
        if (dealer.isPossibleToReceiveCard()) {
            OutputView.printDealerDrawOneMoreCard();
        }
    }
}
