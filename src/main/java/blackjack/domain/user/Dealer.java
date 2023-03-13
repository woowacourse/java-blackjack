package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;
import blackjack.domain.user.player.Players;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer {

    private static final int MIN_DEALER_SCORE = 16;

    private final Participant participant;

    public Dealer(final String name) {
        this.participant = new Participant(name);
    }

    public GameResult declareGameResult(final int playerScore) {
        Score score = this.getScore();
        int dealerScore = score.getValue();
        if (score.isBust()) {
            return GameResult.LOSE;
        }
        if (playerScore > dealerScore) {
            return GameResult.WIN;
        }

        return GameResult.DRAW;
    }

    public void drawCard(final CardPack cardPack) {
        participant.drawCard(cardPack);
    }

    public Map<GameResult, Integer> getResult(Players players) {
        Map<GameResult, Integer> result = new EnumMap<>(GameResult.class);
        for (final GameResult value : GameResult.values()) {
            result.put(value, 0);
        }

        players.getPlayers().forEach(player -> {
            Score playerScore = player.getScore();
            GameResult gameResult = declareGameResult(playerScore.getValue());
            GameResult dealerResult = getDealerResult(gameResult);
            result.put(dealerResult, result.get(dealerResult) + 1);
        });

        return result;
    }

    private GameResult getDealerResult(final GameResult playerResult) {
        if (playerResult == GameResult.WIN) {
            return GameResult.LOSE;
        }
        if (playerResult == GameResult.LOSE) {
            return GameResult.WIN;
        }
        return GameResult.DRAW;
    }

    public boolean isHitAble() {
        Score score = participant.getScore();
        return (score.getValue() <= MIN_DEALER_SCORE) && !isBust();
    }

    private boolean isBust() {
        return participant.isBust();
    }

    public Score getScore() {
        return participant.getScore();
    }

    public List<Card> showCards() {
        return List.copyOf(participant.showCards());
    }

    public String getName() {
        return participant.getName();
    }
}
