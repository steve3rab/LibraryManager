package com.iloo.librarymanager.manager;

/**
 * The IFeeCalculationStrategy interface defines a strategy for calculating
 * fees.
 */
interface IFeeCalculationStrategy {
	double calculateFee(double amount);
}
