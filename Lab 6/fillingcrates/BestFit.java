package fillingcrates;

/**
 * Class that implements the First fit strategy
 * 
 * @author enzoroiz
 *
 */
public class BestFit extends AbstractFit {
	private static final int NOT_FOUND = -1;

	/**
	 * Constructors
	 * 
	 * @param fittingAlgorithm
	 *            name
	 */
	public BestFit(String fittingAlgorithm) {
		super(fittingAlgorithm);
	}

	/**
	 * Constructor
	 */
	public BestFit() {
		super("Best Fit Algorithm");
	}

	@Override
	public void addAmount(int amount) {
		//Create the first crate
		if (this.containers.isEmpty()) {
			storeCrate(amount, null);
			return;
		}

		int bestCrateIndex = findCrate(amount);
		
		if (bestCrateIndex == NOT_FOUND) {
			storeCrate(amount, null);
		} else {
			storeCrate(amount, this.containers.get(bestCrateIndex));
		}

	}

	/**
	 * Methods
	 */

	/**
	 * Method to store an amount in a certain crate
	 * @param amount to store
	 * @param crate in which the amount will be store
	 */
	private void storeCrate(int amount, FillableContainer crate) {
		if (crate == null) {
			crate = new Crate(AbstractFit.SIZE);

			try {
				crate.store(amount);
				this.containers.add(crate);
			} catch (InsufficientCapacityException e) {
				e.getMessage();
			}
		} else {
			try {
				crate.store(amount);
			} catch (InsufficientCapacityException e) {
				e.getMessage();
			}
		}

	}

	/**
	 * Find the crate in which the amount best fits
	 * @param amount to be stored
	 * @return the index of the crate in which the amount best fits or -1 if the amount doesn't fit in any
	 */
	private int findCrate(int amount) {
		int bestFitIndex = NOT_FOUND;
		int bestFitCrateCapacity = AbstractFit.SIZE;
		for (int i = 0; i < this.containers.size(); i++) {
			if (this.containers.get(i).getAvailableCapacity() >= amount
					&& this.containers.get(i).getAvailableCapacity() < bestFitCrateCapacity) {
				bestFitIndex = i;
				bestFitCrateCapacity = this.containers.get(i)
						.getAvailableCapacity();
			}
		}

		return bestFitIndex;
	}

}
