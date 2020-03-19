package view.dto;

import domain.card.Hands;

/**
 *   PlayerDto, DealerDto의 인터페이스
 *
 *   @author ParkDooWon
 */
public interface GamerDto {
	String getName();

	Hands getHands();

	int getTotalScore();
}
