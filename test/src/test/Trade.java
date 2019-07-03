package test;

import java.math.BigDecimal;

public class Trade {

	private String tradeId;
	private String entityName;
	private String tradeType;
	private float agreedFx;
	private String currency;
	private String instructionDate;
	private String settlementDate;
	private int units;
	private float pricePerUnit;
	private BigDecimal usdAmount;
	public String getTradeId() {
		return tradeId;
	}
	public Trade setTradeId(String tradeId) {
		this.tradeId = tradeId;
		return this;
	}
	public String getEntityName() {
		return entityName;
	}
	public Trade setEntityName(String entityName) {
		this.entityName = entityName;
		return this;
	}
	public String getTradeType() {
		return tradeType;
	}
	public Trade setTradeType(String tradeType) {
		this.tradeType = tradeType;
		return this;
	}
	public float getAgreedFx() {
		return agreedFx;
	}
	public Trade setAgreedFx(float agreedFx) {
		this.agreedFx = agreedFx;
		return this;
	}
	@Override
	public String toString() {
		return "Trade [tradeId=" + tradeId + ", entityName=" + entityName + ", tradeType=" + tradeType + ", agreedFx="
				+ agreedFx + ", currency=" + currency + ", instructionDate=" + instructionDate + ", settlementDate="
				+ settlementDate + ", units=" + units + ", pricePerUnit=" + pricePerUnit + ", usdAmount=" + usdAmount
				+ "]";
	}
	public String getCurrency() {
		return currency;
	}
	public Trade setCurrency(String currency) {
		this.currency = currency;
		return this;
	}
	public String getInstructionDate() {
		return instructionDate;
	}
	public Trade setInstructionDate(String instructionDate) {
		this.instructionDate = instructionDate;
		return this;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public int getUnits() {
		return units;
	}
	public Trade setUnits(int units) {
		this.units = units;
		return this;
	}
	public float getPricePerUnit() {
		return pricePerUnit;
	}
	public Trade setPricePerUnit(float pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
		return this;
	}
	public BigDecimal getUsdAmount() {
		return usdAmount;
	}
	public void setUsdAmount(BigDecimal usdAmount) {
		this.usdAmount = usdAmount;
	}
	
}
