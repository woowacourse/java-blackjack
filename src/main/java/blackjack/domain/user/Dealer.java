package blackjack.domain.user;

import blackjack.domain.card.Card;
import blackjack.domain.cardpack.CardPack;
import blackjack.domain.game.GameResult;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class Dealer {

    private final Participant participant;
    private final Map<GameResult, Integer> result;

    public Dealer(final String name) {
        this.result = new EnumMap<>(GameResult.class);
        initResult();
        this.participant = new Participant(name);
    }

    private void initResult() {
        Arrays.stream(GameResult.values())
                .forEach(gameResult -> result.put(gameResult, 0));
    }

    public GameResult declareGameResult(final int playerScore) {
        int dealerScore = getScore();
        if (playerScore > dealerScore) {
            updateDealerScoreBoard(GameResult.LOSE);
            return GameResult.WIN;
        }

        if (playerScore == dealerScore) {
            updateDealerScoreBoard(GameResult.DRAW);
            return GameResult.DRAW;
        }
        updateDealerScoreBoard(GameResult.WIN);
        return GameResult.LOSE;
    }

    private void updateDealerScoreBoard(final GameResult score) {
        result.put(score, result.get(score) + 1);
    }

    public int getScore() {
        return participant.getScore();
    }

    public List<Card> showCards() {
        return List.copyOf(participant.showCards());
    }

    public void drawCard(final CardPack cardPack) {
        participant.drawCard(cardPack);
    }

    public Map<GameResult, Integer> getResult() {
        return new EnumMap<>(result);
    }

    public String getName() {
        return participant.getName();
    }
}
