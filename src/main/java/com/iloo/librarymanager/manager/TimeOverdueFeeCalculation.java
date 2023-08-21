package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a fee
 * based on the time overdue.
 */
class TimeOverdueFeeCalculation implements IFeeCalculationStrategy {
	private double feePerHour;

	public TimeOverdueFeeCalculation(double feePerHour) {
		this.feePerHour = feePerHour;
	}

	@Override
	public double calculateFee(double amount) {
		// 'amount' represents the number of hours overdue
		return feePerHour * amount;
	}
}
