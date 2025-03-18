package controller.dto;

import domain.participant.state.hand.Score;
import domain.TrumpCard;
import java.util.List;

public record CardScoreDto(List<TrumpCard> cards, Score score) {

}
