package controller.dto;

import domain.Score;
import domain.TrumpCard;
import java.util.List;

public record CardScoreDto(List<TrumpCard> cards, Score score) {

}
