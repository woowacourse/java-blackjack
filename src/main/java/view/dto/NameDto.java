package view.dto;

import domain.gamer.Name;

/**
 *   Name Dto입니다.
 *
 *   @author ParkDooWon, AnHyungJu  
 */
public class NameDto {
	private String name;

	private NameDto(String name) {
		this.name = name;
	}

	public static NameDto from(Name name) {
		return new NameDto(name.getName());
	}

	public String getName() {
		return name;
	}
}
