package fillingcrates;

/**
 * Class to represent crate objects
 * @author enzoroiz
 *
 */
public class Crate implements FillableContainer {
	/**
	 * Instance fields
	 */
	int capacity;
	int usedCapacity;

	/**
	 * Constructor
	 */
	public Crate(int capacity) {
		this.capacity = capacity;
		this.usedCapacity = 0;
	}

	@Override
	public int getTotalCapacity() {
		return capacity;
	}

	@Override
	public int getAvailableCapacity() {
		return getTotalCapacity() - getUsedCapacity();
	}

	@Override
	public int getUsedCapacity() {
		return usedCapacity;
	}

	@Override
	public boolean isEmpty() {
		return usedCapacity == 0;
	}

	@Override
	public void store(int size) throws InsufficientCapacityException {
		if (size > getAvailableCapacity()) {
			throw new InsufficientCapacityException(size, capacity);
		} else {
			usedCapacity += size;
		}

	}

}
