package com.iloo.librarymanager.manager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The FeeManager class is responsible for tracking and managing fees for
 * library patrons. It allows librarians to record and update fees associated
 * with library users.
 */
public class FeeManager {
	private static final Logger LOGGER = LogManager.getLogger(FeeManager.class);
	private double totalFees;
	private IFeeCalculationStrategy feeCalculationStrategy;

	/**
	 * Constructs a FeeManager object with an initial total fee of 0 and a fee
	 * calculation strategy.
	 *
	 * @param feeCalculationStrategy The strategy for calculating fees.
	 */
	public FeeManager(IFeeCalculationStrategy feeCalculationStrategy) {
		totalFees = 0.0;
		this.feeCalculationStrategy = feeCalculationStrategy;
	}

	/**
	 * Records a fee for a library patron.
	 *
	 * @param patronName The name of the patron who incurred the fee.
	 * @param amount     The amount of the fee to be recorded.
	 */
	public void recordFee(String patronName, double amount) {
		// Use the fee calculation strategy to calculate the fee.
		double fee = feeCalculationStrategy.calculateFee(amount);

		// Add the calculated fee to the total fees.
		totalFees += fee;

		LOGGER.info("Fee of ${} recorded for patron: {}", fee, patronName);
	}

	/**
	 * Applies a discount to the total fees. This method reduces the total fees by a
	 * specified discount amount.
	 *
	 * @param discountAmount The amount to be deducted from the total fees as a
	 *                       discount.
	 */
	public void applyDiscount(double discountAmount) {
		if (discountAmount >= 0) {
			totalFees -= discountAmount;
			LOGGER.info("Discount of ${} applied.", discountAmount);
		} else {
			LOGGER.info("Invalid discount amount. Please provide a non-negative value.");
		}
	}

	/**
	 * Retrieves the total fees currently owed to the library.
	 *
	 * @return The total fees owed to the library.
	 */
	public double getTotalFees() {
		return totalFees;
	}

}
