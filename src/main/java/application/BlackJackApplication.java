package application;

import static java.util.stream.Collectors.toList;

import domain.Card;
import domain.CardDeck;
import domain.CardDeckGenerator;
import domain.Dealer;
import domain.Name;
import domain.Participant;
import domain.ParticipantGenerator;
import domain.Player;
import domain.Players;
import dto.DealerResult;
import dto.DrawnCardsInfo;
import dto.GameResult;
import dto.WinLoseResult;
import java.util.ArrayList;
import java.util.List;
import view.InputView;
import view.OutputView;

public class BlackJackApplication {

    private static final int NUMBER_OF_SPLIT_CARDS = 2;

    private final InputView inputView;
    private final OutputView outputView;

    public BlackJackApplication(final InputView inputView, final OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        CardDeck cardDeck = CardDeckGenerator.create();

        Dealer dealer = ParticipantGenerator.createDealer();
        Players players = createPlayers();

        // 1. 카드 분배 후 딜러와 플레이어의 카드 정보 출력
        splitCards(dealer, cardDeck);
        players.stream()
                .forEach(player -> splitCards(player, cardDeck));
        outputView.printCardSplitMessage(createDrawnCardsInfos(dealer, dealer.openDrawnCards(), players));

        // 2. 딜러와 플레이어가 원할 때까지 카드를 뽑는다.
        players.stream()
                .forEach(player -> drawCard(cardDeck, player));
        dealerPickCard(cardDeck, dealer);

        // 3. 최종 결과를 출력한다.
        List<GameResult> result = createResult(dealer, players);
        outputView.printScoreResult(result);

        // 4. 승패를 출력한다.
        printResult(dealer, players);
    }

    private void printResult(final Dealer dealer, final Players players) {
        int dealerScore = dealer.calculateCardScore();
        boolean isDealerBurst = dealerScore > 21;

        List<WinLoseResult> winLoseResults = players.stream()
                .map(player -> calculateWinLose(dealerScore, isDealerBurst, player))
                .collect(toList());

        int dealerLoseCount = (int) winLoseResults.stream()
                .filter(WinLoseResult::isWin)
                .count();
        int dealerWinCount = winLoseResults.size() - dealerLoseCount;

        DealerResult dealerResult = DealerResult.toDto(dealer, dealerWinCount, dealerLoseCount);

        outputView.printResult(winLoseResults, dealerResult);
    }

    private WinLoseResult calculateWinLose(final int dealerScore, final boolean isDealerBurst, final Player player) {
        int playerScore = player.calculateCardScore();
        if (playerScore > 21) {
            // 플레이어 파산 경우
            return WinLoseResult.toDto(player, false);
        }

        if (isDealerBurst) {
            // 플레이어 파산x, 딜러 파산 경우
            return WinLoseResult.toDto(player, true);

        }

        // 플레이어 파산x, 딜러 파산 x경우
        boolean result = playerScore > dealerScore;
        return WinLoseResult.toDto(player, result);
    }

    private void drawCard(final CardDeck cardDeck, final Player player) {
        while (true) {
            String result = inputView.readChoiceOfDrawCard(player.getName());

            if (result.equals("y")) {
                player.pickCard(cardDeck.draw());

            }
            outputView.printPlayerCardInfo(DrawnCardsInfo.toDto(player, player.openDrawnCards()));

            if (player.calculateCardScore() > 21) {
                break;
            }

            if (result.equals("n")) {
                break;
            }

        }
    }

    private void dealerPickCard(final CardDeck cardDeck, final Dealer dealer) {
        while (true) {

            if (dealer.calculateCardScore() <= 16) {
                dealer.pickCard(cardDeck.draw());
                outputView.printDealerCardPickMessage();
            }

            if (dealer.calculateCardScore() > 16) {
                break;
            }
        }
    }

    private Players createPlayers() {
        List<String> rawNames = inputView.readPlayerNames();

        List<Name> names = rawNames.stream()
                .map(Name::new)
                .collect(toList());
        return ParticipantGenerator.createPlayers(names);
    }

    private void splitCards(final Participant participant, final CardDeck cardDeck) {
        for (int i = 0; i < NUMBER_OF_SPLIT_CARDS; i++) {
            participant.pickCard(cardDeck.draw());
        }
    }

    private List<DrawnCardsInfo> createDrawnCardsInfos(final Dealer dealer,
                                                       final List<Card> dealerCards,
                                                       final Players players) {
        List<DrawnCardsInfo> cardInfos = new ArrayList<>();

        addDealerCardInfo(dealer, dealerCards, cardInfos);

        players.stream()
                .forEach(player -> cardInfos.add(DrawnCardsInfo.toDto(player, player.openDrawnCards())));

        return cardInfos;
    }

    private List<GameResult> createResult(final Dealer dealer,
                                          final Players players) {
        List<GameResult> result = new ArrayList<>();

        result.add(GameResult.toDto(dealer, dealer.getDrawnCards(), dealer.calculateCardScore()));

        players.stream()
                .forEach(player -> result.add(
                        GameResult.toDto(player, player.getDrawnCards(), player.calculateCardScore())));

        return result;
    }

    private void addDealerCardInfo(final Dealer dealer,
                                   final List<Card> dealerCards,
                                   final List<DrawnCardsInfo> cardInfos) {
        cardInfos.add(DrawnCardsInfo.toDto(dealer, dealerCards));
    }
}
