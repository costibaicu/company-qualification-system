package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntentEngine {

    public static List<Company> qualifyCompanies(List<Company> companies, String query)
    {
        String lowerQuery = query.toLowerCase();
        List<String> keywords = Arrays.asList(lowerQuery.split("\\s+"));

        return companies.stream()
                .map(company -> new ScoredCompany(company, calculateScore(company, keywords, lowerQuery)))
                .filter(sc -> sc.score > 0)
                .sorted((a, b) -> Integer.compare(b.score, a.score))
                .map(sc -> sc.company)
                .collect(Collectors.toList());
    }

    private static int calculateScore(Company company, List<String> keywords, String fullQuery)
    {
        int score = 0;

        String name = company.getOperationalName() != null ? company.getOperationalName().toLowerCase() : "";
        String desc = company.getDescription() != null ? company.getDescription().toLowerCase() : "";
        String address = company.getAddress() != null ? company.getAddress().toLowerCase() : "";

        if (fullQuery.contains("public") && (company.getIsPublic() == null || !company.getIsPublic()))
        {
            return 0;
        }

        for (String kw : keywords)
        {
            if (kw.length() < 3) continue;

            if (name.contains(kw)) score += 5;
            if (address.contains(kw)) score += 4;
            if (desc.contains(kw)) score += 2;

            if (company.getCoreOfferings() != null)
            {
                boolean matchOffering = company.getCoreOfferings().stream().anyMatch(off -> off.toLowerCase().contains(kw));
                if (matchOffering) score += 4;
            }

            if (company.getTargetMarkets() != null)
            {
                boolean matchMarket = company.getTargetMarkets().stream().anyMatch(m -> m.toLowerCase().contains(kw));
                if (matchMarket) score += 3;
            }
        }
        return score;
    }

    private static class ScoredCompany
    {
        Company company;
        int score;

        public ScoredCompany(Company company, int score)
        {
            this.company = company;
            this.score = score;
        }
    }
}