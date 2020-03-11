package common;

import domain.gamer.Gamer;

public class GamerDto {
    private String name;

    private GamerDto(String name) {
        this.name = name;
    }

    public static GamerDto of(Gamer gamer) {
        return new GamerDto(gamer.getName());
    }

    public String getName() {
        return name;
    }
}
