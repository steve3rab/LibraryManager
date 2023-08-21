package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a fee
 * based on a daily rate.
 */
class DailyRateFeeCalculation implements IFeeCalculationStrategy {
	private double dailyRate;

	public DailyRateFeeCalculation(double dailyRate) {
		this.dailyRate = dailyRate;
	}

	@Override
	public double calculateFee(double amount) {
		// Assuming 'amount' represents the number of days overdue
		return dailyRate * amount;
	}
}