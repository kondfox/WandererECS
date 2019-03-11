package entities;

import components.Position;

import java.util.concurrent.atomic.AtomicInteger;

public interface AutoStepping extends Movable {

  int MAX_SPEED = 60;

  int getSpeed();
  AtomicInteger getStepTryCount();

  default boolean shouldStep() {
    return getStepTryCount().getAndIncrement() > (MAX_SPEED - getSpeed());
  }

  @Override
  default void modifyPosition(Position p) {
    this.getPosition().modifyPosition(p);
    clearPositionModifiers();
    getStepTryCount().set(0);
  }
}
