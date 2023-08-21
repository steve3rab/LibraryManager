package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a fixed
 * fee.
 */
class FixedFeeCalculation implements IFeeCalculationStrategy {
	private double fixedFee;

	public FixedFeeCalculation(double fixedFee) {
		this.fixedFee = fixedFee;
	}

	@Override
	public double calculateFee(double amount) {
		return fixedFee;
	}
}
