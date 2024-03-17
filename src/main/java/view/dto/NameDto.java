package view.dto;

import model.player.Name;

public class NameDto {

    private final String name;

    public NameDto(Name name) {
        this.name = name.getValue();
    }

    public String getName() {
        return name;
    }
}
