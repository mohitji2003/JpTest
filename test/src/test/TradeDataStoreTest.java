package test;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;


public class TradeDataStoreTest {

	 /* 
	 * Exercise 1 - Validate for currency SGP settlement date changing
	 * As instruction date is on Sunday so settlement date is Monday
	 */
	  @Test public void testSettlementDateIsNotSameAsInstructinDateBecauseInstructionDateIsOnSunday() { 
		  
		  Trade ord1 = new Trade().setTradeId("101").setEntityName("foo").setTradeType("BUY").setAgreedFx(0.50f).setCurrency("SGP").setInstructionDate("30 Jun 2019").setUnits(200).setPricePerUnit(100.25f);
		  Trade ord2 = new Trade().setTradeId("102").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("30 Jun 2019").setUnits(450).setPricePerUnit(150.5f);
		  
		  TradeDataStore tradeStore = TradeDataStore.getInstance();
		  tradeStore.deleteAll();
		  try {
			  tradeStore.add(ord1);
			  tradeStore.add(ord2);
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  List<Trade> tradeList = tradeStore.getTradeRecords(TradeType.ALL);
	
		  //just for reference purpose
		  System.out.println("testSettlementDateIsNotSameAsInstructinDateBecauseInstructionDateIsOnSunday: "+ tradeList);
		 	 
		  assertEquals("1 Jul 2019", tradeList.get(0).getSettlementDate()); 
	  }
	  
	  
	  /* 
	 * Exercise 2 - Test for currency SGP settlement date not changing
	 * As instruction date is on Monday so settlement date is same
	 */
	  @Test public void testSettlementDateIsSameAsInstructinDateBecauseInstructionDateIsOnMonday() { 
		  
		  Trade ord1 = new Trade().setTradeId("101").setEntityName("foo").setTradeType("BUY").setAgreedFx(0.50f).setCurrency("SGP").setInstructionDate("01 Jul 2019").setUnits(200).setPricePerUnit(100.25f);
		  Trade ord2 = new Trade().setTradeId("102").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("30 Jun 2019").setUnits(450).setPricePerUnit(150.5f);
		  
		  TradeDataStore tradeStore = TradeDataStore.getInstance();
		  tradeStore.deleteAll();
		  try {
			  tradeStore.add(ord1);
			  tradeStore.add(ord2);
		  } catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  List<Trade> tradeList = tradeStore.getTradeRecords(TradeType.ALL);
	
		  //just for reference purpose
		  System.out.println("testSettlementDateIsSameAsInstructinDateBecauseInstructionDateIsOnMonday: "+ tradeList.get(0));
		 	 
		  assertEquals("01 Jul 2019", tradeList.get(0).getSettlementDate()); 
	  }
  
	  /* 
		 * Exercise 3 - Test for currency AED settlement date is changing
		 * As instruction date is on Friday so settlement date is Sunday
		 */
		  @Test public void testSettlementDateIsnotSameAsInstructinDateBecauseInstructionDateIsOnSaturday() { 
			  
			  Trade ord1 = new Trade().setTradeId("101").setEntityName("foo").setTradeType("BUY").setAgreedFx(0.50f).setCurrency("SGP").setInstructionDate("01 Jul 2019").setUnits(200).setPricePerUnit(100.25f);
			  Trade ord2 = new Trade().setTradeId("102").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(450).setPricePerUnit(150.5f);
			  
			  TradeDataStore tradeStore = TradeDataStore.getInstance();
			  tradeStore.deleteAll();
			  try {
				  tradeStore.add(ord1);
				  tradeStore.add(ord2);
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  List<Trade> tradeList = tradeStore.getTradeRecords(TradeType.ALL);
		
			  //just for reference purpose
			  System.out.println("testSettlementDateIsnotSameAsInstructinDateBecauseInstructionDateIsOnSaturday: "+ tradeList.get(1));
			 	 
			  assertEquals("30 Jun 2019", tradeList.get(1).getSettlementDate()); 
		  }
		  
		  
		 /* 
		 * Exercise 3 - Test for BUY Trade sorting on highest amount first
		 */
		  @Test public void testBuyTradeSortOnAmountAsHighestFirst() { 
			  
			  Trade ord1 = new Trade().setTradeId("101").setEntityName("foo").setTradeType("BUY").setAgreedFx(0.50f).setCurrency("SGP").setInstructionDate("01 Jul 2019").setUnits(200).setPricePerUnit(100.25f);
			  Trade ord2 = new Trade().setTradeId("102").setEntityName("bar").setTradeType("BUY").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(450).setPricePerUnit(150.5f);
			  Trade ord3 = new Trade().setTradeId("103").setEntityName("bar").setTradeType("BUY").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(150).setPricePerUnit(150.5f);
			  Trade ord4 = new Trade().setTradeId("104").setEntityName("bar").setTradeType("BUY").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(50).setPricePerUnit(150.5f);
			  
			  TradeDataStore tradeStore = TradeDataStore.getInstance();
			  tradeStore.deleteAll();
			  try {
				  tradeStore.add(ord1);
				  tradeStore.add(ord2);
				  tradeStore.add(ord3);
				  tradeStore.add(ord4);
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  List<Trade> tradeList = tradeStore.getTradeRecords(TradeType.ALL);
		
			  //just for reference purpose
			  System.out.println("testBuyTradeSortOnAmountAsHighestFirst: "+ tradeList.get(0));
			 	 
			  assertEquals(BigDecimal.valueOf(14899.5), tradeList.get(0).getUsdAmount()); 
		  }
		  
		 /* 
		 * Exercise 4 - Test for SELL Trade with lowest amount first
		 */
		  @Test public void testSellTradeSortOnAmountAsLowestFirst() { 
			  
			  Trade ord1 = new Trade().setTradeId("101").setEntityName("foo").setTradeType("SELL").setAgreedFx(0.50f).setCurrency("SGP").setInstructionDate("01 Jul 2019").setUnits(200).setPricePerUnit(100.25f);
			  Trade ord2 = new Trade().setTradeId("102").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(450).setPricePerUnit(150.5f);
			  Trade ord3 = new Trade().setTradeId("103").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(150).setPricePerUnit(150.5f);
			  Trade ord4 = new Trade().setTradeId("104").setEntityName("bar").setTradeType("SELL").setAgreedFx(0.22f).setCurrency("AED").setInstructionDate("28 Jun 2019").setUnits(50).setPricePerUnit(150.5f);
			  
			  TradeDataStore tradeStore = TradeDataStore.getInstance();
			  tradeStore.deleteAll();
			  try {
				  tradeStore.add(ord1);
				  tradeStore.add(ord2);
				  tradeStore.add(ord3);
				  tradeStore.add(ord4);
			  } catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			  List<Trade> tradeList = tradeStore.getTradeRecords(TradeType.ALL);
		
			  //just for reference purpose
			  System.out.println("testSellTradeSortOnAmountAsLowestFirst: "+ tradeList.get(0));
			 	 
			  assertEquals(BigDecimal.valueOf(1655.5), tradeList.get(0).getUsdAmount()); 
		  }
	}
