package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a fee
 * based on the total borrowed value.
 */
class BorrowedValueFeeCalculation implements IFeeCalculationStrategy {
	private double feePercentage;

	public BorrowedValueFeeCalculation(double feePercentage) {
		this.feePercentage = feePercentage;
	}

	@Override
	public double calculateFee(double amount) {
		// 'amount' represents the total value of borrowed items
		return (feePercentage / 100) * amount;
	}
}
