package com.sourcey.materiallogindemo.ListView;

public class WatchList {
    private String tickerSymbol;
    private String liveQuote;


    public WatchList(){}

    public WatchList(String tickerSymbol, String liveQuote ){
        this.tickerSymbol = tickerSymbol;
        this.liveQuote = liveQuote;

    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getLiveQuote() {
        return liveQuote;
    }
}
