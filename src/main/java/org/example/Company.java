package org.example;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Company
{
    @JsonProperty("operational_name")
    private String operationalName;

    private String website;

    @JsonProperty("year_founded")
    private Double yearFounded;

    private JsonNode address;

    @JsonProperty("employee_count")
    private Integer employeeCount;

    private Double revenue;

    @JsonProperty("primary_naics")
    private JsonNode primaryNaics;

    @JsonProperty("secondary_naics")
    private JsonNode secondaryNaics;

    private String description;

    @JsonProperty("business_model")
    private List<String> businessModel;

    @JsonProperty("core_offerings")
    private List<String> coreOfferings;

    @JsonProperty("target_markets")
    private List<String> targetMarkets;

    @JsonProperty("is_public")
    private Boolean isPublic;

    public String getOperationalName()
    {
        return operationalName;
    }

    public String getWebsite()
    {
        return website;
    }

    public Double getYearFounded()
    {
        return yearFounded;
    }

    public String getAddress()
    {
        if (address == null)
            return null;
        if (address.isTextual())
            return address.asText();
        return address.toString();
    }

    public Integer getEmployeeCount()
    {
        return employeeCount;
    }

    public Double getRevenue()
    {
        return revenue;
    }

    public String getDescription()
    {
        return description;
    }

    public List<String> getCoreOfferings()
    {
        return coreOfferings;
    }

    public Boolean getIsPublic()
    {
        return isPublic;
    }

    @Override
    public String toString()
    {
        return operationalName + " (" + website + ") - " + getAddress();
    }

    public List<String> getTargetMarkets()
    {
        return targetMarkets;
    }
}