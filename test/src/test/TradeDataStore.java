package test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;


public class TradeDataStore {
	
	private List<Trade> tradeRecords;
    private static TradeDataStore ordregd = null;
    
    private TradeDataStore(){
    	tradeRecords = new ArrayList<Trade>();
    }
    
    /*
     * Creating a singleton object of OrderDatStore
     */
    public static TradeDataStore getInstance() {
        if(ordregd == null) {
        	ordregd = new TradeDataStore();
              return ordregd;
        }else {
            return ordregd;
        }
    }
    
    /*
     * Adding a record into the tradeRecords List
     */
   public void add(Trade trade) throws Exception {
    	
    	if(!TradeType.BUY.toString().equalsIgnoreCase(trade.getTradeType()) && !TradeType.SELL.toString().equalsIgnoreCase(trade.getTradeType())){
    		throw new Exception("Invalid Trade Type");
    	}
   		tradeRecords.add(trade);
    }

   /*
    * Flushing the tradeRecords list
    */
   public void deleteAll(){
   	if(tradeRecords!=null) {
   		tradeRecords.clear();
   	}
   }
	
    /*
     * Getting all the trade records
     */
    public List<Trade> getTradeRecords(TradeType orderType) {
    	
    	List<Trade> ordList = new ArrayList<Trade>();
    	if(TradeType.ALL == orderType) {
    		ordList = getFilteredRecords(TradeType.BUY);
    		ordList.addAll(getFilteredRecords(TradeType.SELL));
    	}else if(TradeType.BUY == orderType) {
    		ordList = getFilteredRecords(TradeType.BUY);
    	}else if(TradeType.SELL == orderType) {
    		ordList = getFilteredRecords(TradeType.SELL);
    	}
	    return ordList;
	}
    
   /*
    * Returning all BUY records
    */
   private  List<Trade> getFilteredRecords(TradeType orderType) {
    	
	   //Filtering all BUY records
	   List<Trade> orderList = tradeRecords.stream().filter(trade -> trade.getTradeType().equalsIgnoreCase(orderType.toString())).collect(Collectors.toList());
    	
	   //populating settlement date and usd price
	   populateDateAndUSDPrice(orderList);
    	   	
	   //applying sorting
	   if(TradeType.BUY == orderType)
		   orderList.sort((Comparator.comparing(Trade::getUsdAmount).reversed()));
	   else
		   orderList.sort((Comparator.comparing(Trade::getUsdAmount)));
    	
	   return orderList;
	}
   
   /*
    * Logic to add the same price quantities
    * Also creating orderId text
    */
   private void populateDateAndUSDPrice(List<Trade> tradeList) {
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM yyyy", Locale.ENGLISH);
	    LocalDate dateTime = null;
		//populating settlement date and usd price
	   	for(Trade trade : tradeList) {
	   			dateTime = LocalDate.parse(trade.getInstructionDate(), formatter);
	   			int day = dateTime.getDayOfWeek().getValue();
	   			if(trade.getCurrency().equals("AED") || trade.getCurrency().equals("SAR")) {
	   				if(day == 5) {
	   					trade.setSettlementDate(dateTime.plusDays(2).format(formatter));
	   				}else if(day == 6) {
	   					trade.setSettlementDate(dateTime.plusDays(1).format(formatter));
	   				}else {
	   					trade.setSettlementDate(trade.getInstructionDate());
	   				}
	   			}else {
	   				if(day == 6) {
	   					trade.setSettlementDate(dateTime.plusDays(2).format(formatter));
	   				}else if(day == 7) {
	   					trade.setSettlementDate(dateTime.plusDays(1).format(formatter));
	   				}else {
	   					trade.setSettlementDate(trade.getInstructionDate());
	   				}
	   			}
	   			trade.setUsdAmount(BigDecimal.valueOf(trade.getPricePerUnit()*trade.getUnits()*trade.getAgreedFx()));
	   	}
	   
   }
}
