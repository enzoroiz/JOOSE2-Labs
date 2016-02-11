package fillingcrates;

/**
 * Class that implements the First fit strategy
 * @author enzoroiz
 *
 */
public class FirstFit extends AbstractFit {
	/**
	 * Constructors
	 * 
	 * @param fittingAlgorithm
	 *            name
	 */
	public FirstFit(String fittingAlgorithm) {
		super(fittingAlgorithm);
	}
	
	/**
	 * Constructor
	 */
	public FirstFit() {
		super("First Fit Algorithm");
	}

	@Override
	public void addAmount(int amount) {
		//Create the first crate
		if (this.containers.isEmpty()) {
			createAndStore(amount);
			return;
		}
		
		//Goes through the Array List searching for the first crate in which the amount fits
		for (int i = 0; i < this.containers.size(); i++) {
			try {
				this.containers.get(i).store(amount);
				return;
			} catch (Exception e) {
				e.getMessage();
			}
		}

		createAndStore(amount);
	}

	/**
	 * Create a new crate and store the amount in it
	 * @param amount
	 */
	private void createAndStore(int amount) {
		Crate crate = new Crate(AbstractFit.SIZE);
		try {
			crate.store(amount);
			this.containers.add(crate);
		} catch (InsufficientCapacityException e) {
			e.getMessage();
		}
	}

}
