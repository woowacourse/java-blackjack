package domain.card;

import static java.util.Collections.unmodifiableList;

import java.util.ArrayList;
import java.util.List;

public class PlayingCards {
    private final List<PlayingCard> playingCards = new ArrayList<>();

    public void addCard(PlayingCard playingCard) {
        playingCards.add(playingCard);
    }

    public List<PlayingCard> getPlayingCards() {
        return unmodifiableList(playingCards);
    }

    //TODO 에이스를 고려한 최적의 점수 산출 로직을 여기에 담아야 한다.
    public int getResult() {
        return playingCards.stream()
                .mapToInt(this::getScoreByPlayingCard)
                .sum();
    }

    private int getScoreByPlayingCard(PlayingCard playingCard) {
        return playingCard.getDenomination().getScore();
    }
}
