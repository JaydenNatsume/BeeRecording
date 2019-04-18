package com.utarlingtonserc.beerecording.Helper;

public class PerformanceList {
    private String tickerSymbol;
    private String transPerformance;
    private String transDate;


    public PerformanceList(){}

    public PerformanceList(String tickerSymbol, String transPerformance, String transDate){
        this.tickerSymbol = tickerSymbol;
        this.transPerformance = transPerformance;
        this.transDate = transDate;

    }

    public String getTickerSymbol() {
        return tickerSymbol;
    }

    public String getTransPerformance() {
        return transPerformance;
    }

    public String getTransDate() {
        return transDate;
    }
}
