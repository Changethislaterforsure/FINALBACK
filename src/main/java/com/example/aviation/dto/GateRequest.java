package com.example.aviation.dto;

public class GateRequest {
    private String code;
    private String terminal;
    private Long airportId;

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getTerminal() { return terminal; }
    public void setTerminal(String terminal) { this.terminal = terminal; }

    public Long getAirportId() { return airportId; }
    public void setAirportId(Long airportId) { this.airportId = airportId; }
}