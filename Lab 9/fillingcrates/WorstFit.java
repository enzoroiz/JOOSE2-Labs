package fillingcrates;

import java.util.List;

/**
 * Class that implements the Worst fit strategy
 * 
 * @author enzoroiz
 *
 */
public class WorstFit extends AbstractFit {
	private static final int NOT_FOUND = -1;

	/**
	 * Constructors
	 * 
	 * @param fittingAlgorithm
	 *            name
	 */
	public WorstFit(String fittingAlgorithm) {
		super(fittingAlgorithm);
	}
	
	/**
	 * Constructor
	 */
	public WorstFit(List<FillableContainer> containers) {
		super("Worst Fit Algorithm", containers);
	}

	@Override
	public void addAmount(int amount) {
		//Create the first crate
		if (this.containers.isEmpty()) {
			storeCrate(amount, null);
			return;
		}

		int worstCrateIndex = findCrate(amount);
		
		if (worstCrateIndex == NOT_FOUND) {
			storeCrate(amount, null);
		} else {
			storeCrate(amount, this.containers.get(worstCrateIndex));
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
	 * Find the crate in which the amount 'worst' fits
	 * @param amount to be stored
	 * @return the index of the crate in which the amount 'worst' fits or -1 if the amount doesn't fit in any
	 */
	private int findCrate(int amount) {
		int worstFitIndex = NOT_FOUND;
		int worstFitCrateCapacity = 0;
		for (int i = 0; i < this.containers.size(); i++) {
			if (this.containers.get(i).getAvailableCapacity() >= amount	&& this.containers.get(i).getAvailableCapacity() > worstFitCrateCapacity) {
				worstFitIndex = i;
				worstFitCrateCapacity = this.containers.get(i)
						.getAvailableCapacity();
			}
		}

		return worstFitIndex;
	}

}
