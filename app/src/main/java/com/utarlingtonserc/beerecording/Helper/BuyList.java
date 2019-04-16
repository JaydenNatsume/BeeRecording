package com.utarlingtonserc.beerecording.Helper;

public class BuyList {
    private String tickerSymbol;
    private String liveQuote;
    private String buyDate;


    public BuyList(){}

    public BuyList(String tickerSymbol, String liveQuote, String buyDate ){
        this.tickerSymbol = tickerSymbol;
        this.liveQuote = liveQuote;
        this.buyDate = buyDate;

    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getLiveQuote() {
        return liveQuote;
    }

    public String getBuyDate() {
        return buyDate;
    }
}
