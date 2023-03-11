package blackjackgame.domain.game;

import blackjackgame.domain.user.DealerStatus;
import blackjackgame.domain.card.Card;
import blackjackgame.domain.card.Cards;
import blackjackgame.domain.user.Dealer;
import blackjackgame.domain.user.Player;
import blackjackgame.domain.user.PlayerStatus;
import blackjackgame.domain.user.Players;
import blackjackgame.domain.user.User;
import blackjackgame.domain.user.dto.NameDto;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class BlackJackGame {
    private static final int INITIAL_CARD_COUNT = 2;
    private static final int RESULT_INITIAL_VALUE = 0;

    private final Players players;
    private final Dealer dealer;
    private final Cards cards;
    private final Map<NameDto, Result> resultByUserNames;

    public BlackJackGame(Players players, Dealer dealer, Cards cards) {
        this.players = players;
        this.dealer = dealer;
        this.cards = cards;
        this.resultByUserNames = new LinkedHashMap<>();
    }

    public void drawDefaultCard() {
        dealer.receiveCards(cards.drawCards(INITIAL_CARD_COUNT));
        for (Player player : players.getPlayers()) {
            player.receiveCards(cards.drawCards(INITIAL_CARD_COUNT));
        }
    }

    public void hit(User user) {
        user.receiveCard(cards.drawCard());
    }

    public void hitUntilSatisfyingDealerMinimumScore() {
        while (DealerStatus.UNDER_MIN_SCORE.equals(dealer.getStatus())) {
            dealer.receiveCard(cards.drawCard());
        }
    }

    public void judgeWinner() {
        Referee referee = new Referee(players, dealer);
        referee.judgeWinner();
    }

    public Map<NameDto, List<Card>> getSetUpResult() {
        Map<NameDto, List<Card>> setUpResult = new LinkedHashMap<>();

        setUpResult.put(new NameDto(dealer.getName()), List.of(dealer.getFirstCard()));

        for (Player player : players.getPlayers()) {
            setUpResult.put(new NameDto(player.getName()), player.cards());
        }

        return setUpResult;
    }

    public int getDealerExtraDrawCount() {
        return dealer.getExtraDrawCount(INITIAL_CARD_COUNT);
    }

    public Map<NameDto, Result> getPlayerFinalResult() {
        return resultByUserNames;
    }

    public Map<Result, Integer> getDealerFinalResult() {
        Map<Result, Integer> playerCountByDealerResult = new HashMap<>();
        initDealerResult(playerCountByDealerResult);
        for (Result playerResult : resultByUserNames.values()) {
            Result dealerResult = convertPlayerResultToDealerResult(playerResult);
            playerCountByDealerResult.put(dealerResult, playerCountByDealerResult.getOrDefault(dealerResult,
                    RESULT_INITIAL_VALUE) + 1);
        }
        return playerCountByDealerResult;
    }

    private void initDealerResult(Map<Result, Integer> playerCountByResult) {
        playerCountByResult.put(Result.WIN, RESULT_INITIAL_VALUE);
        playerCountByResult.put(Result.DRAW, RESULT_INITIAL_VALUE);
        playerCountByResult.put(Result.LOSE, RESULT_INITIAL_VALUE);
    }

    private Result convertPlayerResultToDealerResult(Result playerResult) {
        if (playerResult == Result.WIN) {
            return Result.LOSE;
        }
        if (playerResult == Result.LOSE) {
            return Result.WIN;
        }
        return Result.DRAW;
    }

    public Dealer getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players.getPlayers();
    }
}
