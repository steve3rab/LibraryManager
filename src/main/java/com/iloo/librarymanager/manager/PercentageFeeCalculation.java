package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a
 * percentage-based fee.
 */
class PercentageFeeCalculation implements IFeeCalculationStrategy {
	private double percentage;

	public PercentageFeeCalculation(double percentage) {
		this.percentage = percentage;
	}

	@Override
	public double calculateFee(double amount) {
		return (percentage / 100) * amount;
	}
}
