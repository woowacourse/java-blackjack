package constant;

public enum DealerRoster {
  DEFAULT("딜러"),
  ;

  private final String name;

  DealerRoster(final String value) {
    this.name = value;
  }

  public String getName() {
    return name;
  }
}
