package com.iloo.librarymanager.manager;

/**
 * A concrete implementation of IFeeCalculationStrategy that calculates a fee
 * based on the number of items.
 */
class ItemCountFeeCalculation implements IFeeCalculationStrategy {
	private double feePerItem;

	public ItemCountFeeCalculation(double feePerItem) {
		this.feePerItem = feePerItem;
	}

	@Override
	public double calculateFee(double amount) {
		// 'amount' represents the number of items with fees
		return feePerItem * amount;
	}
}
