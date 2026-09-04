package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String filePath = "companies.jsonl";
        List<Company> companies = DataLoader.loadCompanies(filePath);
        System.out.println("1. DATA LOADED");
        System.out.println("Uploaded successfully " + companies.size() + " companies.");
        String query = "petroleum"; //just for testing

        List<Company> results = IntentEngine.qualifyCompanies(companies, query);

        System.out.println("\n2. FILTER RESULTS FOR QUERY: " + query);
        System.out.println("Identified companies: " + results.size());

        if (results.isEmpty())
        {
            System.out.println("No companies were found that matched the criteria.");
        } else
        {
            for (int i = 0; i < results.size(); i++)
            {
                Company c = results.get(i);
                System.out.println((i + 1) + ". " + c.getOperationalName() + " | Website: " + c.getWebsite() + " | Address: " + c.getAddress());
            }
        }
    }
}