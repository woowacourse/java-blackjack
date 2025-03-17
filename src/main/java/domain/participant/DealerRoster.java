package domain.participant;

/*
 * 딜러는 현재 요구사항에 따라 고정된 기본 딜러만 존재한다.
 */
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
